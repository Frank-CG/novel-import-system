package com.assignment.novelimportsystem.services;

import com.assignment.novelimportsystem.models.WriterInfo;
import com.assignment.novelimportsystem.repositories.WriterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WriterServiceImpl implements WriterService {
    private WriterRepository writerRepository;

    @Autowired
    public WriterServiceImpl(WriterRepository writerRepository) {
        this.writerRepository = writerRepository;
    }

    @Override
    public List<WriterInfo> listData() {
        List<WriterInfo> writerInfos = new ArrayList<>();
        writerRepository.findAll().forEach(writerInfos::add);
        return writerInfos;
    }

    @Override
    public WriterInfo getById(Long id) {
        return writerRepository.findById(id).orElse(null);
    }

    @Override
    public WriterInfo saveOrUpdate(WriterInfo writerInfo) {
        writerRepository.save(writerInfo);
        return writerInfo;
    }

    @Override
    public void deleteById(Long id) {
        writerRepository.deleteById(id);
    }
}
