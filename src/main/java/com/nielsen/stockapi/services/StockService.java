package com.nielsen.stockapi.services;

import com.nielsen.stockapi.models.StockWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class StockService
{
    private final RefreshService refreshService;
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

    public List<StockWrapper>getStocks(final List<String> ticker)
    {
      return ticker.stream().map(this::findStock).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public BigDecimal findPrice(final StockWrapper stock)
    {
        try
        {
            return stock.getStock().getQuote(refreshService.getRefreshedStock(stock)).getPrice();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    // This method gets latest percentage of price change
    public BigDecimal getLastChangePercent(final StockWrapper stock)
    {
        try
        {
            return stock.getStock().getQuote(refreshService.getRefreshedStock(stock)).getChangeInPercent();
        } catch (IOException e)
        {
            System.out.println("Error");
            e.printStackTrace();
        }
        return null;
    }

    public BigDecimal getChange200MeanPercent (final StockWrapper stock)
    {
        try
        {
           return stock.getStock().getQuote(refreshService.getRefreshedStock(stock)).getChangeFromAvg200InPercent();
        } catch (Exception e)
        {
            System.out.println("Error");
            e.printStackTrace();
        }
        return null;
    }
}
