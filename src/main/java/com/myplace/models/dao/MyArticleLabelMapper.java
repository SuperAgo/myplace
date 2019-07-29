package com.myplace.models.dao;

import com.myplace.models.entity.MyArticleLabel;

public interface MyArticleLabelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MyArticleLabel record);

    int insertSelective(MyArticleLabel record);

    MyArticleLabel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MyArticleLabel record);

    int updateByPrimaryKey(MyArticleLabel record);
}