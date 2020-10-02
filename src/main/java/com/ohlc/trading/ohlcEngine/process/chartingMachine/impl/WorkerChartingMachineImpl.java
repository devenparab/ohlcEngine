package com.ohlc.trading.ohlcEngine.process.chartingMachine.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ohlc.trading.ohlcEngine.common.CommonConstants;
import com.ohlc.trading.ohlcEngine.exception.BarChartDataException;
import com.ohlc.trading.ohlcEngine.model.BarChartDataWrapper;
import com.ohlc.trading.ohlcEngine.process.chartingMachine.WorkerChartingMachine;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WorkerChartingMachineImpl implements WorkerChartingMachine {
    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerChartingMachineImpl.class);

    ObjectMapper mapper = new ObjectMapper();

    @Async("workerChartingMachineExecutor")
    @Override
    public void updateBarChart(BarChartDataWrapper barChartDataWrapper) {
        LOGGER.info("### Started WorkerChartingMachineImpl.java >> updateBarChart() >> Time [{}] :: ThreadId [{}] :: ThreadName [{}] ###",
                CommonConstants.sdf.format(new Date()),Thread.currentThread().getId(), Thread.currentThread().getName());
        //LOGGER.info("### Started WorkerChartingMachineImpl.java >> updateBarChart() >> barChartDataWrapper [{}] ###",barChartDataWrapper);
        //LOGGER.info("### Bar Chart :: [{}]  ###", barChartDataWrapper.getBarChartDataList().stream().map(BarChartData::toString).collect(Collectors.joining(" ### ")) );
        if (!barChartDataWrapper.getBarChartDataList().isEmpty()){
            try {
                JSONObject jsonObject = new JSONObject(mapper.writeValueAsString(barChartDataWrapper));
                LOGGER.info("- - - - - - - - - - - - - - - - - - - - - -");
                LOGGER.info("######     Bar Number [{}]     #####",barChartDataWrapper.getBarChartDataList().get(0).getBarNumber());
                LOGGER.info("- - - - - - - - - - - - - - - - - - - - - -");
                LOGGER.info(jsonObject.getJSONArray("barChartDataList").toString(2));
                LOGGER.info("- - - - - - - - - - - - - - - - - - - - - -");
            } catch (JsonProcessingException e) {
                LOGGER.error("### Exception of type [{}] occurred in [{}] >> updateBarChart() ###",e.getClass().getCanonicalName(), this.getClass(),e);
                throw new BarChartDataException("Unable to generate chart.");
            }
        }

    }
}
