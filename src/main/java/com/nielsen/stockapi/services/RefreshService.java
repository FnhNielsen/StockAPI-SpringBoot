package com.nielsen.stockapi.services;

import com.nielsen.stockapi.models.StockWrapper;
import org.springframework.stereotype.Service;
import yahoofinance.YahooFinance;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.SECONDS;

@Service
public class RefreshService
{
    private static final ScheduledExecutorService scheduler
            = Executors.newScheduledThreadPool(1);
    private final Map<StockWrapper, Boolean> refreshStocks;
    private static final Duration refreshPeriod = Duration.ofSeconds(15);

    public RefreshService()
    {
        refreshStocks = new HashMap<>();
        setRefreshQuaterHour();
    }

    public boolean getRefreshedStock(final StockWrapper stock)
    {
        if (!refreshStocks.containsKey(stock))
        {
            refreshStocks.put(stock,true);
            return true;
        }
        return refreshStocks.get(stock);
    }
    // This method refreshes every 15 minutes
    private void setRefreshQuaterHour()
    {
        scheduler.scheduleAtFixedRate(() ->
                refreshStocks.forEach((stockWrapper, value) -> {
            if (stockWrapper.getLastRefresh().isBefore(LocalDateTime.now().minus(refreshPeriod)))
            {
                System.out.println(stockWrapper.getStock().getSymbol());
                refreshStocks.remove(stockWrapper);
                refreshStocks.put(stockWrapper.withLastRefresh(LocalDateTime.now()), true);
            }
        }),0,15,SECONDS );
    }
}
