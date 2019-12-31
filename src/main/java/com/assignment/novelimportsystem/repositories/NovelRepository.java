package com.assignment.novelimportsystem.repositories;

import com.assignment.novelimportsystem.models.NovelInfo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NovelRepository extends CrudRepository<NovelInfo,Long> {

    @Modifying
    @Query(
            value = "truncate table novel_info",
            nativeQuery = true
    )
    void truncate();
}
