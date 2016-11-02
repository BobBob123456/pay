package com.pay.pojo;

public class UserApiInformation {
    private Integer id;

    private Integer userid;

    private String companyname;

    private String websitename;

    private String websiteurl;

    private String identificationfront;

    private String identificationreverse;

    private String businesslicense;

    private String scsfzbsz;

    private String key;

    private Integer zt;

    private String dbszp;

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

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname == null ? null : companyname.trim();
    }

    public String getWebsitename() {
        return websitename;
    }

    public void setWebsitename(String websitename) {
        this.websitename = websitename == null ? null : websitename.trim();
    }

    public String getWebsiteurl() {
        return websiteurl;
    }

    public void setWebsiteurl(String websiteurl) {
        this.websiteurl = websiteurl == null ? null : websiteurl.trim();
    }

    public String getIdentificationfront() {
        return identificationfront;
    }

    public void setIdentificationfront(String identificationfront) {
        this.identificationfront = identificationfront == null ? null : identificationfront.trim();
    }

    public String getIdentificationreverse() {
        return identificationreverse;
    }

    public void setIdentificationreverse(String identificationreverse) {
        this.identificationreverse = identificationreverse == null ? null : identificationreverse.trim();
    }

    public String getBusinesslicense() {
        return businesslicense;
    }

    public void setBusinesslicense(String businesslicense) {
        this.businesslicense = businesslicense == null ? null : businesslicense.trim();
    }

    public String getScsfzbsz() {
        return scsfzbsz;
    }

    public void setScsfzbsz(String scsfzbsz) {
        this.scsfzbsz = scsfzbsz == null ? null : scsfzbsz.trim();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key == null ? null : key.trim();
    }

    public Integer getZt() {
        return zt;
    }

    public void setZt(Integer zt) {
        this.zt = zt;
    }

    public String getDbszp() {
        return dbszp;
    }

    public void setDbszp(String dbszp) {
        this.dbszp = dbszp == null ? null : dbszp.trim();
    }
}