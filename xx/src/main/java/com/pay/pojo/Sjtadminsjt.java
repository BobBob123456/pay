package com.pay.pojo;

import java.util.Date;

public class Sjtadminsjt {
    private Integer id;

    private String sjtusername;

    private String sjtpassword;

    private Integer sjtusertype;

    private Date logintime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSjtusername() {
        return sjtusername;
    }

    public void setSjtusername(String sjtusername) {
        this.sjtusername = sjtusername == null ? null : sjtusername.trim();
    }

    public String getSjtpassword() {
        return sjtpassword;
    }

    public void setSjtpassword(String sjtpassword) {
        this.sjtpassword = sjtpassword == null ? null : sjtpassword.trim();
    }

    public Integer getSjtusertype() {
        return sjtusertype;
    }

    public void setSjtusertype(Integer sjtusertype) {
        this.sjtusertype = sjtusertype;
    }

    public Date getLogintime() {
        return logintime;
    }

    public void setLogintime(Date logintime) {
        this.logintime = logintime;
    }
}