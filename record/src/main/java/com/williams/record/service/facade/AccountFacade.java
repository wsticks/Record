package com.williams.record.service.facade;

import com.williams.record.model.entity.Record;
import com.williams.record.model.request.RecordRequest;
import com.williams.record.service.RecordService;
import org.springframework.stereotype.Component;

@Component
public class AccountFacade {

    private  final RecordService recordService;

    public AccountFacade(RecordService recordService) {
        this.recordService = recordService;
    }

    public Record createRecord(RecordRequest recordRequest){
        Record record = recordService.createRecord(recordRequest, recordRequest.toRecord());
        return record;
    }
}
