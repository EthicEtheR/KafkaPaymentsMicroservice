package com.aether.WithdrawService.listener;

import com.aether.core.events.WithdrawReqEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@KafkaListener(topics = "withdraw-money-topic")
@Slf4j
@Component
public class WithdrawEventListener {

    @KafkaHandler
    void withdrawing(@Payload WithdrawReqEvent withdrawReqEvent){

        log.info("Received receiverId :{}",withdrawReqEvent.getReceiverId());

    }
}
