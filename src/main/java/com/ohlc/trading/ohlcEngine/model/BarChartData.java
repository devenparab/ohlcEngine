package com.ohlc.trading.ohlcEngine.model;

import java.io.Serializable;

public class BarChartData implements Serializable {
    private static final long serialVersionUID = -582879581201678067L;

    private double open;
    private double high;
    private double low;
    private double close;
    private double volume;
    private String symbol;
    private String event;
    private int barNumber;

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public int getBarNumber() {
        return barNumber;
    }

    public void setBarNumber(int barNumber) {
        this.barNumber = barNumber;
    }

    @Override
    public String toString() {
        return "BarChartData{" +
                "open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", close=" + close +
                ", volume=" + volume +
                ", symbol='" + symbol + '\'' +
                ", event='" + event + '\'' +
                ", barNumber=" + barNumber +
                '}';
    }
}
