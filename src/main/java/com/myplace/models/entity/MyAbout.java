package com.myplace.models.entity;

import java.io.Serializable;
import java.util.Date;

public class MyAbout implements Serializable {
    private Integer id;
    private String content;
    private Date insTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getInsTime() {
        return insTime;
    }

    public void setInsTime(Date insTime) {
        this.insTime = insTime;
    }
}
