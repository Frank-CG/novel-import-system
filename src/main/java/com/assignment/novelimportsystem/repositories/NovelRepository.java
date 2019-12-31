package com.assignment.novelimportsystem.repositories;

import com.assignment.novelimportsystem.models.NovelInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NovelRepository extends CrudRepository<NovelInfo,Long> {
}
