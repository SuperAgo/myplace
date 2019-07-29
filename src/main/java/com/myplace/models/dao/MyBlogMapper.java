package com.myplace.models.dao;

import com.myplace.models.entity.MyBlog;

import java.util.List;
import java.util.Map;

public interface MyBlogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MyBlog record);

    int insertSelective(MyBlog record);

    MyBlog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MyBlog record);

    int updateByPrimaryKey(MyBlog record);

    List<MyBlog> getCarouselArticle();

    List<MyBlog> getLatestArticles();

    List<MyBlog> getPopularArticles();

    List<MyBlog> getMostReadingArticles();

    List<MyBlog> getSelectedArticles();

    List<MyBlog> getClassificationArticles(int getClassificationArticles);

    List<MyBlog> getMostCommentedArticles();
}