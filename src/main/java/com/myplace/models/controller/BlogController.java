package com.myplace.models.controller;

import com.myplace.common.page.RequestModel;
import com.myplace.common.page.ResponseModel;
import com.myplace.models.service.BlogService;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/free")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @ApiOperation(value = "主页")
    @RequestMapping("/blog")
    public ModelAndView getMain(){
        return blogService.getMain();
    }

    @ApiOperation(value = "播放器")
    @RequestMapping("/audio")
    public ModelAndView getAudio(){
        return blogService.getAudio();
    }

    @ApiOperation(value = "主页内容")
    @RequestMapping("/index")
    public ModelAndView getIndex(){
        return blogService.getIndex();
    }

    @ApiOperation(value = "下拉文章")
    @PostMapping("/getClassificationArticles")
    public ResponseModel getClassificationArticles(@Param("labelId") Integer labelId){
        return blogService.getClassificationArticles(labelId);
    }

    @ApiOperation(value = "搜索")
    @PostMapping("/search")
    public ModelAndView getSearch(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,@Param("title") String title){
        return blogService.getSearch(page,title);
    }

    @ApiOperation(value = "关于博主")
    @PostMapping("/about")
    public ModelAndView getAbout(){
        return blogService.getAbout();
    }

    @ApiOperation(value = "给我留言")
    @PostMapping("/contact")
    public ModelAndView getContact(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page){
        return blogService.getContact(page);
    }

    @ApiOperation(value = "刷新留言")
    @PostMapping("/getNewContact")
    public ModelAndView getNewContact(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page){
        return blogService.getNewContact(page);
    }

    @ApiOperation(value = "博客详情")
    @PostMapping("/detail")
    public ModelAndView getDetail(@Param("blogId") Integer blogId){
        return blogService.getDetail(blogId);
    }

    @ApiOperation(value = "分类标签列表")
    @PostMapping("/tab")
    public ModelAndView getTab(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,@Param("tabId") Integer tabId){
        return blogService.getTab(page,tabId);
    }

    @ApiOperation(value = "分类标签详情")
    @PostMapping("/label")
    public ModelAndView getLabel(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,@Param("tabId") Integer tabId){
        return blogService.getLabel(page,tabId);
    }

    @ApiOperation(value = "博客订阅")
    @PostMapping("/subscribe")
    public ResponseModel getSubscribe(@Param("email") String email){
        return blogService.getSubscribe(email);
    }

    @ApiOperation(value = "留言")
    @PostMapping("/leaveAMessage")
    public ResponseModel setleaveAMessage(RequestModel requestModel){
        return blogService.setleaveAMessage(requestModel);
    }

    @ApiOperation(value = "评论")
    @PostMapping("/setCommont")
    public ResponseModel setCommont(RequestModel requestModel){
        return blogService.setCommont(requestModel);
    }

    @ApiOperation(value = "刷新评论")
    @PostMapping("/getNewCommont")
    public ModelAndView getNewCommont(@Param("blogId") Integer blogId){
        return blogService.getNewCommont(blogId);
    }

    @ApiOperation(value = "错误")
    @RequestMapping("/error")
    public ModelAndView getError(){
        return blogService.getError();
    }

    @ApiOperation(value = "错误")
    @RequestMapping("/getError404")
    public ModelAndView getError404(){
        return blogService.getError404();
    }
}
