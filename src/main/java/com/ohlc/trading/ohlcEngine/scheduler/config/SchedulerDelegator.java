package com.ohlc.trading.ohlcEngine.scheduler.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class SchedulerDelegator {
    private static final Logger LOGGER = LoggerFactory.getLogger(SchedulerDelegator.class);

    @Autowired
    WorkerProcess workerProcess;

    private final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedDelayString = "${readTrade.fixed.delay}000", initialDelayString = "${readTrade.initial.delay}000")
    public void workerOne(){
        LOGGER.info("### Started SchedulerConfig.java >> workerOne() >> The time is now [{}] and ThreadId [{}] ###", sdf.format(new Date()),Thread.currentThread().getId());
        workerProcess.readTradeData();
        LOGGER.info("### Finished SchedulerConfig.java >> workerOne() >> The time is now [{}] and ThreadId [{}] ###", sdf.format(new Date()),Thread.currentThread().getId());
    }

    @Scheduled(fixedDelayString = "${finiteStateMachine.fixed.delay}000", initialDelayString = "${finiteStateMachine.initial.delay}000")
    public void workerTwo(){
        LOGGER.info("### Started SchedulerConfig.java >> finiteStateMachine() >> The time is now [{}] and ThreadId [{}] ###", sdf.format(new Date()),Thread.currentThread().getId());
        workerProcess.finiteStateMachine();
        LOGGER.info("### Finished SchedulerConfig.java >> finiteStateMachine() >> The time is now [{}] and ThreadId [{}] ###", sdf.format(new Date()),Thread.currentThread().getId());
    }

    @Scheduled(fixedDelayString = "${emitter.fixed.delay}000", initialDelayString = "${emitter.initial.delay}000")
    public void workerThree(){
        LOGGER.info("### Started SchedulerConfig.java >> emitter() >> The time is now [{}] :: ThreadId [{}] :: ThreadName[{}] ###", sdf.format(new Date()),Thread.currentThread().getId(),Thread.currentThread().getName());
        workerProcess.emitter();
        LOGGER.info("### Finished SchedulerConfig.java >> emitter() >> The time is now [{}] and ThreadId [{}] ###", sdf.format(new Date()),Thread.currentThread().getId());
    }

}
