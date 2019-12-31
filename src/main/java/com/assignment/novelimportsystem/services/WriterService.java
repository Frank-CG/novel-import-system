package com.assignment.novelimportsystem.services;

import com.assignment.novelimportsystem.models.WriterInfo;

import java.util.List;

public interface WriterService {
    List<WriterInfo> listData();
    WriterInfo getById(Long id);
    WriterInfo saveOrUpdate(WriterInfo writerInfo);
    void deleteById(Long id);
}
