package com.myplace.models.dao;

import com.myplace.models.entity.MyLeavingMessage;

public interface MyLeavingMessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MyLeavingMessage record);

    int insertSelective(MyLeavingMessage record);

    MyLeavingMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MyLeavingMessage record);

    int updateByPrimaryKey(MyLeavingMessage record);
}