package com.myplace.models.service;


import com.myplace.common.page.RequestModel;
import com.myplace.common.page.ResponseModel;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

public interface BlogService {
    ModelAndView getIndex();

    ModelAndView getDetail(Integer blogId);

    ModelAndView getLabel(Integer page, Integer tabId);

    ModelAndView getTab(Integer page, Integer tabId);

    ModelAndView getAbout();

    ModelAndView getContact(Integer page);

    ResponseModel getClassificationArticles(Integer labelId);

    ModelAndView getSearch(Integer page,String title);

    ResponseModel getSubscribe(String email);

    ResponseModel setleaveAMessage(RequestModel requestModel);

    ModelAndView getMain();

    ResponseModel setCommont(RequestModel requestModel);

    ModelAndView getError();

    ModelAndView getError404();

    ModelAndView getNewCommont(Integer blogId);

    ModelAndView getNewContact(Integer page);
}
