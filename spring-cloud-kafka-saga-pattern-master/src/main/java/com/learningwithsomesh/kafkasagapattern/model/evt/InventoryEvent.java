package com.learningwithsomesh.kafkasagapattern.model.evt;



import com.learningwithsomesh.kafkasagapattern.model.enums.InventoryStatus;
import com.learningwithsomesh.kafkasagapattern.model.enums.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class InventoryEvent {

    private Integer orderId;
    private InventoryStatus status;
    private Integer productId;

    public InventoryEvent(Integer orderId) {
        this.orderId = orderId;
    }

}