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
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

@Service("blogService")
public class BlogServiceImpl implements BlogService {
    @Resource
    private MyLabelMapper myLabelMapper;
    @Resource
    private MyBlogMapper myBlogMapper;
    @Resource
    private MyUserMapper myUserMapper;
    @Resource
    private MyLeavingMessageMapper myLeavingMessageMapper;

    @Override
    public ModelAndView getIndex() {
        ModelAndView mv = new ModelAndView();
        //header栏   start
        List<Map> myLabelList = myLabelMapper.getLabelByGradeId(LabelGradeEnum.标题栏级.getIndex());
        for (Map myLabel:myLabelList) {
            List<Map> sonLabelList = myLabelMapper.getLabelByParentId((Integer)myLabel.get("id"));
            myLabel.put("sonLabelList",sonLabelList);
        }
        mv.addObject("myLabelList", myLabelList);
        //header栏   end
        //轮播*3
        List<MyBlog> carouselArticle = myBlogMapper.getCarouselArticle();
        mv.addObject("carouselArticle", carouselArticle);
        //最新*3
        List<MyBlog> latestArticles = myBlogMapper.getLatestArticles();
        mv.addObject("latestArticles", latestArticles);
        //热门*3
        List<MyBlog> popularArticles = myBlogMapper.getPopularArticles();
        mv.addObject("popularArticles", popularArticles);
        //阅读量最多*5
        List<MyBlog> mostReadingArticles = myBlogMapper.getMostReadingArticles();
        mv.addObject("mostReadingArticles", mostReadingArticles);

        //sidebar栏  start
        //关于博主
        Map myUser = myUserMapper.selectByType(UserTypeEnum.博主.getIndex());
        mv.addObject("myUser", myUser);
        //精选*2
        List<MyBlog> selectedArticles = myBlogMapper.getSelectedArticles();
        mv.addObject("selectedArticles", selectedArticles);
        //分类文章
        List<Map> sonLabelList = myLabelMapper.getSonLabelList();
        List<MyBlog> classificationArticles = myBlogMapper.getClassificationArticles((Integer) sonLabelList.get(0).get("tabId"));
        mv.addObject("sonLabelList", sonLabelList);
        mv.addObject("classificationArticles", classificationArticles);
        //评论最多文章
        List<MyBlog> mostCommentedArticles = myBlogMapper.getMostCommentedArticles();
        List<List> mostCommentedArticleList = new ArrayList();
        List<MyBlog> page = new ArrayList<>();
        for (MyBlog myBlog : mostCommentedArticles) {
            if (page.size() < 2) {
                page.add(myBlog);
                if (page.size() == 2) {
                    mostCommentedArticleList.add(page);
                    page = new ArrayList<>();
                }
            }
        }
        mv.addObject("mostCommentedArticles", mostCommentedArticleList);
        //sidebar栏  end
        mv.setViewName("index");
        return mv;
    }

