package com.assignment.novelimportsystem.repositories;

import com.assignment.novelimportsystem.models.WriterInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WriterRepository extends CrudRepository<WriterInfo,Long> {
}
