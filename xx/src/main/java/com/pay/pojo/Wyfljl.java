package com.pay.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Wyfljl {
    private Integer id;

    private Integer userid;

    private BigDecimal money;

    private Date ksDate;

    private Date jsDate;

    private Date sqDate;

    private BigDecimal fl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Date getKsDate() {
        return ksDate;
    }

    public void setKsDate(Date ksDate) {
        this.ksDate = ksDate;
    }

    public Date getJsDate() {
        return jsDate;
    }

    public void setJsDate(Date jsDate) {
        this.jsDate = jsDate;
    }

    public Date getSqDate() {
        return sqDate;
    }

    public void setSqDate(Date sqDate) {
        this.sqDate = sqDate;
    }

    public BigDecimal getFl() {
        return fl;
    }

    public void setFl(BigDecimal fl) {
        this.fl = fl;
    }
}