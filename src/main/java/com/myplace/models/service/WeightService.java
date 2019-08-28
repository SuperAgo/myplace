package com.myplace.models.service;

import com.myplace.common.page.RequestModel;
import com.myplace.common.page.ResponseModel;

public interface WeightService {

    ResponseModel setNewWeight(RequestModel requestModel);

    ResponseModel getNearlySevenDaysOfData();

    ResponseModel getThisMonthSData(RequestModel requestModel);
}
