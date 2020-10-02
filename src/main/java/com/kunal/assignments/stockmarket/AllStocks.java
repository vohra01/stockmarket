package com.kunal.assignments.stockmarket;

import java.util.Map;

public class AllStocks {

    public static Double allShareIndex(Map<String, Stock> stocks) {
        Double allShareIndex = 0.0;
        for (Stock stock : stocks.values()) {
            allShareIndex += stock.getPrice();
        }
        return Math.pow(allShareIndex, 1.0 / stocks.size());
    }

}
