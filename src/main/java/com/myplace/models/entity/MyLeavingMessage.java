package com.myplace.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class MyLeavingMessage implements Serializable {
    private List<MyLeavingMessage> sonList;

    public List<MyLeavingMessage> getSonList() {
        return sonList;
    }

    public void setSonList(List<MyLeavingMessage> sonList) {
        this.sonList = sonList;
    }

    private String parentName;
    private String networkAvatar;

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getNetworkAvatar() {
        return networkAvatar;
    }

    public void setNetworkAvatar(String networkAvatar) {
        this.networkAvatar = networkAvatar;
    }

    /**
     * Id
     */
    private Integer id;

    /**
     * 父Id（为0则为评论源）
     */
    private Integer parentId;

    /**
     * 博客Id，为空则为留言
     */
    private Integer blogId;

    /**
     * 名字
     */
    private String name;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 留言
     */
    private String message;

    /**
     * 新增时间
     */
    private Date insTime;

    /**
     * 评论数量
     */
    private Integer comments;

    /**
     * 点赞数量
     */
    private Integer compliments;

    /**
     * Id
     * 
     */
    public Integer getId() {
        return id;
    }

    /**
     * Id
     * 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 父Id（为0则为评论源）
     * 
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 父Id（为0则为评论源）
     * 
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 博客Id，为空则为留言
     *
     */
    public Integer getBlogId() {
        return blogId;
    }

    /**
     * 博客Id，为空则为留言
     *
     */
    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    /**
     * 名字
     * 
     */
    public String getName() {
        return name;
    }

    /**
     * 名字
     * 
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 邮箱
     * 
     */
    public String getEmail() {
        return email;
    }

    /**
     * 邮箱
     * 
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 留言
     * 
     */
    public String getMessage() {
        return message;
    }

    /**
     * 留言
     * 
     */
    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    /**
     * 新增时间
     * 
     */
    public Date getInsTime() {
        return insTime;
    }

    /**
     * 新增时间
     * 
     */
    public void setInsTime(Date insTime) {
        this.insTime = insTime;
    }

    /**
     * 评论数量
     * 
     */
    public Integer getComments() {
        return comments;
    }

    /**
     * 评论数量
     * 
     */
    public void setComments(Integer comments) {
        this.comments = comments;
    }

    /**
     * 点赞数量
     * 
     */
    public Integer getCompliments() {
        return compliments;
    }

    /**
     * 点赞数量
     * 
     */
    public void setCompliments(Integer compliments) {
        this.compliments = compliments;
    }
}