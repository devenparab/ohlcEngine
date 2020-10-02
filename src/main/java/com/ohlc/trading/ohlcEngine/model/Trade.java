package com.ohlc.trading.ohlcEngine.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ohlc.trading.ohlcEngine.common.CommonConstants;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Trade implements Serializable/*, Comparator<Trade>*/ {
    private static final long serialVersionUID = -5073382210998337264L;

    public String sym;
    @JsonProperty("T")
    public String t;
    @JsonProperty("P")
    public double p;
    @JsonProperty("Q")
    public double q;
    @JsonProperty("TS")
    public double tS;
    public String side;
    @JsonProperty("TS2")
    public long tS2;
    private String tS2_formattedTime;
    private LocalDateTime tS2LocalDateTime;

    public LocalDateTime gettS2LocalDateTime() {
        return tS2LocalDateTime;
    }

    public void settS2LocalDateTime(LocalDateTime tS2LocalDateTime) {
        this.tS2LocalDateTime = tS2LocalDateTime;
    }

    public String getSym() {
        return sym;
    }

    public void setSym(String sym) {
        this.sym = sym;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public double getP() {
        return p;
    }

    public void setP(double p) {
        this.p = p;
    }

    public double getQ() {
        return q;
    }

    public void setQ(double q) {
        this.q = q;
    }

    public double gettS() {
        return tS;
    }

    public void settS(double tS) {
        this.tS = tS;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public long gettS2() {
        return tS2;
    }

    public void settS2(long tS2) {
        this.tS2 = tS2;
        formatTS(this.tS2);
    }

    public String gettS2_formattedTime() {
        return tS2_formattedTime;
    }

    public void settS2_formattedTime(String tS2_formattedTime) {
        this.tS2_formattedTime = tS2_formattedTime;
    }


    private void formatTS(long unix_seconds) {
        long seconds = unix_seconds / 1_000_000_000;
        long nanos = unix_seconds % 1_000_000_000;
        Instant instant = Instant.ofEpochSecond(seconds, nanos);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(CommonConstants.dateFormat);
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.of(CommonConstants.IST));
        this.tS2_formattedTime = dateTime.format(dtf);
        this.tS2LocalDateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "sym='" + sym + '\'' +
                ", t='" + t + '\'' +
                ", p=" + p +
                ", q=" + q +
                ", tS=" + tS +
                ", side='" + side + '\'' +
                ", tS2=" + tS2 +
                ", tS2_formattedTime='" + tS2_formattedTime + '\'' +
                ", tS2LocalDateTime=" + tS2LocalDateTime +
                '}';
    }

   /*@Override
    public int compare(Trade o1, Trade o2) {
        try {
            return new SimpleDateFormat("HH:mm:ss").parse(o1.gettS2_formattedTime())
                            .compareTo(new SimpleDateFormat("HH:mm:ss").parse(o1.gettS2_formattedTime()));
        } catch (ParseException e) {
            return 0;
        }
    }*/
}
