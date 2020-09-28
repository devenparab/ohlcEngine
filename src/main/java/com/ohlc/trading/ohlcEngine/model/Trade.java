package com.ohlc.trading.ohlcEngine.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Trade implements Serializable {
    private static final long serialVersionUID = 1L;

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
                '}';
    }
}
