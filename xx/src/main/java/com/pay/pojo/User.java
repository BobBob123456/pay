package com.pay.pojo;

import java.util.Date;

public class User {
    private Integer id;

    private String username;

    private String loginpassword;

    private Integer usertype;

    private Integer status;

    private Date regdate;

    private String activate;

    private Integer mbk;

    private Integer sjuserid;

    private Integer gmwttk;

    private String mypayurlname;
    
    private String usersessionid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getLoginpassword() {
        return loginpassword;
    }

    public void setLoginpassword(String loginpassword) {
        this.loginpassword = loginpassword == null ? null : loginpassword.trim();
    }

    public Integer getUsertype() {
        return usertype;
    }

    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    public String getActivate() {
        return activate;
    }

    public void setActivate(String activate) {
        this.activate = activate == null ? null : activate.trim();
    }

    public Integer getMbk() {
        return mbk;
    }

    public void setMbk(Integer mbk) {
        this.mbk = mbk;
    }

    public Integer getSjuserid() {
        return sjuserid;
    }

    public void setSjuserid(Integer sjuserid) {
        this.sjuserid = sjuserid;
    }

    public Integer getGmwttk() {
        return gmwttk;
    }

    public void setGmwttk(Integer gmwttk) {
        this.gmwttk = gmwttk;
    }

    public String getMypayurlname() {
        return mypayurlname;
    }

    public void setMypayurlname(String mypayurlname) {
        this.mypayurlname = mypayurlname == null ? null : mypayurlname.trim();
    }

	public String getUsersessionid() {
		return usersessionid;
	}

	public void setUsersessionid(String usersessionid) {
		this.usersessionid = usersessionid;
	}
    
    
}