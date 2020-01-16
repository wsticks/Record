package com.williams.record.repository;

import com.williams.record.model.entity.Record;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository  extends PagingAndSortingRepository<Record ,Long>{

    Record findOneByUniqueKey(String uniqueKey);
    List<Record> findAll();
}
