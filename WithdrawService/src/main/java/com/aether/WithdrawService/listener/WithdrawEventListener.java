package com.aether.WithdrawService.listener;

import com.aether.core.events.WithdrawReqEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@KafkaListener(topics = "withdraw-money-topic")
public class WithdrawEventListener {

    @KafkaHandler
    public void withdrawing(@Payload  WithdrawReqEvent event) {
        log.info("Received receiverId: {}", event.getReceiverId());
    }
}
