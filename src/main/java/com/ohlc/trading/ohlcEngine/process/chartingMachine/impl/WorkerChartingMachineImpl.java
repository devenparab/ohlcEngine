package com.ohlc.trading.ohlcEngine.process.chartingMachine.impl;

import com.ohlc.trading.ohlcEngine.common.CommonConstants;
import com.ohlc.trading.ohlcEngine.model.BarChartData;
import com.ohlc.trading.ohlcEngine.model.BarChartDataWrapper;
import com.ohlc.trading.ohlcEngine.process.chartingMachine.WorkerChartingMachine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Collectors;

@Service
public class WorkerChartingMachineImpl implements WorkerChartingMachine {
    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerChartingMachine.class);

    @Async("workerChartingMachineExecutor")
    @Override
    public void updateBarChart(BarChartDataWrapper barChartDataWrapper) {
        LOGGER.info("### Started WorkerChartingMachineImpl.java >> updateBarChart() >> Time [{}] :: ThreadId [{}] :: ThreadName [{}] ###",
                CommonConstants.sdf.format(new Date()),Thread.currentThread().getId(), Thread.currentThread().getName());
        //LOGGER.info("### Started WorkerChartingMachineImpl.java >> updateBarChart() >> barChartDataWrapper [{}] ###",barChartDataWrapper);
        if (!barChartDataWrapper.getBarChartDataList().isEmpty()){
            LOGGER.info("### Bar Chart :: [{}]  ###", barChartDataWrapper.getBarChartDataList().stream().map(BarChartData::toString).collect(Collectors.joining(" ### ")) );
        }

    }
}
