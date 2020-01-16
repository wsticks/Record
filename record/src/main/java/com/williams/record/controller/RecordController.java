package com.williams.record.controller;

import com.williams.record.model.entity.Record;
import com.williams.record.model.request.RecordRequest;
import com.williams.record.service.RecordService;
import com.williams.record.service.facade.AccountFacade;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/product")
public class RecordController {

    private final RecordService recordService;
    private final AccountFacade accountFacade;

    public RecordController(RecordService recordService, AccountFacade accountFacade) {
        this.accountFacade = accountFacade;
        Assert.notNull(recordService);
        this.recordService = recordService;
    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json")
    public Record record(@RequestBody RecordRequest recordRequest, Record record){
        Record record1 = accountFacade.createRecord(recordRequest);
        return record1;
    }

    @Transactional
    @RequestMapping(method = RequestMethod.PUT, path = "/{recordKey}",
            consumes = "application/json",
            produces = "application/json")
    public Record updateRecord(
            @PathVariable String recordKey,
            @RequestBody RecordRequest recordRequest){
        Record record = recordService.updateRecord(recordRequest,recordKey);
        return record;
    }

    @Transactional
    @RequestMapping(method = RequestMethod.GET, path = "/{recordKey}",
            produces = "application/json")
    public Record viewRecord(@PathVariable String recordKey){
        Record record = recordService.viewRecord(recordKey);
        return record;
    }

    @Transactional
    @RequestMapping(method = RequestMethod.GET,
            produces = "application/json")
    public List<Record> viewRecords(){
        List<Record> records = recordService.viewRecords();
        return records;
    }
}
