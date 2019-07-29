package com.myplace.models.entity;

import java.util.Date;

public class MyBlog {
    /**
     * Id
     */
    private Integer id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 主标签
     */
    private Integer label;

    /**
     * 作者Id
     */
    private Integer author;

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
     * 阅读量
     */
    private Integer reader;

    /**
     * 是否置顶(0非置顶，1置顶)
     */
    private Integer isTop;

    /**
     * 置顶时间
     */
    private Date toppingTime;

    /**
     * 是否精选(0非精选，1精选)
     */
    private Integer isSelected;

    /**
     * 精选时间
     */
    private Date selectedTime;

    /**
     * 内容
     */
    private String content;

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
     * 文章标题
     * 
     */
    public String getTitle() {
        return title;
    }

    /**
     * 文章标题
     * 
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 主标签
     */
    public Integer getLabel() {
        return label;
    }

    /**
     * 主标签
     */
    public void setLabel(Integer label) {
        this.label = label;
    }

    /**
     * 作者Id
     * 
     */
    public Integer getAuthor() {
        return author;
    }

    /**
     * 作者Id
     * 
     */
    public void setAuthor(Integer author) {
        this.author = author;
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

    /**
     * 阅读量
     * 
     */
    public Integer getReader() {
        return reader;
    }

    /**
     * 阅读量
     * 
     */
    public void setReader(Integer reader) {
        this.reader = reader;
    }

    /**
     * 是否置顶(0非置顶，1置顶)
     * 
     */
    public Integer getIsTop() {
        return isTop;
    }

    /**
     * 是否置顶(0非置顶，1置顶)
     * 
     */
    public void setIsTop(Integer isTop) {
        this.isTop = isTop;
    }

    /**
     * 置顶时间
     * 
     */
    public Date getToppingTime() {
        return toppingTime;
    }

    /**
     * 置顶时间
     * 
     */
    public void setToppingTime(Date toppingTime) {
        this.toppingTime = toppingTime;
    }

    /**
     * 是否精选(0非精选，1精选)
     * 
     */
    public Integer getIsSelected() {
        return isSelected;
    }

    /**
     * 是否精选(0非精选，1精选)
     * 
     */
    public void setIsSelected(Integer isSelected) {
        this.isSelected = isSelected;
    }

    /**
     * 精选时间
     * 
     */
    public Date getSelectedTime() {
        return selectedTime;
    }

    /**
     * 精选时间
     * 
     */
    public void setSelectedTime(Date selectedTime) {
        this.selectedTime = selectedTime;
    }

    /**
     * 内容
     * 
     */
    public String getContent() {
        return content;
    }

    /**
     * 内容
     * 
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}