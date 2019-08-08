package com.myplace.models.dao;

import com.myplace.models.entity.MyLeavingMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MyLeavingMessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MyLeavingMessage record);

    int insertSelective(MyLeavingMessage record);

    MyLeavingMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MyLeavingMessage record);

    int updateByPrimaryKey(MyLeavingMessage record);

    List<MyLeavingMessage> selectCommentByBlogId(Integer blogId);

    List<MyLeavingMessage> selectListByParentId(@Param("parentId") Integer parentId,@Param("blogId") Integer blogId);

    List<MyLeavingMessage> selectLeavingMessageListByParentId(@Param("parentId") Integer parentId);

    List<MyLeavingMessage> selectLeavingMessageList();
}