package com.ohlc.trading.ohlcEngine.model;

public class SnapshotTradeWrapper {
    private Trade trade;
    private double volume;
    private double currentHigh;
    private double currentLow;

    public Trade getTrade() {
        return trade;
    }

    public void setTrade(Trade trade) {
        this.trade = trade;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getCurrentHigh() {
        return currentHigh;
    }

    public void setCurrentHigh(double currentHigh) {
        this.currentHigh = currentHigh;
    }

    public double getCurrentLow() {
        return currentLow;
    }

    public void setCurrentLow(double currentLow) {
        this.currentLow = currentLow;
    }
}
