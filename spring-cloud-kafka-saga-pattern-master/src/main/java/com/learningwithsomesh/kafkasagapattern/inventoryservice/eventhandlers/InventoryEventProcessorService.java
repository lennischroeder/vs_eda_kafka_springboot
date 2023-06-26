package com.learningwithsomesh.kafkasagapattern.inventoryservice.eventhandlers;





import com.learningwithsomesh.kafkasagapattern.model.enums.InventoryStatus;
import com.learningwithsomesh.kafkasagapattern.model.evt.InventoryEvent;
import org.springframework.stereotype.Service;


import com.learningwithsomesh.kafkasagapattern.model.evt.OrderEvent;


import java.util.HashMap;
import java.util.Map;

@Service
public class InventoryEventProcessorService {

    // user - credit limit
    public static final Map<Integer, Integer> productMap = new HashMap<>();

    static {
        productMap.put(1, 1000);
        productMap.put(2, 2000);
        productMap.put(3, 500);
    }

    public InventoryEvent processIventoryOrderEvent(OrderEvent orderEvent) {
        int productId = orderEvent.getProductId();
        int quantity = orderEvent.getQuantity();

        InventoryEvent inventoryEvent = new InventoryEvent(orderEvent.getOrderId());

        if (productMap.containsKey(productId)) {
            int inventoryLimit = productMap.get(productId);
            if (inventoryLimit >= quantity) {
                inventoryEvent.setStatus(InventoryStatus.ONSTOCK);
                productMap.put(productId, inventoryLimit - quantity);
            } else {
                inventoryEvent.setStatus(InventoryStatus.EMPTY);
            }
        }

        System.out.println("XXXXXXXXX Code going out of processOrderEvent with inventoryEvent: " + inventoryEvent);

        return inventoryEvent;
    }
}

