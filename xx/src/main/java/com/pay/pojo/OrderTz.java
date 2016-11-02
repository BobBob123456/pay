package com.pay.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class OrderTz {
    private Integer id;

    private String sjtMerchantid;

    private String sjtUsername;

    private String sjtTransid;

    private String sjtReturn;

    private String sjtError;

    private BigDecimal sjtFactmoney;

    private String sjtSucctime;

    private String sjtSign;

    private String sjtUrlname;

    private Integer sjtNum;

    private Integer success;

    private Integer diaodang;

    private Date datetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSjtMerchantid() {
        return sjtMerchantid;
    }

    public void setSjtMerchantid(String sjtMerchantid) {
        this.sjtMerchantid = sjtMerchantid == null ? null : sjtMerchantid.trim();
    }

    public String getSjtUsername() {
        return sjtUsername;
    }

    public void setSjtUsername(String sjtUsername) {
        this.sjtUsername = sjtUsername == null ? null : sjtUsername.trim();
    }

    public String getSjtTransid() {
        return sjtTransid;
    }

    public void setSjtTransid(String sjtTransid) {
        this.sjtTransid = sjtTransid == null ? null : sjtTransid.trim();
    }

    public String getSjtReturn() {
        return sjtReturn;
    }

    public void setSjtReturn(String sjtReturn) {
        this.sjtReturn = sjtReturn == null ? null : sjtReturn.trim();
    }

    public String getSjtError() {
        return sjtError;
    }

    public void setSjtError(String sjtError) {
        this.sjtError = sjtError == null ? null : sjtError.trim();
    }

    public BigDecimal getSjtFactmoney() {
        return sjtFactmoney;
    }

    public void setSjtFactmoney(BigDecimal sjtFactmoney) {
        this.sjtFactmoney = sjtFactmoney;
    }

    public String getSjtSucctime() {
        return sjtSucctime;
    }

    public void setSjtSucctime(String sjtSucctime) {
        this.sjtSucctime = sjtSucctime == null ? null : sjtSucctime.trim();
    }

    public String getSjtSign() {
        return sjtSign;
    }

    public void setSjtSign(String sjtSign) {
        this.sjtSign = sjtSign == null ? null : sjtSign.trim();
    }

    public String getSjtUrlname() {
        return sjtUrlname;
    }

    public void setSjtUrlname(String sjtUrlname) {
        this.sjtUrlname = sjtUrlname == null ? null : sjtUrlname.trim();
    }

    public Integer getSjtNum() {
        return sjtNum;
    }

    public void setSjtNum(Integer sjtNum) {
        this.sjtNum = sjtNum;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public Integer getDiaodang() {
        return diaodang;
    }

    public void setDiaodang(Integer diaodang) {
        this.diaodang = diaodang;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
}