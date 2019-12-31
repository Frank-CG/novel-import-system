package com.assignment.novelimportsystem.services;

import com.assignment.novelimportsystem.models.NovelInfo;
import com.assignment.novelimportsystem.models.WriterInfo;
import com.assignment.novelimportsystem.repositories.NovelRepository;
import com.assignment.novelimportsystem.repositories.WriterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NovelServiceImpl implements NovelService {
    private NovelRepository novelRepository;
    private WriterRepository writerRepository;

    @Autowired
    public NovelServiceImpl(NovelRepository novelRepository, WriterRepository writerRepository) {
        this.novelRepository = novelRepository;
        this.writerRepository = writerRepository;
    }

    @Override
    public List<NovelInfo> getAllNovelInfo() {
        List<NovelInfo> novelInfoList = new ArrayList<>();
        novelRepository.findAll().forEach(novelInfoList::add);
        return novelInfoList;
    }

    @Override
    public List<NovelInfo> getNovelInfosByWriterId(Long writerId) {
        List<NovelInfo> novelInfoList = new ArrayList<>();
        novelRepository.findAll().forEach(novelInfo -> {
            if(novelInfo.getWriterId().equals(writerId)){
                novelInfoList.add(novelInfo);
            }
        });
        return novelInfoList;
    }

    @Override
    public NovelInfo getNovelInfoById(Long id) {
        return novelRepository.findById(id).orElse(null);
    }

    @Override
    public boolean checkData(NovelInfo novelInfo) {
        if(novelInfo.getWriterId() == null){
            return false;
        }
        Optional<WriterInfo> writer = writerRepository.findById(novelInfo.getWriterId());
        if(!writer.isPresent()){
            return false;
        }
        return true;
    }

    @Override
    public NovelInfo saveOrUpdate(NovelInfo novelInfo) {
        novelRepository.save(novelInfo);
        return novelInfo;
    }

    @Override
    public void delete(Long id) {
        novelRepository.deleteById(id);
    }
}
