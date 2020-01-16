package com.williams.record.model.response;

import com.williams.record.model.entity.Record;
import com.williams.record.util.TimeUtil;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class RecordResponse {

    private String uniqueKey;
    private String nameOfProduct;
    private String description;
    private double price;
    private String quantity;
    private String created_At;
    private String update_At;

    public static RecordResponse fromRecordUpdate(Record record){
        return RecordResponse.builder()
                .created_At(TimeUtil.getIsoTime(record.getCreated_At()))
                .update_At(TimeUtil.getIsoTime(record.getUpdate_At()))
                .uniqueKey(record.getUniqueKey())
                .nameOfProduct(record.getNameOfProduct())
                .description(record.getDescription())
                .price(record.getPrice())
                .quantity(record.getQuantity())
                .build();
    }

    public static List<RecordResponse> fromRecord(List<Record> records){
        return records.stream().map(record ->{
            return fromRecordUpdate(record);
        } ).collect(Collectors.toList());
    }
}
