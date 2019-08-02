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

    List<Map> getLabelByGradeId(int tabGrade);

    Map getBlogTabByBlogId(Integer blogId);

    Map getTabBySonId(Integer tabId);

    Map getTabByBlogId(Integer blogId);

    Map getTabByTabId(Integer tabId);

    List<MyLabel> selectMyLabelByPage(Map params);

    Map getTabByTabUrl(Map params);

    List<Map> selectArticleSonLabels(Integer blogId);
}