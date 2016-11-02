package com.pay.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private Integer id;

    private Integer userid;

    private String transid;

    private Date tradedate;

    private BigDecimal trademoney;

    private BigDecimal sxfmoney;

    private BigDecimal sjflmoney;

    private BigDecimal ordermoney;

    private String productname;

    private Integer amount;

    private String username;

    private String email;

    private String mobile;

    private Integer zt;

    private String sjtMerchantUrl;

    private String sjtReturnUrl;

    private String tjurl;

    private String cardno;

    private String cardpwd;

    private Integer typepay;

    private String tongdao;

    private String bankname;

    private String payname;

    private String fhlx;

    private String ewmimg;

    private String tbhdurl;

    private String additionalinfo;

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

    public String getTransid() {
        return transid;
    }

    public void setTransid(String transid) {
        this.transid = transid == null ? null : transid.trim();
    }

    public Date getTradedate() {
        return tradedate;
    }

    public void setTradedate(Date tradedate) {
        this.tradedate = tradedate;
    }

    public BigDecimal getTrademoney() {
        return trademoney;
    }

    public void setTrademoney(BigDecimal trademoney) {
        this.trademoney = trademoney;
    }

    public BigDecimal getSxfmoney() {
        return sxfmoney;
    }

    public void setSxfmoney(BigDecimal sxfmoney) {
        this.sxfmoney = sxfmoney;
    }

    public BigDecimal getSjflmoney() {
        return sjflmoney;
    }

    public void setSjflmoney(BigDecimal sjflmoney) {
        this.sjflmoney = sjflmoney;
    }

    public BigDecimal getOrdermoney() {
        return ordermoney;
    }

    public void setOrdermoney(BigDecimal ordermoney) {
        this.ordermoney = ordermoney;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname == null ? null : productname.trim();
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Integer getZt() {
        return zt;
    }

    public void setZt(Integer zt) {
        this.zt = zt;
    }

    public String getSjtMerchantUrl() {
        return sjtMerchantUrl;
    }

    public void setSjtMerchantUrl(String sjtMerchantUrl) {
        this.sjtMerchantUrl = sjtMerchantUrl == null ? null : sjtMerchantUrl.trim();
    }

    public String getSjtReturnUrl() {
        return sjtReturnUrl;
    }

    public void setSjtReturnUrl(String sjtReturnUrl) {
        this.sjtReturnUrl = sjtReturnUrl == null ? null : sjtReturnUrl.trim();
    }

    public String getTjurl() {
        return tjurl;
    }

    public void setTjurl(String tjurl) {
        this.tjurl = tjurl == null ? null : tjurl.trim();
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno == null ? null : cardno.trim();
    }

    public String getCardpwd() {
        return cardpwd;
    }

    public void setCardpwd(String cardpwd) {
        this.cardpwd = cardpwd == null ? null : cardpwd.trim();
    }

    public Integer getTypepay() {
        return typepay;
    }

    public void setTypepay(Integer typepay) {
        this.typepay = typepay;
    }

    public String getTongdao() {
        return tongdao;
    }

    public void setTongdao(String tongdao) {
        this.tongdao = tongdao == null ? null : tongdao.trim();
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname == null ? null : bankname.trim();
    }

    public String getPayname() {
        return payname;
    }

    public void setPayname(String payname) {
        this.payname = payname == null ? null : payname.trim();
    }

    public String getFhlx() {
        return fhlx;
    }

    public void setFhlx(String fhlx) {
        this.fhlx = fhlx == null ? null : fhlx.trim();
    }

    public String getEwmimg() {
        return ewmimg;
    }

    public void setEwmimg(String ewmimg) {
        this.ewmimg = ewmimg == null ? null : ewmimg.trim();
    }

    public String getTbhdurl() {
        return tbhdurl;
    }

    public void setTbhdurl(String tbhdurl) {
        this.tbhdurl = tbhdurl == null ? null : tbhdurl.trim();
    }

    public String getAdditionalinfo() {
        return additionalinfo;
    }

    public void setAdditionalinfo(String additionalinfo) {
        this.additionalinfo = additionalinfo == null ? null : additionalinfo.trim();
    }
}