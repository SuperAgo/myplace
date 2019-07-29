package com.myplace.models.entity;

public class MyArticleLabel {
    /**
     * Id
     */
    private Integer id;

    /**
     * 标签Id
     */
    private Integer labelId;

    /**
     * 博客Id
     */
    private Integer blogId;

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
     * 标签Id
     * 
     */
    public Integer getLabelId() {
        return labelId;
    }

    /**
     * 标签Id
     * 
     */
    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }

    /**
     * 博客Id
     * 
     */
    public Integer getBlogId() {
        return blogId;
    }

    /**
     * 博客Id
     * 
     */
    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }
}