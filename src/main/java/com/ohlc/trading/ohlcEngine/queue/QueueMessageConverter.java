package com.ohlc.trading.ohlcEngine.queue;

import com.ohlc.trading.ohlcEngine.model.BarChartDataWrapper;
import com.ohlc.trading.ohlcEngine.model.Trade;
import com.ohlc.trading.ohlcEngine.model.TradesInfo;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import java.io.Serializable;

@Component
public class QueueMessageConverter implements MessageConverter {

    @Override
    public Message toMessage(Object o, Session session) throws JMSException, MessageConversionException {
        if (o instanceof BarChartDataWrapper) {
            return session.createObjectMessage((Serializable) o);
        } else if (o instanceof TradesInfo){
            return session.createObjectMessage((Serializable) o);
        } else {
            throw  new MessageConversionException("Message is not of expected type.");
        }
    }

    @Override
    public Object fromMessage(Message message) throws JMSException, MessageConversionException {
        Serializable o = null;
        if (message instanceof ObjectMessage){
            o = ((ObjectMessage) message).getObject();
            if (o instanceof BarChartDataWrapper){
                return o;
            } else if (o instanceof TradesInfo){
                return o;
            } else {
                throw new MessageConversionException("Message is not of expected type.");
            }
        } else {
            throw new MessageConversionException("Message cannot be parsed.");
        }
    }
}
