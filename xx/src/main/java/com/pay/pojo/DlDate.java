package com.pay.pojo;

import java.util.Date;

public class DlDate {
    private Integer id;

    private Integer userid;

    private Date dldate;

    private String ip;

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

    public Date getDldate() {
        return dldate;
    }

    public void setDldate(Date dldate) {
        this.dldate = dldate;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }
}