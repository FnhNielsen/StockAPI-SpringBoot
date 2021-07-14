package com.nielsen.stockapi.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.With;
import yahoofinance.Stock;

import java.time.LocalDateTime;

@AllArgsConstructor
@With
public class StockWrapper
{
    private final yahoofinance.Stock stock;
    private final LocalDateTime lastRefresh;


    public StockWrapper(final yahoofinance.Stock stock)
    {
        this.stock = stock;
        lastRefresh = LocalDateTime.now();
    }

    public yahoofinance.Stock getStock()
    {
        return stock;
    }

    public LocalDateTime getLastRefresh()
    {
        return lastRefresh;
    }
}
