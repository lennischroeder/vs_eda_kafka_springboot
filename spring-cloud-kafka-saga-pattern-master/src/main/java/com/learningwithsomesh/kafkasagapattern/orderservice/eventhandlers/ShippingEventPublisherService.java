package com.learningwithsomesh.kafkasagapattern.orderservice.eventhandlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learningwithsomesh.kafkasagapattern.model.evt.ShippingEvent;
import com.learningwithsomesh.kafkasagapattern.model.evt.ShippingAfterPaymentEvent;
import com.learningwithsomesh.kafkasagapattern.orderservice.entity.PurchaseOrder;

import reactor.core.publisher.FluxSink;

@Service
public class ShippingEventPublisherService {

    @Autowired FluxSink<ShippingAfterPaymentEvent> shippingEventChannel;

    public void raiseShippingCreatedEvent(final PurchaseOrder purchaseOrder) {
        ShippingAfterPaymentEvent sape = new ShippingAfterPaymentEvent();
        sape.setOrderId(purchaseOrder.getId());
        sape.setPincode(purchaseOrder.getPincode());
        sape.setUserId(purchaseOrder.getUserId());
        this.shippingEventChannel.next(sape);
    }

}