package com.myplace.models.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myplace.common.page.RequestModel;
import com.myplace.common.page.ResponseModel;
import com.myplace.models.dao.MyBlogMapper;
import com.myplace.models.dao.MyLabelMapper;
import com.myplace.models.dao.MyLeavingMessageMapper;
import com.myplace.models.dao.MyUserMapper;
import com.myplace.models.entity.MyBlog;
import com.myplace.models.entity.MyLabel;
import com.myplace.models.entity.MyLeavingMessage;
import com.myplace.models.entity.MyUser;
import com.myplace.models.entity.enums.LabelGradeEnum;
import com.myplace.models.entity.enums.UserTypeEnum;
import com.myplace.models.service.BlogService;
import com.myplace.models.service.ConsoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

@Service("consoleService")
public class ConsoleServiceImpl implements ConsoleService {
    @Resource
    private MyLabelMapper myLabelMapper;
    @Resource
    private MyBlogMapper myBlogMapper;
    @Resource
    private MyUserMapper myUserMapper;
    @Resource
    private MyLeavingMessageMapper myLeavingMessageMapper;


    @Override
    public ModelAndView getLogin() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        return mv;
    }
}
