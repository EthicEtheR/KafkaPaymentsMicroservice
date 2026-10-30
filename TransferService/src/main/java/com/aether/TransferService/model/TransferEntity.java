package com.aether.TransferService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferEntity {

    @Id
    private String transferId;
    @Column(nullable = false)
    private String senderId;
    @Column(nullable = false)
    private String receiverId;
    @Column(nullable = false)
    private BigDecimal amount;

}
