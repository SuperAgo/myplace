package com.myplace.models.dao;

import com.myplace.models.entity.MyLabel;

import java.util.List;
import java.util.Map;

public interface MyLabelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MyLabel record);

    int insertSelective(MyLabel record);

    MyLabel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MyLabel record);

    int updateByPrimaryKey(MyLabel record);

    List<Map> getLabelByParentId(int parentId);

    List<Map> getSonLabelList();
}