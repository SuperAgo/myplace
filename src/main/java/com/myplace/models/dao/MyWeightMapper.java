package com.myplace.models.dao;

import com.myplace.models.entity.MyWeight;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MyWeightMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(MyWeight myWeight);

    MyWeight selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MyWeight myWeight);

    List<Map> getNearlySevenDaysOfData();

    List<Map> getThisMonthSData(@Param("params") Map<String, Object> params);

    int selectByUserIdAndDate(@Param("params") Map<String, Object> params);
}