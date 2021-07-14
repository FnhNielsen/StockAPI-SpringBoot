package com.nielsen.stockapi.controllers;

import com.nielsen.stockapi.models.StockWrapper;
import com.nielsen.stockapi.services.StockService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import yahoofinance.YahooFinance;

import java.util.List;

@RestController
public class StocksController
{
    private final StockService stockService;

    @Autowired
    public StocksController(StockService stockService)
    {
        this.stockService = stockService;
    }

    @SneakyThrows
    @GetMapping("/stocks/{stockTicker}")
    public StockWrapper getStock(String stockTicker)
    {
        return null;
    }
}
