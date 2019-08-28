package com.myplace.models.controller;

import com.myplace.common.page.RequestModel;
import com.myplace.common.page.ResponseModel;
import com.myplace.models.service.BlogService;
import com.myplace.models.service.WeightService;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/myWeight")
public class WeightController {
    @Autowired
    private WeightService weightService;

    @ApiOperation(value = "图表页面demo")
    @RequestMapping("/demo")
    public ModelAndView getDemo(ModelAndView modelAndView){
        modelAndView.setViewName("weightDemo");
        return modelAndView;
    }

    @ApiOperation(value = "图表页面")
    @RequestMapping("/getChartPage")
    public ModelAndView getChartPage(ModelAndView modelAndView){
        modelAndView.setViewName("myWeight");
        return modelAndView;
    }

    @ApiOperation(value = "获得近七天数据")
    @PostMapping("/getNearlySevenDaysOfData")
    public ResponseModel getNearlySevenDaysOfData(){
        return weightService.getNearlySevenDaysOfData();
    }

    @ApiOperation(value = "获得本月数据")
    @PostMapping("/getThisMonthSData")
    public ResponseModel getThisMonthSData(RequestModel requestModel){
        return weightService.getThisMonthSData(requestModel);
    }


    @ApiOperation(value = "新的体重")
    @PostMapping("/setNewWeight")
    public ResponseModel setNewWeight(RequestModel requestModel){
        return weightService.setNewWeight(requestModel);
    }
}
