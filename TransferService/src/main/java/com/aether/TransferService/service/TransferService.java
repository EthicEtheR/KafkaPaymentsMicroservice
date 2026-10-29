package com.aether.TransferService.service;

import com.aether.TransferService.model.TransferEventEntity;

public interface TransferService {

    String transfer(TransferEventEntity eventEntity);
}
