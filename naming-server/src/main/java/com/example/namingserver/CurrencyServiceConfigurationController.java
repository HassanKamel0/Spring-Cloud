package com.example.namingserver;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("naming-server")
@RequiredArgsConstructor
public class CurrencyServiceConfigurationController {
    private final CurrencyServiceConfiguration currencyServiceConfiguration;

    @GetMapping("currency-configuration")
    public CurrencyServiceConfiguration retrieveCurrencyExchange() {
        return currencyServiceConfiguration;
    }
}
