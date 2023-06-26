package com.learningwithsomesh.kafkasagapattern.orderservice.eventhandlers;



import com.learningwithsomesh.kafkasagapattern.model.enums.*;
import com.learningwithsomesh.kafkasagapattern.model.evt.InventoryEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learningwithsomesh.kafkasagapattern.model.evt.PaymentEvent;
import com.learningwithsomesh.kafkasagapattern.model.evt.ShippingEvent;
import com.learningwithsomesh.kafkasagapattern.orderservice.entity.PurchaseOrder;
import com.learningwithsomesh.kafkasagapattern.orderservice.repository.PurchaseOrderRepository;

import javax.transaction.Transactional;

@Service
public class InventoryEventConsumerService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private ShippingEventPublisherService seps;

    @Transactional
    public void consumeInventoryEvent(InventoryEvent inventoryEvent){
        this.purchaseOrderRepository.findById(inventoryEvent.getOrderId())
                .ifPresent(purchaseOrder -> {
                    purchaseOrder.setStatus(inventoryEvent.getStatus().equals(InventoryStatus.ONSTOCK) ? OrderStatus.ORDER_CONTINUE : OrderStatus.ORDER_CANCELLED);
                    this.purchaseOrderRepository.save(purchaseOrder);
                    if (inventoryEvent.getStatus().equals(InventoryStatus.ONSTOCK)) {
                        // Inventar verfügbar
                        System.out.println("Inventory is available. Proceed with further actions.");
                        // Führen Sie hier weitere Aktionen für den Fall aus, dass das Inventar verfügbar ist
                    } else {
                        // Inventar nicht verfügbar
                        System.out.println("Inventory is not available. Handle out-of-stock scenario.");
                        // Führen Sie hier weitere Aktionen für den Fall aus, dass das Inventar nicht verfügbar ist
                    }

                });

    }

}