package com.pay.pojo;

import java.math.BigDecimal;

public class Sjapi {
    private Integer id;

    private String apiname;

    private String myname;

    private String shid;

    private String key;

    private String zhanghu;

    private String editDate;

    private String payname;

    private BigDecimal fl;

    private Integer xz;

    private String tzurl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApiname() {
        return apiname;
    }

    public void setApiname(String apiname) {
        this.apiname = apiname == null ? null : apiname.trim();
    }

    public String getMyname() {
        return myname;
    }

    public void setMyname(String myname) {
        this.myname = myname == null ? null : myname.trim();
    }

    public String getShid() {
        return shid;
    }

    public void setShid(String shid) {
        this.shid = shid == null ? null : shid.trim();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key == null ? null : key.trim();
    }

    public String getZhanghu() {
        return zhanghu;
    }

    public void setZhanghu(String zhanghu) {
        this.zhanghu = zhanghu == null ? null : zhanghu.trim();
    }

    public String getEditDate() {
        return editDate;
    }

    public void setEditDate(String editDate) {
        this.editDate = editDate == null ? null : editDate.trim();
    }

    public String getPayname() {
        return payname;
    }

    public void setPayname(String payname) {
        this.payname = payname == null ? null : payname.trim();
    }

    public BigDecimal getFl() {
        return fl;
    }

    public void setFl(BigDecimal fl) {
        this.fl = fl;
    }

    public Integer getXz() {
        return xz;
    }

    public void setXz(Integer xz) {
        this.xz = xz;
    }

    public String getTzurl() {
        return tzurl;
    }

    public void setTzurl(String tzurl) {
        this.tzurl = tzurl == null ? null : tzurl.trim();
    }
}