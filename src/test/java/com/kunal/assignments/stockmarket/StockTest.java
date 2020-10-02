package com.kunal.assignments.stockmarket;

import com.kunal.assignments.stockmarket.enums.StockType;
import com.kunal.assignments.stockmarket.enums.TradeType;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class StockTest {

    @Test
    public void should_assert_divident_of_stock_for_provided_stock() {
        //Given
        Stock stockABC = new Stock("ABC", StockType.COMMON, 23.0, 0.0, 60.0);
        Stock stockGIN = new Stock("GIN", StockType.PREFERRED, 8.0, 0.2, 100.0);

        //When
        Double dividendABC = stockABC.dividend(1.0);
        Double expectedDividendABC = stockABC.getLastDividend() / 1.0;
        Double dividendGIN = stockGIN.dividend(1.0);
        Double expectedDividendGIN = stockGIN.getFixedDividend() * stockGIN.getParValue() / 1.0;

        //Then
        assertEquals(expectedDividendABC, dividendABC, 0.0);
        assertEquals(expectedDividendGIN, dividendGIN, 0.0);
    }

    @Test
    public void should_match_expected_price_earninng_ratio() {

        //Given
        Stock stockABC = new Stock("ABC", StockType.COMMON, 23.0, 0.0, 60.0);

        //When
        Double peRatioABC = stockABC.PERatio(1.0);
        Double expectedPeRatioABC = 1.0 / stockABC.getLastDividend();

        //Then
        assertEquals(expectedPeRatioABC, peRatioABC, 0.0);
    }

    @Test
    public void should_match_buying_price_of_stock_ABC() {
        //Given
        Stock stockABC = new Stock("ABC", StockType.COMMON, 23.0, 0.0, 60.0);

        //When
        stockABC.buy(1, 10.0);

        //Then
        assertEquals(10.0, stockABC.getPrice(), 0.0);
    }

    @Test
    public void should_assert_selling_of_stock_ABC() {

        //Given
        Stock stockABC = new Stock("ABC", StockType.COMMON, 23.0, 0.0, 60.0);

        //When
        stockABC.sell(1, 10.0);

        //Then
        assertEquals(10.0, stockABC.getPrice(), 0.0);
    }

    @Test
    public void should_get_current_value_of_stock_when_stock_ABC() {

        //Given
        Stock stockABC = new Stock("ABC", StockType.COMMON, 23.0, 0.0, 60.0);

        //When
        stockABC.sell(1, 10.0);
        stockABC.buy(1, 11.0);

        //Then
        assertEquals(11.0, stockABC.getPrice(), 0.0);
    }

    @Test
    public void testCalculateVolumeWeightedStockPrice() {

        //Given
        Stock stockABC = new Stock("ABC", StockType.COMMON, 23.0, 0.0, 60.0);
        stockABC.sell(2, 10.0);
        stockABC.buy(2, 10.0);

        //When
        Double volumeWeightedStockPrice = stockABC.calculateVolumeWithWeightOfStockPrice();

        //Then
        assertEquals(10.0, volumeWeightedStockPrice, 0.0);


        //Given
        Date now = new Date();
        // Date 20 minutes ago
        Date startTime = new Date(now.getTime() - (20 * 60 * 1000));
        stockABC.getTrades().put(startTime, new Trade(TradeType.BUY, 1, 20.0));
        // The new (outdated) trade should not affect calculation of the Volume Weighted Stock Price

        //When
        volumeWeightedStockPrice = stockABC.calculateVolumeWithWeightOfStockPrice();

        //Then
        assertEquals(10.0, volumeWeightedStockPrice, 0.0);
    }
}
