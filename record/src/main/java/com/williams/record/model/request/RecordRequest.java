package com.williams.record.model.request;

import com.williams.record.model.entity.Record;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class RecordRequest {

//    private String uniqueKey;
    private String nameOfProduct;
    private String description;
    private double price;
    private String quantity;

    public Record toRecord(){
        Record record = new Record();
        record.setNameOfProduct(nameOfProduct);
        record.setDescription(description);
        record.setPrice(price);
        record.setQuantity(quantity);
//        record.setUniqueKey(uniqueKey);
        return record;
    }
}
