package com.aether.core.events;

import java.math.BigDecimal;

public class WithdrawReqEvent {
    private String senderId;
    private String receiverId;
    private BigDecimal amount;

    public WithdrawReqEvent(String senderId) {

    }

    public WithdrawReqEvent(String senderId, String receiverId, BigDecimal amount) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.amount = amount;
    }

    public String getSenderId() {
        return senderId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
