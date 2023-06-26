package com.learningwithsomesh.kafkasagapattern.inventoryservice.configuration;




import com.learningwithsomesh.kafkasagapattern.inventoryservice.eventhandlers.InventoryEventProcessorService;
import com.learningwithsomesh.kafkasagapattern.model.evt.InventoryEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.learningwithsomesh.kafkasagapattern.model.evt.OrderEvent;


import java.util.function.Function;

@Configuration
public class InventoryServiceConfig {

    @Autowired
    private InventoryEventProcessorService inventoryEventProcessorService;

    @Bean
    public Function<OrderEvent, InventoryEvent> inventoryEventProcessor(){
        return inventoryEventProcessorService::processIventoryOrderEvent;
    }
}
