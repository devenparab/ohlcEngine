package com.ohlc.trading.ohlcEngine.queue;

import com.ohlc.trading.ohlcEngine.common.CommonConstants;
import com.ohlc.trading.ohlcEngine.exception.TradeInfoException;
import com.ohlc.trading.ohlcEngine.model.Trades;
import com.ohlc.trading.ohlcEngine.process.finiteStateMachine.WorkerFiniteStateMachine;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class QueueListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(QueueListener.class);

    @Autowired
    WorkerFiniteStateMachine workerFiniteStateMachine;

    @JmsListener(destination = CommonConstants.FSM_Q, containerFactory = "queueListenerFactory")
    public void receiveTradeData(@Payload Trades trades, @Headers Map<String, Object> headers){
        //LOGGER.info("### QueueListener.java >> receiveTradeData() >> jms-message-type [{}] ###",headers.get("jms-message-type"));
        LOGGER.info("received <" + trades + ">");
        if (StringUtils.isEmpty(trades.getSym())){
            LOGGER.error("### receiveTradeData() trade name cannot be Empty ###");
            throw new TradeInfoException("Trade Info Corrupt");
        }else{
            LOGGER.info("### receiveTradeData() trade name cannot be Empty ###");
            workerFiniteStateMachine.computeFiniteStateMachine(trades);
        }
    }
}
