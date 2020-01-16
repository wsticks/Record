package com.williams.record.service;

import com.williams.record.exception.ConflictException;
import com.williams.record.exception.NotFoundException;
import com.williams.record.exception.ProcessingException;
import com.williams.record.exception.RecordApiException;
import com.williams.record.model.entity.Record;
import com.williams.record.model.request.RecordRequest;
import com.williams.record.model.response.RecordResponse;
import com.williams.record.repository.RecordRepository;
import com.williams.record.util.GatewayBeanUtil;
import com.williams.record.util.SecurityUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RecordService {

    private final RecordRepository recordRepository;
    private final String RECORD_EXIST = "Record already exist";
    private final String RECORD_NOT_FOUND ="Record to update was not found";


    public RecordService(RecordRepository recordRepository) {
        Assert.notNull(recordRepository);
        this.recordRepository = recordRepository;
    }

    public Record createRecord(RecordRequest recordRequest, Record record){
        prepareForSave(record);
        Record record1 = recordRepository.findOneByUniqueKey(record.getUniqueKey());
        if(record1 != null){
            throw new ConflictException(RECORD_EXIST);
        }
        return recordRepository.save(record);
    }

    public Record updateRecord(RecordRequest updateRecord, String recordKey){
        Record recordToUpdate = fetchRecordByUniqueKey(recordKey);
        GatewayBeanUtil.copyProperties(updateRecord,recordToUpdate);
        return recordRepository.save(recordToUpdate);
    }

    private void prepareForSave(Record record) throws RecordApiException {
        generateUniqueKey(record);
    }



    private void generateUniqueKey(Record record) throws ProcessingException {
        if (record.getUniqueKey() != null) {
            return;
        }
        String rawKey = record.getDescription() + LocalDateTime.now() + Math.random() + record.getNameOfProduct();
        String uniqueKey = SecurityUtil.hashWithMd5(rawKey);
        record.setUniqueKey(uniqueKey);
    }

//    private void generateUniqueKey(Record recordResponse) throws ProcessingException {
//        if (recordResponse.getUniqueKey() != null) {
//            return;
//        }
//        String rawKey = recordResponse.getDescription() + LocalDateTime.now() + Math.random() + recordResponse.getNameOfProduct();
//        String uniqueKey = SecurityUtil.hashWithMd5(rawKey);
//        recordResponse.setUniqueKey(uniqueKey);
//    }

    public Record fetchRecordByUniqueKey(String recordKey){
        Record savedRecord = recordRepository.findOneByUniqueKey(recordKey);
        if(savedRecord == null){
            throw new NotFoundException(RECORD_NOT_FOUND);
        }
        return savedRecord;
    }

    public Record viewRecord(String recordKey){
        Record record = recordRepository.findOneByUniqueKey(recordKey);
        return record;
    }

    public List<Record> viewRecords(){
        List<Record> records = recordRepository.findAll();
        return records;
    }
}
