package com.ohlc.trading.ohlcEngine.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class BarChartDataWrapper implements Serializable {
    private static final long serialVersionUID = -5502397932358127060L;

    private List<BarChartData> barChartDataList = new LinkedList<>();

    public List<BarChartData> getBarChartDataList() {
        return barChartDataList;
    }

    public void setBarChartDataList(List<BarChartData> barChartDataList) {
        this.barChartDataList = barChartDataList;
    }
}
