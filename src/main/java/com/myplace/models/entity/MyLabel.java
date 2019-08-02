package com.myplace.models.entity;

import java.util.Date;

public class MyLabel {
    /**
     * Id
     */
    private Integer id;

    /**
     * 父Id(未0则为父标签)
     */
    private Integer parentId;

    /**
     * 标签等级：1标题栏，2文章类型
     */
    private Integer tabGrade;

    /**
     * 标签名称
     */
    private String labelName;

    /**
     * 简介
     */
    private String remark;

    /**
     * 图片背景
     */
    private String background;

    /**
     * 新增人
     */
    private Integer insUser;

    /**
     * 新增时间
     */
    private Date insTime;

    /**
     * 顺序
     */
    private Integer sort;

    /**
     * 地址
     */
    private String url;

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
     * 父Id(未0则为父标签)
     * 
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 父Id(未0则为父标签)
     * 
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 标签等级：1标题栏，2文章类型
     */
    public Integer getTabGrade() {
        return tabGrade;
    }

    /**
     * 标签等级：1标题栏，2文章类型
     */
    public void setTabGrade(Integer tabGrade) {
        this.tabGrade = tabGrade;
    }

    /**
     * 标签名称
     * 
     */
    public String getLabelName() {
        return labelName;
    }

    /**
     * 标签名称
     * 
     */
    public void setLabelName(String labelName) {
        this.labelName = labelName == null ? null : labelName.trim();
    }

    /**
     * 简介
     * 
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 简介
     * 
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 图片背景
     * 
     */
    public String getBackground() {
        return background;
    }

    /**
     * 图片背景
     * 
     */
    public void setBackground(String background) {
        this.background = background == null ? null : background.trim();
    }

    /**
     * 新增人
     * 
     */
    public Integer getInsUser() {
        return insUser;
    }

    /**
     * 新增人
     * 
     */
    public void setInsUser(Integer insUser) {
        this.insUser = insUser;
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
     * 顺序
     * 
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 顺序
     * 
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 地址
     * 
     */
    public String getUrl() {
        return url;
    }

    /**
     * 地址
     * 
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }
}