package com.example.currencyexchangeservice;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CurrencyExchangeController {
    private Logger logger= LoggerFactory.getLogger(CurrencyExchangeController.class);
    private final Environment environment;
    private final CurrencyExchangeRepository currencyExchangeRepository;

    @GetMapping("currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveFromCurrencyExchange(@PathVariable String from, @PathVariable String to) {
        logger.info("retrieveFromCurrencyExchange called with {} to {}", from, to);
        CurrencyExchange currencyExchange=currencyExchangeRepository.findByFromAndTo(from, to);
        if(currencyExchange==null) {
            throw new RuntimeException("Unable to find currency exchange from " + from + " to " + to);
        }
        currencyExchange.setEnvironment(environment.getProperty("server.port"));
        return currencyExchange;
    }
}
