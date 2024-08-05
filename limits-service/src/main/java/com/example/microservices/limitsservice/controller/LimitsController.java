package com.example.microservices.limitsservice.controller;

import com.example.microservices.limitsservice.bean.Limits;
import com.example.microservices.limitsservice.configuration.Configuration;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LimitsController {
    private final Configuration configuration;

    @GetMapping("/limits")
    public Limits retrieveLimits() {
        return new Limits(configuration.getMinimum(),configuration.getMaximum());
    }
}
