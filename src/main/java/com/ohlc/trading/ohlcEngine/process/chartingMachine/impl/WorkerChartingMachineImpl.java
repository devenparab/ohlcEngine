package com.ohlc.trading.ohlcEngine.process.chartingMachine.impl;

import com.ohlc.trading.ohlcEngine.process.chartingMachine.WorkerChartingMachine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class WorkerChartingMachineImpl implements WorkerChartingMachine {
    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerChartingMachine.class);

    @Override
    public void updateBarChart() {

    }
}
