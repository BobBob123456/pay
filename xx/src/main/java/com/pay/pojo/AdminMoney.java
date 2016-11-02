package com.pay.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class AdminMoney {
    private Integer id;

    private Integer userid;

    private BigDecimal money;

    private Integer jj;

    private String content;

    private Date datetime;

    private Integer zt;

    private Integer lr;

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

    public Integer getJj() {
        return jj;
    }

    public void setJj(Integer jj) {
        this.jj = jj;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public Integer getZt() {
        return zt;
    }

    public void setZt(Integer zt) {
        this.zt = zt;
    }

    public Integer getLr() {
        return lr;
    }

    public void setLr(Integer lr) {
        this.lr = lr;
    }
}