package com.aether.TransferService.controller;

import com.aether.TransferService.model.TransferEventEntity;
import com.aether.TransferService.service.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfer")
public class TransferController {
    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public ResponseEntity<String> transferRequest(@RequestBody TransferEventEntity eventEntity){

        return ResponseEntity.ok( transferService.transfer(eventEntity));
    }
}
