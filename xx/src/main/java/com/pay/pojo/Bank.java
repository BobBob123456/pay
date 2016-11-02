package com.pay.pojo;

import java.math.BigDecimal;

public class Bank {
    private Integer id;

    private Integer userid;

    private String bankname;

    private String bankbranch;

    private String bankaccountnumber;

    private String bankcompellation;

    private Integer moren;

    private Integer banktype;

    private BigDecimal tkMoney;

    private Double sxfMoney;

    private BigDecimal sjMoney;

    private Integer t;

    private Integer tkIf;

    private String zhihang;

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

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname == null ? null : bankname.trim();
    }

    public String getBankbranch() {
        return bankbranch;
    }

    public void setBankbranch(String bankbranch) {
        this.bankbranch = bankbranch == null ? null : bankbranch.trim();
    }

    public String getBankaccountnumber() {
        return bankaccountnumber;
    }

    public void setBankaccountnumber(String bankaccountnumber) {
        this.bankaccountnumber = bankaccountnumber == null ? null : bankaccountnumber.trim();
    }

    public String getBankcompellation() {
        return bankcompellation;
    }

    public void setBankcompellation(String bankcompellation) {
        this.bankcompellation = bankcompellation == null ? null : bankcompellation.trim();
    }

    public Integer getMoren() {
        return moren;
    }

    public void setMoren(Integer moren) {
        this.moren = moren;
    }

    public Integer getBanktype() {
        return banktype;
    }

    public void setBanktype(Integer banktype) {
        this.banktype = banktype;
    }

    public BigDecimal getTkMoney() {
        return tkMoney;
    }

    public void setTkMoney(BigDecimal tkMoney) {
        this.tkMoney = tkMoney;
    }

    public Double getSxfMoney() {
        return sxfMoney;
    }

    public void setSxfMoney(Double sxfMoney) {
        this.sxfMoney = sxfMoney;
    }

    public BigDecimal getSjMoney() {
        return sjMoney;
    }

    public void setSjMoney(BigDecimal sjMoney) {
        this.sjMoney = sjMoney;
    }

    public Integer getT() {
        return t;
    }

    public void setT(Integer t) {
        this.t = t;
    }

    public Integer getTkIf() {
        return tkIf;
    }

    public void setTkIf(Integer tkIf) {
        this.tkIf = tkIf;
    }

    public String getZhihang() {
        return zhihang;
    }

    public void setZhihang(String zhihang) {
        this.zhihang = zhihang == null ? null : zhihang.trim();
    }
}