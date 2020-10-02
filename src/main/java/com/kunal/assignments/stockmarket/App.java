package com.kunal.assignments.stockmarket;

import com.kunal.assignments.stockmarket.enums.StockType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Configuration
@ComponentScan
public class App {
    private static Log log = LogFactory.getLog(App.class);

    public static void main(String[] args) {
        try {
            ApplicationContext context =
                    new AnnotationConfigApplicationContext(App.class);

            // Run dividend and P/E Ratio routines
            @SuppressWarnings("unchecked")
            Map<String, Stock> db = context.getBean("Db", Map.class);
            for (Stock stock : db.values()) {
                System.out.println("Stock " + stock.getSymbol() + " dividend: " + stock.dividend(9.1));
                System.out.println("Stock " + stock.getSymbol() + " PriceEArning Ratio: " + stock.PERatio(9.1));
                // Record some trades and log in console
                for (int i = 1; i <= 10; i++) {
                    Random random = new Random();
                    Integer minimumRange = 1;
                    Integer maximumRange = 10;
                    Double randomValue = minimumRange + (maximumRange - minimumRange) * random.nextDouble();
                    stock.buy(i, randomValue);
                    System.out.println("Stock " + stock.getSymbol() + " bought " + i + " shares at $" + randomValue);
                    randomValue = minimumRange + (maximumRange - minimumRange) * random.nextDouble();
                    stock.sell(i, randomValue);
                    System.out.println("Stock " + stock.getSymbol() + " sold " + i + " shares at $" + randomValue);
                    Thread.sleep(1000);
                }
                System.out.println("Stock " + stock.getSymbol() + " price: $" + stock.getPrice());
                System.out.println("Stock " + stock.getSymbol() + " volumeWeightedStockPrice: $" + stock.calculateVolumeWithWeightOfStockPrice());
            }
            Double allShareIndex = AllStocks.allShareIndex(db);
            System.out.println("AllStocks All Share Index: " + allShareIndex);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Bean
    Map<String, Stock> Db() {
        HashMap<String, Stock> db = new HashMap<String, Stock>();
        db.put("ABC", new Stock("ABC", StockType.COMMON, 23.0, 0.0, 60.0));
        db.put("TEA", new Stock("TEA", StockType.COMMON, 0.0, 0.0, 100.0));
        db.put("POP", new Stock("POP", StockType.COMMON, 8.0, 0.0, 100.0));
        db.put("GIN", new Stock("GIN", StockType.PREFERRED, 8.0, 0.2, 100.0));
        db.put("JOE", new Stock("JOE", StockType.COMMON, 13.0, 0.0, 250.0));
        return db;
    }
}
