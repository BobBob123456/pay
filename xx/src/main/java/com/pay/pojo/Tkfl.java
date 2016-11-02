package com.pay.pojo;

import java.math.BigDecimal;

public class Tkfl {
    private Integer id;

    private BigDecimal kMoney;

    private BigDecimal sMoney;

    private BigDecimal flMoney;

    private Integer t;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getkMoney() {
        return kMoney;
    }

    public void setkMoney(BigDecimal kMoney) {
        this.kMoney = kMoney;
    }

    public BigDecimal getsMoney() {
        return sMoney;
    }

    public void setsMoney(BigDecimal sMoney) {
        this.sMoney = sMoney;
    }

    public BigDecimal getFlMoney() {
        return flMoney;
    }

    public void setFlMoney(BigDecimal flMoney) {
        this.flMoney = flMoney;
    }

    public Integer getT() {
        return t;
    }

    public void setT(Integer t) {
        this.t = t;
    }
}