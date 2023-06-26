package com.learningwithsomesh.kafkasagapattern.orderservice.configuration;

import com.learningwithsomesh.kafkasagapattern.model.evt.*;
import com.learningwithsomesh.kafkasagapattern.orderservice.eventhandlers.InventoryEventConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.learningwithsomesh.kafkasagapattern.model.evt.ShippingAfterPaymentEvent;
import com.learningwithsomesh.kafkasagapattern.orderservice.eventhandlers.PaymentEventConsumerService;
import com.learningwithsomesh.kafkasagapattern.orderservice.eventhandlers.ShippingEventConsumerService;

import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;
import java.util.function.Supplier;

@Configuration
public class OrderServiceConfig {

    @Autowired
    private PaymentEventConsumerService consumerService;

    @Autowired
    InventoryEventConsumerService inventoryConsumerService;
    
    @Autowired
    private ShippingEventConsumerService shippingConsumerService;

    @Bean
    public DirectProcessor<OrderEvent> getFlux(){
        return DirectProcessor.create();
    }

    @Bean
    public DirectProcessor<ShippingAfterPaymentEvent> getFlux1(){
        return DirectProcessor.create();
    }

    
    @Bean
    public FluxSink<OrderEvent> orderEventChannel(DirectProcessor<OrderEvent> processor){
        return processor.sink();
    }
    
    @Bean
    public FluxSink<ShippingAfterPaymentEvent> paymentToShippingEventChannel(DirectProcessor<ShippingAfterPaymentEvent> processor){
        return processor.sink();
    }

    @Bean
    public Supplier<Flux<OrderEvent>> orderEventPublisher(DirectProcessor<OrderEvent> processor){
        return () -> processor;
    }
    
    @Bean
    public Supplier<Flux<ShippingAfterPaymentEvent>> shippingEventPublisher(DirectProcessor<ShippingAfterPaymentEvent> processor){
        return () -> processor;
    }

    @Bean
    public Consumer<PaymentEvent> paymentEventConsumer(){
        return consumerService::consumePaymentEvent;
    }

    @Bean
    public Consumer<InventoryEvent> inventoryEventConsumer(){return inventoryConsumerService::consumeInventoryEvent;}
    

    @Bean
    public Consumer<ShippingEvent> shippingEventConsumer(){
    return shippingConsumerService::consumeShippingEvent;
}

}