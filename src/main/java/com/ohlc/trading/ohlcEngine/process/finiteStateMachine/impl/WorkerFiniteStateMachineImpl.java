package com.ohlc.trading.ohlcEngine.process.finiteStateMachine.impl;

import com.ohlc.trading.ohlcEngine.model.Trades;
import com.ohlc.trading.ohlcEngine.process.finiteStateMachine.WorkerFiniteStateMachine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class WorkerFiniteStateMachineImpl implements WorkerFiniteStateMachine {
    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerFiniteStateMachineImpl.class);

    @Override
    public void computeFiniteStateMachine(Trades trades) {
        LOGGER.info("### WorkerFiniteStateMachineImpl.java >> computeFiniteStateMachine() >> Trades[{}] ###",trades.toString());
        //to-do implement logic for FSM here
    }
}
