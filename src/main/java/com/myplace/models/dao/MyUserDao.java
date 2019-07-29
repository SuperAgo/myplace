package com.myplace.models.dao;

import com.myplace.models.entity.MyUser;

public interface MyUserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(MyUser record);

    int insertSelective(MyUser record);

    MyUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MyUser record);

    int updateByPrimaryKeyWithBLOBs(MyUser record);

    int updateByPrimaryKey(MyUser record);
}