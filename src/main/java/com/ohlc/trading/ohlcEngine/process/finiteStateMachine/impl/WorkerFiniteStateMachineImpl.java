package com.ohlc.trading.ohlcEngine.process.finiteStateMachine.impl;

import com.ohlc.trading.ohlcEngine.common.CommonConstants;
import com.ohlc.trading.ohlcEngine.model.BarChartData;
import com.ohlc.trading.ohlcEngine.model.BarChartDataWrapper;
import com.ohlc.trading.ohlcEngine.model.Trade;
import com.ohlc.trading.ohlcEngine.model.TradesInfo;
import com.ohlc.trading.ohlcEngine.process.finiteStateMachine.WorkerFiniteStateMachine;
import com.ohlc.trading.ohlcEngine.queue.QueueMessagingService;
import com.ohlc.trading.ohlcEngine.queue.types.QueueName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkerFiniteStateMachineImpl implements WorkerFiniteStateMachine {
    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerFiniteStateMachineImpl.class);

    @Autowired
    QueueMessagingService queueMessagingService;

    @Async("workerFiniteStateMachineExecutor")
    @Override
    public void computeFiniteStateMachine(TradesInfo tradesInfo) {
        LOGGER.info("### Started WorkerFiniteStateMachineImpl.java >> computeFiniteStateMachine() >> The time is now [{}] :: ThreadId [{}] :: ThreadName [{}] ###",
                CommonConstants.sdf.format(new Date()),Thread.currentThread().getId(), Thread.currentThread().getName());
          /* LOGGER.info("### Bar Counter :: [{}] and Trades [{}] ###",
                    tradesInfo.getBarCounter(), tradesInfo.getTrades().stream().map(Trade::toString).collect(Collectors.joining(" ### ")) );*/
        BarChartDataWrapper barChartDataWrapper = new BarChartDataWrapper();
        List<Trade> trades = tradesInfo.getTrades();
        LOGGER.info("### Bar Counter :: [{}] and Trades [{}] ###",
                tradesInfo.getBarCounter(), trades.stream().map(Trade::toString).collect(Collectors.joining(" ### ")) );
        if (!trades.isEmpty()){
            if (trades.size() == 1){
                BarChartData barChartData = new BarChartData();
                barChartData.setBarNumber(tradesInfo.getBarCounter());
                barChartData.setOpen(trades.get(0).getP());
                barChartData.setHigh(trades.get(0).getP());
                barChartData.setClose(trades.get(0).getP());
                barChartData.setLow(trades.get(0).getP());
                barChartData.setEvent("ohlc_notify");
                barChartData.setSymbol(trades.get(0).getSym());
                barChartData.setVolume(trades.get(0).getQ());
                barChartDataWrapper.getBarChartDataList().add(barChartData);
                queueMessagingService.sendObjMessage(QueueName.CHART_Q, barChartDataWrapper);
            }else {

            }
            //queueMessagingService.sendObjMessage(QueueName.CHART_Q, barChartDataWrapper);
        }else {
            LOGGER.info("### No trade happened ###");
        }
    }
}
