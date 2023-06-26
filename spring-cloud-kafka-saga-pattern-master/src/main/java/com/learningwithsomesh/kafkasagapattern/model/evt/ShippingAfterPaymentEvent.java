package com.learningwithsomesh.kafkasagapattern.model.evt;

import lombok.Data;

@Data
public class ShippingAfterPaymentEvent {
    private Integer orderId;
    private Integer userId;
    private Integer pincode;
}