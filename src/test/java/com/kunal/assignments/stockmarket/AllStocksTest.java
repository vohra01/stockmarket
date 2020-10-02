package com.kunal.assignments.stockmarket;

import com.kunal.assignments.stockmarket.enums.StockType;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class AllStocksTest {

    @Test
    public void should_assert_all_share_index_in_list() {
        //Given
        HashMap<String, Stock> db = new HashMap<String, Stock>();
        db.put("ABC", new Stock("ABC", StockType.COMMON, 23.0, 0.0, 60.0));
        db.put("TEA", new Stock("TEA", StockType.COMMON, 0.0, 0.0, 100.0));
        db.put("POP", new Stock("POP", StockType.COMMON, 8.0, 0.0, 100.0));
        db.put("GIN", new Stock("GIN", StockType.PREFERRED, 8.0, 0.2, 100.0));
        db.put("JOE", new Stock("JOE", StockType.COMMON, 13.0, 0.0, 250.0));
        for (Stock stock : db.values()) {
            // Recording trades
            for (int i = 1; i <= 10; i++) {
                stock.buy(1, 1.0);
                stock.sell(1, 1.0);
            }
        }

        //When
        Double allShareIndex = AllStocks.allShareIndex(db);

        //Then
        assertEquals(1.379729661461215, allShareIndex, 0.0);
    }

}
