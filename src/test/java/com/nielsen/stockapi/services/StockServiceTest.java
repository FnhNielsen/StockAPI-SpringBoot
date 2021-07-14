package com.nielsen.stockapi.services;

import com.nielsen.stockapi.models.StockWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StockServiceTest
{
    @Autowired
    private StockService stockService;
    @Test
    void Invoke()
    {
        final StockWrapper stock = stockService.findStock("KO");
        System.out.println(stock.getStock());

        final BigDecimal price = stockService.findPrice(stock);
        System.out.println(price);
    }
}