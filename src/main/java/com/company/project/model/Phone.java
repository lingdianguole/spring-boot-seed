package com.company.project.model;

import javax.persistence.*;

public class Phone {
    @Id
    private Long phoneid;

    private Long phonenum;

    private Integer userid;

    /**
     * @return phoneid
     */
    public Long getPhoneid() {
        return phoneid;
    }

    /**
     * @param phoneid
     */
    public void setPhoneid(Long phoneid) {
        this.phoneid = phoneid;
    }

    /**
     * @return phonenum
     */
    public Long getPhonenum() {
        return phonenum;
    }

    /**
     * @param phonenum
     */
    public void setPhonenum(Long phonenum) {
        this.phonenum = phonenum;
    }

    /**
     * @return userid
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * @param userid
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}