package com.myplace.models.controller;

import com.myplace.common.page.RequestModel;
import com.myplace.common.page.ResponseModel;
import com.myplace.models.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/index")
public class indexController {
    @Autowired
    private IndexService indexService;

    @PostMapping("/getIndex")
    public ResponseModel getIndex(RequestModel requestModel){
        return indexService.getIndex(requestModel);
    }
}
