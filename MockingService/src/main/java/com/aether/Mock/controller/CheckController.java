package com.aether.Mock.controller;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/check")
public class CheckController {

    @GetMapping("/200")
    public ResponseEntity<String> goodResponse(){
        return ResponseEntity.ok("External service is up and running");
    }
    @GetMapping("/500")
    public ResponseEntity<String> badResponse(){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("External service is off");
    }
}
