package com.aether.core.events;

import java.math.BigDecimal;

public class DepositReqEvent {
    private String senderId;
    private String receiverId;
    private BigDecimal amount;

    public DepositReqEvent() {
    }

    public DepositReqEvent(String senderId, String receiverId, BigDecimal amount) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.amount = amount;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