    @Override
    public ModelAndView getDetail(Integer blogId) {
        ModelAndView mv = new ModelAndView();
        //header栏   start
        List<Map> myLabelList = myLabelMapper.getLabelByGradeId(LabelGradeEnum.标题栏级.getIndex());
        for (Map myLabel:myLabelList) {
            List<Map> sonLabelList = myLabelMapper.getLabelByParentId((Integer)myLabel.get("id"));
            myLabel.put("sonLabelList",sonLabelList);
        }
        mv.addObject("myLabelList", myLabelList);
        //header栏   end
        //sidebar栏  start
        //关于博主
        Map myUser = myUserMapper.selectByType(UserTypeEnum.博主.getIndex());
        mv.addObject("myUser", myUser);
        //精选*2
        mv.addObject("selectedArticles", myBlogMapper.getSelectedArticles());
        //分类文章
        List<Map> sonLabelList = myLabelMapper.getSonLabelList();
        List<MyBlog> classificationArticles = myBlogMapper.getClassificationArticles((Integer) sonLabelList.get(0).get("tabId"));
        mv.addObject("sonLabelList", sonLabelList);
        mv.addObject("classificationArticles", classificationArticles);
        //评论最多文章
        List<MyBlog> mostCommentedArticles = myBlogMapper.getMostCommentedArticles();
        List<List> mostCommentedArticleList = new ArrayList();
        List<MyBlog> page = new ArrayList<>();
        for (MyBlog myBlog : mostCommentedArticles) {
            if (page.size() < 2) {
                page.add(myBlog);
                if (page.size() == 2) {
                    mostCommentedArticleList.add(page);
                    page = new ArrayList<>();
                }
            }
        }
        mv.addObject("mostCommentedArticles", mostCommentedArticleList);
        //sidebar栏  end

        //文章tab
        List<Map> blogTabs = new ArrayList<>();
        Map blogTab = myLabelMapper.getBlogTabByBlogId(blogId);
        blogTabs.add(blogTab);
        Map blogLabelTab = myLabelMapper.getTabByBlogId(blogId);
        blogTabs.add(blogLabelTab);
        Integer sonId = (Integer) blogLabelTab.get("tabId");
        for (int i = 0; i < 3; i++) {
            Map labelTab = myLabelMapper.getTabBySonId(sonId);
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
        MyBlog myBlog = myBlogMapper.selectByPrimaryKey(blogId);
        List<Map> articleSonLabels = myLabelMapper.selectArticleSonLabels(blogId);
        myBlog.setReader(myBlog.getReader() + 1);
        myBlogMapper.updateByPrimaryKeySelective(myBlog);
        mv.addObject("blogDetail", myBlog);
        mv.addObject("articleSonLabels", articleSonLabels);
        mv.setViewName("blog-details");
        return mv;
    }

    @Override
    public ModelAndView getTab(Integer pageNum, Integer tabId) {
        ModelAndView mv = new ModelAndView();
        //header栏   start
        List<Map> myLabelList = myLabelMapper.getLabelByGradeId(LabelGradeEnum.标题栏级.getIndex());
        for (Map myLabel:myLabelList) {
            List<Map> sonLabelList = myLabelMapper.getLabelByParentId((Integer)myLabel.get("id"));
            myLabel.put("sonLabelList",sonLabelList);
        }
        mv.addObject("myLabelList", myLabelList);
        //header栏   end
        //sidebar栏  start
        //关于博主
        Map myUser = myUserMapper.selectByType(UserTypeEnum.博主.getIndex());
        mv.addObject("myUser", myUser);
        //精选*2
        mv.addObject("selectedArticles", myBlogMapper.getSelectedArticles());
        //分类文章
        List<Map> sonLabelList = myLabelMapper.getSonLabelList();
        List<MyBlog> classificationArticles = myBlogMapper.getClassificationArticles((Integer) sonLabelList.get(0).get("tabId"));
        mv.addObject("sonLabelList", sonLabelList);
        mv.addObject("classificationArticles", classificationArticles);
        //评论最多文章
        List<MyBlog> mostCommentedArticles = myBlogMapper.getMostCommentedArticles();
        List<List> mostCommentedArticleList = new ArrayList();
        List<MyBlog> page = new ArrayList<>();
        for (MyBlog myBlog : mostCommentedArticles) {
            if (page.size() < 2) {
                page.add(myBlog);
                if (page.size() == 2) {
                    mostCommentedArticleList.add(page);
                    page = new ArrayList<>();
                }
            }
        }
        mv.addObject("mostCommentedArticles", mostCommentedArticleList);
        //sidebar栏  end
        Map params = new HashMap();
        params.put("tabId", tabId);
        //类别信息
        MyLabel myLabel = myLabelMapper.selectByPrimaryKey(tabId);
        mv.addObject("myTab", myLabel);
        PageHelper.startPage(pageNum, 6);
        List<MyLabel> myLabelsList = myLabelMapper.selectMyLabelByPage(params);
        PageInfo<MyLabel> myLabelPageInfo = new PageInfo<>(myLabelsList);
        mv.addObject("PageInfo", myLabelPageInfo);
        mv.addObject("url", "/blog/tab");
        String paramsId = "tabId=" + tabId;
        mv.addObject("paramsId", paramsId);
        //标签tab
        List<Map> blogTabs = new ArrayList<>();
        Map thisLabelTab = myLabelMapper.getTabByTabId(tabId);
        blogTabs.add(thisLabelTab);
        Integer sonId = (Integer) thisLabelTab.get("tabId");
        for (int i = 0; i < 3; i++) {
            Map labelTab = myLabelMapper.getTabBySonId(sonId);
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

    @Override
    public ModelAndView getLabel(Integer pageNum, Integer tabId) {
        ModelAndView mv = new ModelAndView();
        //header栏   start
        List<Map> myLabelList = myLabelMapper.getLabelByGradeId(LabelGradeEnum.标题栏级.getIndex());
        for (Map myLabel:myLabelList) {
            List<Map> sonLabelList = myLabelMapper.getLabelByParentId((Integer)myLabel.get("id"));
            myLabel.put("sonLabelList",sonLabelList);
        }
        mv.addObject("myLabelList", myLabelList);
        //header栏   end
        //sidebar栏  start
        //关于博主
        Map myUser = myUserMapper.selectByType(UserTypeEnum.博主.getIndex());
        mv.addObject("myUser", myUser);
        //精选*2
        mv.addObject("selectedArticles", myBlogMapper.getSelectedArticles());
        //分类文章
        List<Map> sonLabelList = myLabelMapper.getSonLabelList();
        List<MyBlog> classificationArticles = myBlogMapper.getClassificationArticles((Integer) sonLabelList.get(0).get("tabId"));
        mv.addObject("sonLabelList", sonLabelList);
        mv.addObject("classificationArticles", classificationArticles);
        //评论最多文章
        List<MyBlog> mostCommentedArticles = myBlogMapper.getMostCommentedArticles();
        List<List> mostCommentedArticleList = new ArrayList();
        List<MyBlog> page = new ArrayList<>();
        for (MyBlog myBlog : mostCommentedArticles) {
            if (page.size() < 2) {
                page.add(myBlog);
                if (page.size() == 2) {
                    mostCommentedArticleList.add(page);
                    page = new ArrayList<>();
                }
            }
        }
        mv.addObject("mostCommentedArticles", mostCommentedArticleList);
        //sidebar栏  end
        Map params = new HashMap();
        params.put("label", tabId);
        //类别信息
        MyLabel myLabel = myLabelMapper.selectByPrimaryKey(tabId);
        mv.addObject("myLabel", myLabel);
        PageHelper.startPage(pageNum, 1);
        List<MyBlog> myBlogList = myBlogMapper.selectMyBlogByPage(params);
        PageInfo<MyBlog> myBlogPageInfo = new PageInfo<>(myBlogList);
        mv.addObject("PageInfo", myBlogPageInfo);
        mv.addObject("url", "/blog/label");
        String paramsId = "tabId=" + tabId;
        mv.addObject("paramsId", paramsId);
        //标签tab
        List<Map> blogTabs = new ArrayList<>();
        Map thisLabelTab = myLabelMapper.getTabByTabId(tabId);
        blogTabs.add(thisLabelTab);
        Integer sonId = (Integer) thisLabelTab.get("tabId");
        for (int i = 0; i < 3; i++) {
            Map labelTab = myLabelMapper.getTabBySonId(sonId);
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

    @Override
    public ModelAndView getAbout() {
        ModelAndView mv = new ModelAndView();
        //header栏   start
        List<Map> myLabelList = myLabelMapper.getLabelByGradeId(LabelGradeEnum.标题栏级.getIndex());
        for (Map myLabel:myLabelList) {
            List<Map> sonLabelList = myLabelMapper.getLabelByParentId((Integer)myLabel.get("id"));
            myLabel.put("sonLabelList",sonLabelList);
        }
        mv.addObject("myLabelList", myLabelList);
        //header栏   end
        //sidebar栏  start
        //精选*2
        mv.addObject("selectedArticles", myBlogMapper.getSelectedArticles());
        //分类文章
        List<Map> sonLabelList = myLabelMapper.getSonLabelList();
        List<MyBlog> classificationArticles = myBlogMapper.getClassificationArticles((Integer) sonLabelList.get(0).get("tabId"));
        mv.addObject("sonLabelList", sonLabelList);
        mv.addObject("classificationArticles", classificationArticles);
        //评论最多文章
        List<MyBlog> mostCommentedArticles = myBlogMapper.getMostCommentedArticles();
        List<List> mostCommentedArticleList = new ArrayList();
        List<MyBlog> page = new ArrayList<>();
        for (MyBlog myBlog : mostCommentedArticles) {
            if (page.size() < 2) {
                page.add(myBlog);
                if (page.size() == 2) {
                    mostCommentedArticleList.add(page);
                    page = new ArrayList<>();
                }
            }
        }
        mv.addObject("mostCommentedArticles", mostCommentedArticleList);
        //sidebar栏  end
        //标签tab
        List<Map> blogTabs = new ArrayList<>();
        Map map = new HashMap();
        map.put("url", "about");
        map.put("tabGrade", LabelGradeEnum.标题栏级.getIndex());
        Map thisLabelTab = myLabelMapper.getTabByTabUrl(map);
        mv.addObject("myTab", thisLabelTab);
        blogTabs.add(thisLabelTab);
        Integer sonId = (Integer) thisLabelTab.get("tabId");
        for (int i = 0; i < 3; i++) {
            Map labelTab = myLabelMapper.getTabBySonId(sonId);
            blogTabs.add(labelTab);
            if (LabelGradeEnum.标题栏级.getIndex() == (Integer) labelTab.get("tabId")) {
                break;
            } else {
                sonId = (Integer) labelTab.get("tabId");
            }
        }
        Collections.reverse(blogTabs);
        mv.addObject("blogTabs", blogTabs);

        Map myUser = myUserMapper.selectByType(UserTypeEnum.博主.getIndex());
        mv.addObject("myUser", myUser);
        mv.setViewName("about");
        return mv;
    }

    @Override
    public ModelAndView getContact() {
        ModelAndView mv = new ModelAndView();
        //header栏   start
        List<Map> myLabelList = myLabelMapper.getLabelByGradeId(LabelGradeEnum.标题栏级.getIndex());
        for (Map myLabel:myLabelList) {
            List<Map> sonLabelList = myLabelMapper.getLabelByParentId((Integer)myLabel.get("id"));
            myLabel.put("sonLabelList",sonLabelList);
        }
        mv.addObject("myLabelList", myLabelList);
        //header栏   end

        //标签tab
        List<Map> blogTabs = new ArrayList<>();
        Map map = new HashMap();
        map.put("url", "contact");
        map.put("tabGrade", LabelGradeEnum.标题栏级.getIndex());
        Map thisLabelTab = myLabelMapper.getTabByTabUrl(map);
        mv.addObject("myTab", thisLabelTab);
        blogTabs.add(thisLabelTab);
        Integer sonId = (Integer) thisLabelTab.get("tabId");
        for (int i = 0; i < 3; i++) {
            Map labelTab = myLabelMapper.getTabBySonId(sonId);
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
        Map messageBoard = myUserMapper.getMyMessageBoard(UserTypeEnum.博主.getIndex());
        mv.addObject("messageBoard", messageBoard);
        mv.setViewName("contact");
        return mv;
    }

    @Override
    public ResponseModel getClassificationArticles(Integer labelId) {
        List<MyBlog> classificationArticles = myBlogMapper.getClassificationArticles(labelId);
        return ResponseModel.success(classificationArticles);
    }

    @Override
    public ModelAndView getScarch(Integer pageNum, String title) {
        ModelAndView mv = new ModelAndView();
        //header栏   start
        List<Map> myLabelList = myLabelMapper.getLabelByGradeId(LabelGradeEnum.标题栏级.getIndex());
        for (Map myLabel:myLabelList) {
            List<Map> sonLabelList = myLabelMapper.getLabelByParentId((Integer)myLabel.get("id"));
            myLabel.put("sonLabelList",sonLabelList);
        }
        mv.addObject("myLabelList", myLabelList);
        //header栏   end
        Map params = new HashMap();
        params.put("title", title);
        PageHelper.startPage(pageNum, 8);
        List<MyBlog> myBlogList = myBlogMapper.selectMyBlogByPage(params);
        PageInfo<MyBlog> myBlogPageInfo = new PageInfo<>(myBlogList);
        mv.addObject("PageInfo", myBlogPageInfo);
        mv.addObject("url", "/blog/scarch");
        String paramsId = "title=" + title;
        mv.addObject("paramsId", paramsId);
        mv.addObject("params", title);
        mv.setViewName("search-result");
        return mv;
    }

    @Override
    public ResponseModel getSubscribe(String email) {
        if (StringUtils.isEmpty(email)) {
            return ResponseModel.error("请输入邮箱！");
        }
        MyUser myUser = myUserMapper.selectByEmail(email);
        if (null == myUser) {
            myUser = new MyUser();
            myUser.setEmail(email);
            myUser.setSubscribe(1);
            int i = myUserMapper.insertSelective(myUser);
            if (i < 1) {
                return ResponseModel.error("订阅失败！");
            }
        } else {
            if (1 == myUser.getSubscribe()) {
                return ResponseModel.success("您已经订阅过了哦！");
            } else {
                myUser.setSubscribe(1);
                int i = myUserMapper.updateByPrimaryKeySelective(myUser);
                if (i < 1) {
                    return ResponseModel.error("订阅失败！");
                }
            }
        }
        return ResponseModel.success("订阅成功！");
    }

    @Override
    public ResponseModel setleaveAMessage(RequestModel requestModel) {
        MyLeavingMessage myLeavingMessage = JSON.parseObject(JSON.toJSONString(requestModel.getParams()), MyLeavingMessage.class);
        myLeavingMessage.setInsTime(new Date());
        int i = myLeavingMessageMapper.insertSelective(myLeavingMessage);
        if (i < 1) {
            return ResponseModel.error("留言成功！");
        }
        return ResponseModel.success("留言成功！");
    }
}