package com.pay.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class MoneyBd {
    private Integer id;

    private Integer userid;

    private BigDecimal money;

    private Date datetime;

    private Integer lx;

    private BigDecimal ymoney;

    private BigDecimal gmoney;

    private String transid;

    private Integer tcjb;
    
    private String remark;
    
    private Integer dispose_userid;
    
    private User  user;

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

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public Integer getLx() {
        return lx;
    }

    public void setLx(Integer lx) {
        this.lx = lx;
    }

    public BigDecimal getYmoney() {
        return ymoney;
    }

    public void setYmoney(BigDecimal ymoney) {
        this.ymoney = ymoney;
    }

    public BigDecimal getGmoney() {
        return gmoney;
    }

    public void setGmoney(BigDecimal gmoney) {
        this.gmoney = gmoney;
    }

    public String getTransid() {
        return transid;
    }

    public void setTransid(String transid) {
        this.transid = transid == null ? null : transid.trim();
    }

    public Integer getTcjb() {
        return tcjb;
    }

    public void setTcjb(Integer tcjb) {
        this.tcjb = tcjb;
    }

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getDispose_userid() {
		return dispose_userid;
	}

	public void setDispose_userid(Integer dispose_userid) {
		this.dispose_userid = dispose_userid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
    
	
}