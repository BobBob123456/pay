package com.pay.pojo;

public class UserSafeTyInformation {
    private Integer id;

    private Integer userid;

    private String affirmpassword;

    private String paypassword;

    private String affirmtitle;

    private String affirmanswer;

    private String email;

    private Integer t0;

    private Integer paybank;

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

    public String getAffirmpassword() {
        return affirmpassword;
    }

    public void setAffirmpassword(String affirmpassword) {
        this.affirmpassword = affirmpassword == null ? null : affirmpassword.trim();
    }

    public String getPaypassword() {
        return paypassword;
    }

    public void setPaypassword(String paypassword) {
        this.paypassword = paypassword == null ? null : paypassword.trim();
    }

    public String getAffirmtitle() {
        return affirmtitle;
    }

    public void setAffirmtitle(String affirmtitle) {
        this.affirmtitle = affirmtitle == null ? null : affirmtitle.trim();
    }

    public String getAffirmanswer() {
        return affirmanswer;
    }

    public void setAffirmanswer(String affirmanswer) {
        this.affirmanswer = affirmanswer == null ? null : affirmanswer.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getT0() {
        return t0;
    }

    public void setT0(Integer t0) {
        this.t0 = t0;
    }

    public Integer getPaybank() {
        return paybank;
    }

    public void setPaybank(Integer paybank) {
        this.paybank = paybank;
    }
}