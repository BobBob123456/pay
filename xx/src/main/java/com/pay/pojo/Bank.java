package com.pay.pojo;


public class Bank {
    private Integer id;

    private Integer userid;

    private String bankname;

    private String bankbranch;

    private String bankaccountnumber;

    private String bankcompellation;

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

    public String getZhihang() {
        return zhihang;
    }

    public void setZhihang(String zhihang) {
        this.zhihang = zhihang == null ? null : zhihang.trim();
    }
}