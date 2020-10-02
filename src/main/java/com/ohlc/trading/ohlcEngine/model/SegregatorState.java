package com.ohlc.trading.ohlcEngine.model;

import com.ohlc.trading.ohlcEngine.states.State;

import java.time.LocalDateTime;

public class SegregatorState {

    private int barCounter;
    private State state;
    private LocalDateTime startInterval;
    private LocalDateTime endInterval;
    private long interval;

    public int getBarCounter() {
        return barCounter;
    }

    public void setBarCounter(int barCounter) {
        this.barCounter = barCounter;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public LocalDateTime getStartInterval() {
        return startInterval;
    }

    public void setStartInterval(LocalDateTime startInterval) {
        this.startInterval = startInterval;
    }

    public LocalDateTime getEndInterval() {
        return endInterval;
    }

    public void setEndInterval(LocalDateTime endInterval) {
        this.endInterval = endInterval;
    }

    public long getInterval() {
        return interval;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    @Override
    public String toString() {
        return "SegregatorState{" +
                "barCounter=" + barCounter +
                ", states=" + state +
                ", startInterval=" + startInterval +
                ", endInterval=" + endInterval +
                ", interval=" + interval +
                '}';
    }
}
