package com.nielsen.stockapi.services;

import com.nielsen.stockapi.models.StockWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class StockWrapperServiceTest
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

        final BigDecimal change = stockService.getLastChangePercent(stock);
        System.out.println(change);

        final BigDecimal changeIn200 = stockService.getChange200MeanPercent(stock);
        System.out.println(changeIn200);
    }
    @Test
    void multipleStocks() throws IOException, InterruptedException
    {
        final List<StockWrapper> stocks = stockService.getStocks(Arrays.asList("VZ","RIO"));
        findPrices(stocks);
        Thread.sleep(16000);
        final StockWrapper stock = stockService.findStock("AAPL");
        stocks.add(stock);

        System.out.println(stockService.findPrice(stock));
        findPrices(stocks);
    }
    private void findPrices(List<StockWrapper> stocks)
    {
        stocks.forEach(stock -> {
            try
            {
                System.out.println(stockService.findPrice(stock));
            } catch (Exception e)
            {
                e.printStackTrace();
            }

        });
    }
}