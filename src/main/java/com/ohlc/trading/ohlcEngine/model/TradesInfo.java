package com.ohlc.trading.ohlcEngine.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class TradesInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Trade> trades = new LinkedList<>();
    private int barCounter;

    public List<Trade> getTrades() {
        return trades;
    }

    public void setTrades(List<Trade> trades) {
        this.trades = trades;
    }

    public int getBarCounter() {
        return barCounter;
    }

    public void setBarCounter(int barCounter) {
        this.barCounter = barCounter;
    }

    @Override
    public String toString() {
        return "TradesInfo{" +
                "trades=" + trades +
                ", barCounter=" + barCounter +
                '}';
    }
}
