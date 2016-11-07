package com.pay.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class TkList {
    private Integer id;

    private Integer userid;

    private BigDecimal tkMoney;

    private BigDecimal sxfMoney;

    private BigDecimal money;

    private String bankname;

    private String zhiBankname;

    private String fenBankname;

    private String bankNumber;

    private String myname;

    private Date sqDate;

    private Date qrDate;

    private Integer zt;

    private Integer t;

    private Integer wt;

    private String orderId;
    
    private String voucher;
    
    private User   user;
    
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

    public BigDecimal getTkMoney() {
        return tkMoney;
    }

    public void setTkMoney(BigDecimal tkMoney) {
        this.tkMoney = tkMoney;
    }

    public BigDecimal getSxfMoney() {
        return sxfMoney;
    }

    public void setSxfMoney(BigDecimal sxfMoney) {
        this.sxfMoney = sxfMoney;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname == null ? null : bankname.trim();
    }

    public String getZhiBankname() {
        return zhiBankname;
    }

    public void setZhiBankname(String zhiBankname) {
        this.zhiBankname = zhiBankname == null ? null : zhiBankname.trim();
    }

    public String getFenBankname() {
        return fenBankname;
    }

    public void setFenBankname(String fenBankname) {
        this.fenBankname = fenBankname == null ? null : fenBankname.trim();
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber == null ? null : bankNumber.trim();
    }

    public String getMyname() {
        return myname;
    }

    public void setMyname(String myname) {
        this.myname = myname == null ? null : myname.trim();
    }

    public Date getSqDate() {
        return sqDate;
    }

    public void setSqDate(Date sqDate) {
        this.sqDate = sqDate;
    }

    public Date getQrDate() {
        return qrDate;
    }

    public void setQrDate(Date qrDate) {
        this.qrDate = qrDate;
    }

    public Integer getZt() {
        return zt;
    }

    public void setZt(Integer zt) {
        this.zt = zt;
    }

    public Integer getT() {
        return t;
    }

    public void setT(Integer t) {
        this.t = t;
    }

    public Integer getWt() {
        return wt;
    }

    public void setWt(Integer wt) {
        this.wt = wt;
    }

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getVoucher() {
		return voucher;
	}

	public void setVoucher(String voucher) {
		this.voucher = voucher;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
    
}