package com.assignment.novelimportsystem.controllers;

import com.assignment.novelimportsystem.models.WriterInfo;
import com.assignment.novelimportsystem.services.WriterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("api/v1/writer")
@RestController
public class WriterController {
    private final WriterService writerService;

    @Autowired
    public WriterController(WriterService writerService) {
        this.writerService = writerService;
    }

    @GetMapping
    public List<WriterInfo> listAll() {
        return writerService.listData();
    }

    @PostMapping
    public void add(@RequestBody @Valid WriterInfo writerInfo) {
        writerService.saveOrUpdate(writerInfo);
    }

    @DeleteMapping
    public void delete(@PathVariable("writerId")Long writerId){
        writerService.deleteById(writerId);
    }
}
