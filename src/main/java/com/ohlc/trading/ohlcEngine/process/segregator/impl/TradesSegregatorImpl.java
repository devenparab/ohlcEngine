package com.ohlc.trading.ohlcEngine.process.segregator.impl;

import com.ohlc.trading.ohlcEngine.common.CommonConstants;
import com.ohlc.trading.ohlcEngine.model.SegregatorState;
import com.ohlc.trading.ohlcEngine.model.Trade;
import com.ohlc.trading.ohlcEngine.model.TradesInfo;
import com.ohlc.trading.ohlcEngine.process.segregator.TradesSegregator;
import com.ohlc.trading.ohlcEngine.queue.QueueMessagingService;
import com.ohlc.trading.ohlcEngine.queue.types.QueueName;
import com.ohlc.trading.ohlcEngine.states.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TradesSegregatorImpl implements TradesSegregator {
    private static final Logger LOGGER = LoggerFactory.getLogger(TradesSegregator.class);

    @Autowired
    QueueMessagingService queueMessagingService;

    TradesInfo tradesInfo;

    private final AtomicInteger counter = new AtomicInteger(0);

    @Override
    public void sliceTradesBasedOnInterval(Trade trade, long intervalSecs) {
        if(CommonConstants.SEGREGATOR_INFO_MAP.isEmpty()){
            tradesInfo = new TradesInfo();
            SegregatorState segregatorState = new SegregatorState();
            setStateInfo(trade, intervalSecs, segregatorState);
        }else{
            SegregatorState segregatorState = CommonConstants.SEGREGATOR_INFO_MAP.get(CommonConstants.CURRENT_ACTIVE_SLOT);
            //LOGGER.info("### segregatorState [{}] ###",segregatorState);
            if (segregatorState.getState().equals(State.ACTIVE)){
                if (trade.gettS2LocalDateTime().isBefore(segregatorState.getEndInterval())){
                    tradesInfo.getTrades().add(trade);
                }else {
                    //send this slot to FSM Queue and proceed ahead
                    queueMessagingService.sendObjMessage(QueueName.FSM_Q, tradesInfo);
                    segregatorState = reInitialize();
                    setStateInfo(trade, intervalSecs, segregatorState);
                }
            }
        }
    }

    private SegregatorState reInitialize() {
        SegregatorState segregatorState;
        CommonConstants.SEGREGATOR_INFO_MAP.clear();
        tradesInfo = new TradesInfo();
        segregatorState = new SegregatorState();
        return segregatorState;
    }

    private void setStateInfo(Trade trade, long intervalSecs, SegregatorState segregatorState) {
        segregatorState.setBarCounter(counter.addAndGet(1));
        segregatorState.setState(State.ACTIVE);
        segregatorState.setStartInterval(trade.gettS2LocalDateTime()/*LocalDateTime.parse(trade.gettS2_formattedTime())*/);
        segregatorState.setEndInterval(trade.gettS2LocalDateTime().plusSeconds(intervalSecs)/*LocalDateTime.parse(trade.gettS2_formattedTime()).plusSeconds(intervalSecs)*/);
        segregatorState.setInterval(intervalSecs);
        CommonConstants.SEGREGATOR_INFO_MAP.put(CommonConstants.CURRENT_ACTIVE_SLOT, segregatorState);
        tradesInfo.setBarCounter(segregatorState.getBarCounter());
        tradesInfo.getTrades().add(trade);
    }
}
