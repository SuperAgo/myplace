package com.myplace.models.dao;

import com.myplace.models.entity.MyAbout;

import java.util.List;

public interface MyAboutMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MyAbout record);

    int insertSelective(MyAbout record);

    MyAbout selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MyAbout record);

    int updateByPrimaryKey(MyAbout record);

    List<MyAbout> selectAll();
}