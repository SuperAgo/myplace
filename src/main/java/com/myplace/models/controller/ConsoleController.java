package com.myplace.models.controller;

import com.myplace.common.page.RequestModel;
import com.myplace.common.page.ResponseModel;
import com.myplace.models.service.BlogService;
import com.myplace.models.service.ConsoleService;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/console")
public class ConsoleController {
    @Autowired
    private ConsoleService consoleService;

    @ApiOperation(value = "登录")
    @RequestMapping("/login")
    public ModelAndView getLogin(){
        return consoleService.getLogin();
    }


}
