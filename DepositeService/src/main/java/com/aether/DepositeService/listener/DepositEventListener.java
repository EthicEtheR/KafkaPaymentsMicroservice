package com.aether.DepositeService.listener;

import com.aether.core.events.DepositReqEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@KafkaListener(topics = "deposit-money-topic")
@Component
@Slf4j
public class DepositEventListener {

    @KafkaHandler
    public void depositEvent(@Payload DepositReqEvent depositReqEvent){
        log.info("Received senderId :{}",depositReqEvent.getSenderId());
    }
}
