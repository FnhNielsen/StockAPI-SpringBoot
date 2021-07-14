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
    private final Stock stock;
    private final LocalDateTime lastRefresh;


    public StockWrapper(final Stock stock)
    {
        this.stock = stock;
        lastRefresh = LocalDateTime.now();
    }

    public Stock getStock()
    {
        return stock;
    }

    public LocalDateTime getLastRefresh()
    {
        return lastRefresh;
    }
}
