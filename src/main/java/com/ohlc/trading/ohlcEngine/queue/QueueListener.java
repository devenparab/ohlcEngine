package com.ohlc.trading.ohlcEngine.queue;

import com.ohlc.trading.ohlcEngine.common.CommonConstants;
import com.ohlc.trading.ohlcEngine.exception.BarChartDataException;
import com.ohlc.trading.ohlcEngine.exception.TradeInfoException;
import com.ohlc.trading.ohlcEngine.model.BarChartDataWrapper;
import com.ohlc.trading.ohlcEngine.model.TradesInfo;
import com.ohlc.trading.ohlcEngine.process.chartingMachine.WorkerChartingMachine;
import com.ohlc.trading.ohlcEngine.process.finiteStateMachine.WorkerFiniteStateMachine;
import com.ohlc.trading.ohlcEngine.queue.types.QueueName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Autowired
    WorkerChartingMachine workerChartingMachine;

    @JmsListener(destination = QueueName.FSM_Q, containerFactory = "queueListenerFactory")
    public void receiveTradeData(@Payload TradesInfo tradesInfo, @Headers Map<String, Object> headers){
        LOGGER.info("received <" + tradesInfo + ">");
        if (tradesInfo.getTrades().size() < 0){
            LOGGER.error("### receiveTradeData() trades are Empty ###");
            throw new TradeInfoException("Trade Info Corrupt");
        }else{
            workerFiniteStateMachine.computeFiniteStateMachine(tradesInfo);
        }
    }

    @JmsListener(destination = QueueName.CHART_Q, containerFactory = "queueListenerFactory")
    public void receiveBarChartData(@Payload BarChartDataWrapper barChartDataWrapper, @Headers Map<String, Object> headers){
        LOGGER.info("received <" + barChartDataWrapper + ">");
        if (barChartDataWrapper.getBarChartDataList().size() < 0){
            LOGGER.error("### receiveBarChartData() bar chart data are Empty ###");
            //throw new BarChartDataException("Trade Info Corrupt");
        }else{
            workerChartingMachine.updateBarChart(barChartDataWrapper);
        }
    }
}
