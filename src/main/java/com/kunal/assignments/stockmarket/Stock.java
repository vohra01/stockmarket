package com.kunal.assignments.stockmarket;

import com.kunal.assignments.stockmarket.enums.StockType;
import com.kunal.assignments.stockmarket.enums.TradeType;

import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

public class Stock {

    private String symbol;
    private StockType type;
    private Double lastDividend;
    private Double fixedDividend;
    private Double parValue;
    private TreeMap<Date, Trade> trades;

    public Stock(String symbol, StockType type, Double lastDividend, Double fixedDividend, Double parValue) {
        this.setSymbol(symbol);
        this.setType(type);
        this.setLastDividend(lastDividend);
        this.setFixedDividend(fixedDividend);
        this.setParValue(parValue);
        this.trades = new TreeMap<Date, Trade>();
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public StockType getType() {
        return type;
    }

    public void setType(StockType type) {
        this.type = type;
    }

    public Double getLastDividend() {
        return lastDividend;
    }

    public void setLastDividend(Double lastDividend) {
        this.lastDividend = lastDividend;
    }

    public Double getFixedDividend() {
        return fixedDividend;
    }

    public void setFixedDividend(Double fixedDividend) {
        this.fixedDividend = fixedDividend;
    }

    public Double getParValue() {
        return parValue;
    }

    public void setParValue(Double parValue) {
        this.parValue = parValue;
    }

    public TreeMap<Date, Trade> getTrades() {
        return trades;
    }


    @Override
    public String toString() {
        return "Stock [symbol=" + symbol + ", type=" + type + ", lastDividend="
                + lastDividend + ", fixedDividend=" + fixedDividend
                + ", parValue=" + parValue + "]";
    }

    Double dividend(Double price) {
        switch (this.getType()) {
            case COMMON:
                return this.getLastDividend() / price;
            case PREFERRED:
                return this.getFixedDividend() * this.getParValue() / price;
            default:
                return 0.0;
        }
    }

    Double PERatio(Double price) {
        return price / this.getLastDividend();
    }

    void buy(Integer quantity, Double price) {
        Trade trade = new Trade(TradeType.BUY, quantity, price);
        this.trades.put(new Date(), trade);
    }

    void sell(Integer quantity, Double price) {
        Trade trade = new Trade(TradeType.SELL, quantity, price);
        this.trades.put(new Date(), trade);
    }

    Double getPrice() {
        if (this.trades.size() < 0) {
            //Assuming No trade as price 0
            return 0.0;
        }

        // Uses the last trade price as price
        return this.trades.lastEntry().getValue().getPrice();
    }

    Double calculateVolumeWithWeightOfStockPrice() {
        Date now = new Date();
        // Date 15 minutes ago
        Date startTime = new Date(now.getTime() - (15 * 60 * 1000));
        // Get trades for the last 15 minutes
        SortedMap<Date, Trade> trades = this.trades.tailMap(startTime);
        // Calculate
        Double totalStockPrice = 0.0;
        Integer totalQuantity = 0;
        for (Trade trade : trades.values()) {
            totalQuantity += trade.getQuantity();
            totalStockPrice += trade.getPrice() * trade.getQuantity();
        }
        return totalStockPrice / totalQuantity;
    }
}
