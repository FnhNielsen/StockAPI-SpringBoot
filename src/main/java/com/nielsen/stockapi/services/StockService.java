package com.nielsen.stockapi.services;

import com.nielsen.stockapi.models.StockWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;

@AllArgsConstructor
@Service
public class StockService
{
    public StockWrapper findStock(String ticker)
    {
        try
        {
            return new StockWrapper(YahooFinance.get(ticker));
        } catch (Exception e)
        {
            System.out.println("Error");
            e.printStackTrace();
        }
        return null;
    }
    public BigDecimal findPrice(final StockWrapper stock)
    {
        try
        {
            return stock.getStock().getQuote(true).getPrice();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
