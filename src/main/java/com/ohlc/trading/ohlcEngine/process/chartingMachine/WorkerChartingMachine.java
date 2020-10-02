package com.ohlc.trading.ohlcEngine.process.chartingMachine;

import com.ohlc.trading.ohlcEngine.model.BarChartDataWrapper;

public interface WorkerChartingMachine {
    void updateBarChart(BarChartDataWrapper barChartDataWrapper);
}
