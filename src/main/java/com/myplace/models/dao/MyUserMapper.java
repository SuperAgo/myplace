package com.myplace.models.dao;

import com.myplace.models.entity.MyUser;

import java.util.Map;

public interface MyUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MyUser record);

    int insertSelective(MyUser record);

    MyUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MyUser record);

    int updateByPrimaryKey(MyUser record);

    Map selectByType(int type);

    MyUser selectByEmail(String email);

    Map getMyMessageBoard(int type);
}