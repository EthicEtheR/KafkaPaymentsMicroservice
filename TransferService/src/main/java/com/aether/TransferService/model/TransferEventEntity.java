package com.aether.TransferService.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransferEventEntity {
    private String senderId;
    private String receiverId;
    private BigDecimal amount;

}
