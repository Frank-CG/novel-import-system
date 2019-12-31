package com.assignment.novelimportsystem.controllers;

import com.assignment.novelimportsystem.models.NovelInfo;
import com.assignment.novelimportsystem.payloads.FailedRecord;
import com.assignment.novelimportsystem.payloads.Response;
import com.assignment.novelimportsystem.services.NovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RequestMapping("api/v1/novel")
@RestController
public class NovelController {
    private final NovelService novelService;

    @Autowired
    public NovelController(NovelService novelService) {
        this.novelService = novelService;
    }

    @GetMapping
    public List<NovelInfo> getAllNovelInfo() {
        return novelService.getAllNovelInfo();
    }

//    @GetMapping(path = "{writerId}")
//    public List<NovelInfo> getNovelInfosByWriterId(@PathVariable("writerId")Long writerId) {
//        return novelService.getNovelInfosByWriterId(writerId);
//    }

    @GetMapping(path = "{id}")
    public NovelInfo getNovelInfoById(@PathVariable("id") Long id){
        return novelService.getNovelInfoById(id);
    }

    @PostMapping
    public Response addOrUpdateNovelInfo(@RequestBody @Valid NovelInfo novelInfo){
        System.out.println(novelInfo);
        Response response = null;
        boolean flag = novelService.checkData(novelInfo);
        if(flag){
            novelService.saveOrUpdate(novelInfo);
            response = new Response(200,"Success",null);
        }else{
            response = new Response(400,"Failed on validation",null);
        }
        return response;
    }

    private boolean processSingleLine(String line){
        System.out.println(line);
        if(line.split(",").length != 5){
            return false;
        }
        NovelInfo novelInfo = NovelInfo.parse(line);
        boolean flag = novelService.checkData(novelInfo);
        if(flag){
            novelService.saveOrUpdate(novelInfo);
        }else{
            return false;
        }
        return true;
    }

    @PostMapping("/fileImport")
    public Response fileImport(@RequestParam MultipartFile file) {
        Response response = null;
        HashMap<String,Object> metadata = new HashMap<>();
        ArrayList<FailedRecord> failedRecords = new ArrayList<>();
        int count = 0;
        int failedCount = 0;
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String line = reader.readLine();
            while (line != null) {
                count++;
                System.out.println("Line:"+line);
                boolean flag = processSingleLine(line);
                if (!flag){
                    failedCount++;
                    failedRecords.add(new FailedRecord(count,line));
                    System.out.println("Failed to process record:[LineNum="+count+"; Content="+line+";]");
                }
                System.out.println(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException ex){
            ex.printStackTrace();;
        }
        metadata.put("Total Count",count);
        metadata.put("Success Count",count - failedCount);
        metadata.put("Failed Count",failedCount);
        if(failedCount > 0){
            metadata.put("Failed Records",failedRecords);
        }
        if(count > 0){
            if(failedCount == 0){
                response = new Response(200,"Success",metadata);
            }else if(count > failedCount){
                response = new Response(200,"Partially Success",metadata);
            }else if(count == failedCount){
                response = new Response(400,"All records are not valid",metadata);
            }
        }else{
            response = new Response(400,"File content is empty",null);
        }
        return response;
    }

    @DeleteMapping(path = "{id}")
    public void deleteNovelInfo(@PathVariable("id")Long id){
        novelService.delete(id);
    }
}
