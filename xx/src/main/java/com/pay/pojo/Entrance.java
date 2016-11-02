package com.pay.pojo;

public class Entrance {
    private Integer id;

    private String code;

    private String urlajax;

    private String namename;

    private Integer status;

    private String tel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getUrlajax() {
        return urlajax;
    }

    public void setUrlajax(String urlajax) {
        this.urlajax = urlajax == null ? null : urlajax.trim();
    }

    public String getNamename() {
        return namename;
    }

    public void setNamename(String namename) {
        this.namename = namename == null ? null : namename.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }
}