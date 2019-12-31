package com.assignment.novelimportsystem.services;

import com.assignment.novelimportsystem.models.NovelInfo;

import java.util.List;


public interface NovelService{
    List<NovelInfo> getAllNovelInfo();
    List<NovelInfo> getNovelInfosByWriterId(Long writeId);
    NovelInfo getNovelInfoById(Long novelId);
    boolean checkData(NovelInfo novelInfo);
    NovelInfo saveOrUpdate(NovelInfo novelInfo);
    void delete(Long id);
}
