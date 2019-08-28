package com.myplace.models.service.impl;

import com.alibaba.fastjson.JSON;
import com.myplace.common.page.RequestModel;
import com.myplace.common.page.ResponseModel;
import com.myplace.models.dao.MyWeightMapper;
import com.myplace.models.entity.MyWeight;
import com.myplace.models.service.WeightService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("weightService")
public class WeightServiceImpl implements WeightService {
    @Resource
    private MyWeightMapper myWeightMapper;

    @Override
    public ResponseModel setNewWeight(RequestModel requestModel) {
        requestModel.getParams().put("userId",2);
        int x = myWeightMapper.selectByUserIdAndDate(requestModel.getParams());
        if (x > 0) {
            return ResponseModel.error("小可爱，今天已经新增过了哦！");
        }
        MyWeight myWeight = JSON.parseObject(JSON.toJSONString(requestModel.getParams()), MyWeight.class);
        int i = myWeightMapper.insertSelective(myWeight);
        if (i < 0) {
            return ResponseModel.error("小可爱，新增失败了(╥╯^╰╥)！");
        }
        return ResponseModel.success("小可爱，新增成功(￣▽￣)／！");
    }

    @Override
    public ResponseModel getNearlySevenDaysOfData() {
        List<Map> nearlySevenDaysOfData = myWeightMapper.getNearlySevenDaysOfData();
        return ResponseModel.success(nearlySevenDaysOfData);
    }

    @Override
    public ResponseModel getThisMonthSData(RequestModel requestModel) {
        List<Map> thisMonthSData = myWeightMapper.getThisMonthSData(requestModel.getParams());
        return ResponseModel.success(thisMonthSData);
    }

}
