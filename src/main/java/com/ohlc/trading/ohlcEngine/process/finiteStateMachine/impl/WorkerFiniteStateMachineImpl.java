package com.ohlc.trading.ohlcEngine.process.finiteStateMachine.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ohlc.trading.ohlcEngine.common.CommonConstants;
import com.ohlc.trading.ohlcEngine.model.*;
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
                Trade currentTrade = trades.get(0);
                barChartData.setOpen(currentTrade.getP());
                barChartData.setHigh(currentTrade.getP());
                barChartData.setClose(currentTrade.getP());
                barChartData.setLow(currentTrade.getP());
                barChartData.setEvent("ohlc_notify");
                barChartData.setSymbol(currentTrade.getSym());
                barChartData.setVolume(currentTrade.getQ());
                barChartDataWrapper.getBarChartDataList().add(barChartData);
                queueMessagingService.sendObjMessage(QueueName.CHART_Q, barChartDataWrapper);
            }else {
                /*Trade startingTrade = trades.stream().findFirst().get();
                System.out.println("First Interval: " + startingTrade.gettS2_formattedTime());
                Trade lastTrade = trades.stream().reduce((first, second) -> second).orElse(null);
                System.out.println("Last Interval: " + lastTrade.gettS2_formattedTime());*/
                for (int i = 0; i < trades.size(); i++) {
                    BarChartData barChartData = new BarChartData();
                    Trade currentTrade = trades.get(i);
                    barChartData.setBarNumber(tradesInfo.getBarCounter());
                    barChartData.setSymbol(currentTrade.getSym());
                    if (i == 0){//for starting interval
                        SnapshotTradeWrapper snapshotTradeWrapper = new SnapshotTradeWrapper();
                        snapshotTradeWrapper.setTrade(currentTrade);
                        snapshotTradeWrapper.setVolume(currentTrade.getQ());
                        snapshotTradeWrapper.setCurrentHigh(currentTrade.getP());
                        snapshotTradeWrapper.setCurrentLow(currentTrade.getP());
                        CommonConstants.OPENER_TRADE_SNAPSHOT.put(currentTrade,snapshotTradeWrapper);
                        barChartData.setOpen(currentTrade.getP());
                        barChartData.setHigh(currentTrade.getP());
                        barChartData.setLow(currentTrade.getP());
                        barChartData.setClose(0);
                        barChartData.setEvent("ohlc_notify");
                        barChartDataWrapper.getBarChartDataList().add(barChartData);
                    } else if(i == trades.size()-1){//for last interval
                        SnapshotTradeWrapper snapshotTradeWrapper = CommonConstants.OPENER_TRADE_SNAPSHOT.get(trades.get(0));
                        double updatedVolume = snapshotTradeWrapper.getVolume() + currentTrade.getQ();
                        snapshotTradeWrapper.setVolume(updatedVolume);
                        barChartData.setOpen(snapshotTradeWrapper.getTrade().getP());
                        barChartData.setVolume(snapshotTradeWrapper.getVolume());
                        double latestHigh = currentTrade.getP() > snapshotTradeWrapper.getCurrentHigh() ? currentTrade.getP() : snapshotTradeWrapper.getCurrentHigh();
                        double latestLow = currentTrade.getP() < snapshotTradeWrapper.getCurrentHigh() ? currentTrade.getP() : snapshotTradeWrapper.getCurrentHigh();
                        snapshotTradeWrapper.setCurrentHigh(latestHigh);
                        snapshotTradeWrapper.setCurrentLow(latestLow);
                        barChartData.setHigh(snapshotTradeWrapper.getCurrentHigh());
                        barChartData.setLow(snapshotTradeWrapper.getCurrentLow());
                        barChartData.setClose(currentTrade.getP());
                        barChartData.setEvent("ohlc_notify");
                        barChartDataWrapper.getBarChartDataList().add(barChartData);
                        CommonConstants.OPENER_TRADE_SNAPSHOT.remove(trades.get(0));
                    } else{
                        /*int lastIndex = i;
                        Trade lastTrade = trades.get(--lastIndex);*/
                        SnapshotTradeWrapper snapshotTradeWrapper = CommonConstants.OPENER_TRADE_SNAPSHOT.get(trades.get(0));
                        double updatedVolume = snapshotTradeWrapper.getVolume() + currentTrade.getQ();
                        snapshotTradeWrapper.setVolume(updatedVolume);
                        barChartData.setOpen(snapshotTradeWrapper.getTrade().getP());
                        double latestHigh = currentTrade.getP() > snapshotTradeWrapper.getCurrentHigh() ? currentTrade.getP() : snapshotTradeWrapper.getCurrentHigh();
                        double latestLow = currentTrade.getP() < snapshotTradeWrapper.getCurrentHigh() ? currentTrade.getP() : snapshotTradeWrapper.getCurrentHigh();
                        snapshotTradeWrapper.setCurrentHigh(latestHigh);
                        snapshotTradeWrapper.setCurrentLow(latestLow);
                        barChartData.setHigh(snapshotTradeWrapper.getCurrentHigh());
                        barChartData.setLow(snapshotTradeWrapper.getCurrentLow());
                        barChartData.setClose(0);
                        barChartData.setEvent("ohlc_notify");
                        barChartData.setVolume(snapshotTradeWrapper.getVolume());
                        barChartDataWrapper.getBarChartDataList().add(barChartData);
                    }
                }
            }
            queueMessagingService.sendObjMessage(QueueName.CHART_Q, barChartDataWrapper);
        }else {
            LOGGER.info("### No trade happened ###");
        }
    }
}
