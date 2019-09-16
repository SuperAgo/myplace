package com.myplace.models.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myplace.common.page.RequestModel;
import com.myplace.common.page.ResponseModel;
import com.myplace.models.entity.MyAbout;
import com.myplace.models.entity.MyBlog;
import com.myplace.models.entity.MyLabel;
import com.myplace.models.entity.MyLeavingMessage;
import com.myplace.models.entity.enums.LabelGradeEnum;
import com.myplace.models.entity.enums.UserTypeEnum;
import com.myplace.models.service.BlogService;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@RestController
@RequestMapping("/free")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @ApiOperation(value = "主页")
    @RequestMapping("/blog")
    public ModelAndView getMain() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("myLabelList",blogService.getMyLabelList());
        mv.setViewName("main");
        return mv;
    }

    @ApiOperation(value = "播放器")
    @RequestMapping("/audio")
    public ModelAndView getAudio() {
        return blogService.getAudio();
    }

    @ApiOperation(value = "主页内容")
    @RequestMapping("/index")
    public ModelAndView getIndex() {
        ModelAndView mv = new ModelAndView();
        //轮播*3
        mv.addObject("carouselArticle", blogService.getCarouselArticle());
        //最新*3
        mv.addObject("latestArticles", blogService.getLatestArticles());
        //热门*3
        mv.addObject("popularArticles", blogService.getPopularArticles());
        //阅读量最多*5
        mv.addObject("mostReadingArticles",blogService.getMostReadingArticles());
        //sidebar栏  start
        mv.addObject("myUser", blogService.getMyUserByType(UserTypeEnum.博主.getIndex()));
        //精选*2
        mv.addObject("selectedArticles", blogService.getSelectedArticles());
        //分类文章
        List<Map> sonLabelList = blogService.getSonLabelList();
        mv.addObject("sonLabelList", sonLabelList);
        mv.addObject("classificationArticles", blogService.getClassificationArticles((Integer) sonLabelList.get(0).get("tabId")));
        //评论最多文章
        mv.addObject("mostCommentedArticles", blogService.getMostCommentedArticles());
        //sidebar栏  end
        mv.setViewName("index");
        return mv;
    }

    @ApiOperation(value = "下拉文章")
    @PostMapping("/getClassificationArticles")
    public ResponseModel getClassificationArticles(@Param("labelId") Integer labelId) {
        List<MyBlog> classificationArticles = blogService.getClassificationArticles(labelId);
        return ResponseModel.success(classificationArticles);
    }

    @ApiOperation(value = "搜索")
    @PostMapping("/search")
    public ModelAndView getSearch(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page, @Param("title") String title) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("myLabelList",blogService.getMyLabelList());
        //sidebar栏  start
        mv.addObject("myUser", blogService.getMyUserByType(UserTypeEnum.博主.getIndex()));
        //精选*2
        mv.addObject("selectedArticles", blogService.getSelectedArticles());
        //分类文章
        List<Map> sonLabelList = blogService.getSonLabelList();
        mv.addObject("sonLabelList", sonLabelList);
        mv.addObject("classificationArticles", blogService.getClassificationArticles((Integer) sonLabelList.get(0).get("tabId")));
        //评论最多文章
        mv.addObject("mostCommentedArticles", blogService.getMostCommentedArticles());
        //sidebar栏  end

        Map params = new HashMap();
        params.put("title", title);
        params.put("page", page);
        PageHelper.startPage(page, 8);
        List<MyBlog> myBlogList = blogService.selectMyBlogByPage(params);
        PageInfo<MyBlog> myBlogPageInfo = new PageInfo<>(myBlogList);
        mv.addObject("PageInfo", myBlogPageInfo);
        mv.addObject("url", "/free/search");
        String paramsId = "title=" + title;
        mv.addObject("paramsId", paramsId);
        mv.addObject("params", title);
        mv.setViewName("search-result");
        return mv;
    }

    @ApiOperation(value = "关于博主")
    @PostMapping("/about")
    public ModelAndView getAbout() {
        ModelAndView mv = new ModelAndView();
        //标签tab
        List<Map> blogTabs = new ArrayList<>();
        Map map = new HashMap();
        map.put("url", "about");
        map.put("tabGrade", LabelGradeEnum.标题栏级.getIndex());
        Map thisLabelTab = blogService.getTabByTabUrl(map);
        mv.addObject("myTab", thisLabelTab);
        blogTabs.add(thisLabelTab);
        Integer sonId = (Integer) thisLabelTab.get("tabId");
        for (int i = 0; i < 3; i++) {
            Map labelTab = blogService.getTabBySonId(sonId);
            blogTabs.add(labelTab);
            if (LabelGradeEnum.标题栏级.getIndex() == (Integer) labelTab.get("tabId")) {
                break;
            } else {
                sonId = (Integer) labelTab.get("tabId");
            }
        }
        Collections.reverse(blogTabs);
        mv.addObject("blogTabs", blogTabs);

        List<MyAbout> aboutList = blogService.selectAll();
        mv.addObject("aboutList", aboutList);
        mv.setViewName("about");
        return mv;
    }

    @ApiOperation(value = "给我留言")
    @PostMapping("/contact")
    public ModelAndView getContact(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page) {
        ModelAndView mv = new ModelAndView();
        //标签tab
        List<Map> blogTabs = new ArrayList<>();
        Map map = new HashMap();
        map.put("url", "contact");
        map.put("tabGrade", LabelGradeEnum.标题栏级.getIndex());
        Map thisLabelTab = blogService.getTabByTabUrl(map);
        mv.addObject("myTab", thisLabelTab);
        blogTabs.add(thisLabelTab);
        Integer sonId = (Integer) thisLabelTab.get("tabId");
        for (int i = 0; i < 3; i++) {
            Map labelTab = blogService.getTabBySonId(sonId);
            blogTabs.add(labelTab);
            if (LabelGradeEnum.标题栏级.getIndex() == (Integer) labelTab.get("tabId")) {
                break;
            } else {
                sonId = (Integer) labelTab.get("tabId");
            }
        }
        Collections.reverse(blogTabs);
        mv.addObject("blogTabs", blogTabs);
        //留言板信息
        PageHelper.startPage(page, 10);
        List<MyLeavingMessage> myLeavingMessageList = blogService.selectLeavingMessageList();
        PageInfo<MyLeavingMessage> myLeavingMessagePageInfo = new PageInfo<>(myLeavingMessageList);
        for (MyLeavingMessage myLeavingMessage : myLeavingMessagePageInfo.getList()) {
            List<MyLeavingMessage> sonMessageList = blogService.selectLeavingMessageListByParentId(myLeavingMessage.getId());
            myLeavingMessage.setSonList(sonMessageList);
        }
        Map messageBoard = blogService.getMyMessageBoard(UserTypeEnum.博主.getIndex());
        mv.addObject("messageBoard", messageBoard);
        mv.addObject("PageInfo", myLeavingMessagePageInfo);
        mv.setViewName("contact");
        return mv;
    }

    @ApiOperation(value = "刷新留言")
    @PostMapping("/getNewContact")
    public ModelAndView getNewContact(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page) {
        ModelAndView mv = new ModelAndView();
        //留言板信息
        PageHelper.startPage(page, 10);
        List<MyLeavingMessage> myLeavingMessageList = blogService.selectLeavingMessageList();
        PageInfo<MyLeavingMessage> myLeavingMessagePageInfo = new PageInfo<>(myLeavingMessageList);
        for (MyLeavingMessage myLeavingMessage : myLeavingMessagePageInfo.getList()) {
            List<MyLeavingMessage> sonList = blogService.selectLeavingMessageListByParentId(myLeavingMessage.getId());
            myLeavingMessage.setSonList(sonList);
        }
        Map messageBoard = blogService.getMyMessageBoard(UserTypeEnum.博主.getIndex());
        mv.addObject("messageBoard", messageBoard);
        mv.addObject("PageInfo", myLeavingMessagePageInfo);
        mv.setViewName("contact::contact_refresh");
        return mv;
    }

    @ApiOperation(value = "博客详情")
    @PostMapping("/detail")
    public ModelAndView getDetail(@Param("blogId") Integer blogId) {
        ModelAndView mv = new ModelAndView();
        //sidebar栏  start
        mv.addObject("myUser", blogService.getMyUserByType(UserTypeEnum.博主.getIndex()));
        //精选*2
        mv.addObject("selectedArticles", blogService.getSelectedArticles());
        //分类文章
        List<Map> sonLabelList = blogService.getSonLabelList();
        mv.addObject("sonLabelList", sonLabelList);
        mv.addObject("classificationArticles", blogService.getClassificationArticles((Integer) sonLabelList.get(0).get("tabId")));
        //评论最多文章
        mv.addObject("mostCommentedArticles", blogService.getMostCommentedArticles());
        //sidebar栏  end

        //文章tab
        List<Map> blogTabs = new ArrayList<>();
        Map blogTab = blogService.getBlogTabByBlogId(blogId);
        blogTabs.add(blogTab);
        Map blogLabelTab = blogService.getTabByBlogId(blogId);
        blogTabs.add(blogLabelTab);
        Integer sonId = (Integer) blogLabelTab.get("tabId");
        for (int i = 0; i < 3; i++) {
            Map labelTab = blogService.getTabBySonId(sonId);
            blogTabs.add(labelTab);
            if (LabelGradeEnum.标题栏级.getIndex() == (Integer) labelTab.get("tabId")) {
                break;
            } else {
                sonId = (Integer) labelTab.get("tabId");
            }
        }
        Collections.reverse(blogTabs);
        mv.addObject("blogTabs", blogTabs);

        //文章详情
        MyBlog myBlog = blogService.selectMyBlogByPrimaryKey(blogId);
        List<Map> articleSonLabels = blogService.selectArticleSonLabels(blogId);
        blogService.updateReader(myBlog);
        List<MyLeavingMessage> commentList = blogService.selectCommentByBlogId(blogId);
        for (MyLeavingMessage myLeavingMessage : commentList) {
            List<MyLeavingMessage> sonList = blogService.selectListByParentId(myLeavingMessage.getId(), myLeavingMessage.getBlogId());
            myLeavingMessage.setSonList(sonList);
        }
        mv.addObject("blogDetail", myBlog);
        mv.addObject("articleSonLabels", articleSonLabels);
        mv.addObject("commentList", commentList);
        mv.setViewName("blog-details");
        return mv;
    }

    @ApiOperation(value = "分类标签列表")
    @PostMapping("/tab")
    public ModelAndView getTab(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page, @Param("tabId") Integer tabId) {
        ModelAndView mv = new ModelAndView();
        Map params = new HashMap();
        params.put("tabId", tabId);
        params.put("page", page);
        //类别信息
        MyLabel myLabelDetail = blogService.selectMyLabelByPrimaryKey(tabId);
        mv.addObject("myTab", myLabelDetail);
        PageHelper.startPage(page, 6);
        List<MyLabel> myLabelsList = blogService.selectMyLabelByPage(params);
        PageInfo<MyLabel> myLabelPageInfo = new PageInfo<>(myLabelsList);
        mv.addObject("PageInfo", myLabelPageInfo);
        mv.addObject("url", "/free/tab");
        String paramsId = "tabId=" + tabId;
        mv.addObject("paramsId", paramsId);
        //标签tab
        List<Map> blogTabs = new ArrayList<>();
        Map thisLabelTab = blogService.getTabByTabId(tabId);
        blogTabs.add(thisLabelTab);
        Integer sonId = (Integer) thisLabelTab.get("tabId");
        for (int i = 0; i < 3; i++) {
            Map labelTab = blogService.getTabBySonId(sonId);
            blogTabs.add(labelTab);
            if (LabelGradeEnum.标题栏级.getIndex() == (Integer) labelTab.get("tabId")) {
                break;
            } else {
                sonId = (Integer) labelTab.get("tabId");
            }
        }
        Collections.reverse(blogTabs);
        mv.addObject("blogTabs", blogTabs);

        mv.setViewName("categories");
        return mv;
    }

    @ApiOperation(value = "分类标签详情")
    @PostMapping("/label")
    public ModelAndView getLabel(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page, @Param("tabId") Integer tabId) {
        ModelAndView mv = new ModelAndView();
        //sidebar栏  start
        mv.addObject("myUser", blogService.getMyUserByType(UserTypeEnum.博主.getIndex()));
        //精选*2
        mv.addObject("selectedArticles", blogService.getSelectedArticles());
        //分类文章
        List<Map> sonLabelList = blogService.getSonLabelList();
        mv.addObject("sonLabelList", sonLabelList);
        mv.addObject("classificationArticles", blogService.getClassificationArticles((Integer) sonLabelList.get(0).get("tabId")));
        //评论最多文章
        mv.addObject("mostCommentedArticles", blogService.getMostCommentedArticles());
        //sidebar栏  end

        Map params = new HashMap();
        params.put("label", tabId);
        params.put("page", page);
        //类别信息
        MyLabel myLabel = blogService.selectMyLabelByPrimaryKey(tabId);
        mv.addObject("myLabel", myLabel);
        PageHelper.startPage(page, 8);
        List<MyBlog> myBlogList = blogService.selectMyBlogByPage(params);
        PageInfo<MyBlog> myBlogPageInfo = new PageInfo<>(myBlogList);
        mv.addObject("PageInfo", myBlogPageInfo);
        mv.addObject("url", "/free/label");
        String paramsId = "tabId=" + tabId;
        mv.addObject("paramsId", paramsId);
        //标签tab
        List<Map> blogTabs = new ArrayList<>();
        Map thisLabelTab = blogService.getTabByTabId(tabId);
        blogTabs.add(thisLabelTab);
        Integer sonId = (Integer) thisLabelTab.get("tabId");
        for (int i = 0; i < 3; i++) {
            Map labelTab = blogService.getTabBySonId(sonId);
            blogTabs.add(labelTab);
            if (LabelGradeEnum.标题栏级.getIndex() == (Integer) labelTab.get("tabId")) {
                break;
            } else {
                sonId = (Integer) labelTab.get("tabId");
            }
        }
        Collections.reverse(blogTabs);
        mv.addObject("blogTabs", blogTabs);

        mv.setViewName("category-result");
        return mv;
    }

    @ApiOperation(value = "博客订阅")
    @PostMapping("/subscribe")
    public ResponseModel getSubscribe(@Param("email") String email) {
        return blogService.getSubscribe(email);
    }

    @ApiOperation(value = "留言")
    @PostMapping("/leaveAMessage")
    public ResponseModel setleaveAMessage(RequestModel requestModel) {
        return blogService.setleaveAMessage(requestModel);
    }

    @ApiOperation(value = "评论")
    @PostMapping("/setCommont")
    public ResponseModel setCommont(RequestModel requestModel) {
        return blogService.setCommont(requestModel);
    }

    @ApiOperation(value = "刷新评论")
    @PostMapping("/getNewCommont")
    public ModelAndView getNewCommont(@Param("blogId") Integer blogId) {
        ModelAndView mv = new ModelAndView();
        //文章详情
        MyBlog myBlog = blogService.selectMyBlogByPrimaryKey(blogId);
        List<Map> articleSonLabels = blogService.selectArticleSonLabels(blogId);
        myBlog.setReader(myBlog.getReader() + 1);
        blogService.updateReader(myBlog);
        List<MyLeavingMessage> commentList = blogService.selectCommentByBlogId(blogId);
        for (MyLeavingMessage myLeavingMessage : commentList) {
            List<MyLeavingMessage> sonList = blogService.selectListByParentId(myLeavingMessage.getId(), myLeavingMessage.getBlogId());
            myLeavingMessage.setSonList(sonList);
        }
        mv.addObject("blogDetail", myBlog);
        mv.addObject("articleSonLabels", articleSonLabels);
        mv.addObject("commentList", commentList);
        mv.setViewName("blog-details::detail_refresh");
        return mv;
    }

    @ApiOperation(value = "错误")
    @RequestMapping("/error")
    public ModelAndView getError() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("myLabelList",blogService.getMyLabelList());
        mv.setViewName("error");
        return mv;
    }

    @ApiOperation(value = "错误")
    @RequestMapping("/getError404")
    public ModelAndView getError404() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("404");
        return mv;
    }
}
