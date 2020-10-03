package com.ohlc.trading.ohlcEngine.common;

import com.ohlc.trading.ohlcEngine.model.SegregatorState;
import com.ohlc.trading.ohlcEngine.model.SnapshotTradeWrapper;
import com.ohlc.trading.ohlcEngine.model.Trade;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class CommonConstants {
    public static final Map<String, SegregatorState> SEGREGATOR_INFO_MAP = new HashMap<>();
    public static final String IST = "Asia/Kolkata";
    public static final  String dateFormat = "dd-MMM-yyyy HH:mm:ss";
    public static final SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
    public static final String CURRENT_ACTIVE_SLOT = "CURRENT_ACTIVE_SLOT";
    public static final Map<Trade, SnapshotTradeWrapper> OPENER_TRADE_SNAPSHOT = new HashMap<>();
}
