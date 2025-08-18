package com.assesment.spring.data.mongodb.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sample")
public class SampleController {

    @GetMapping
    public ResponseEntity<String> getSample() {
        return ResponseEntity.ok("Sample api");
    }
}

