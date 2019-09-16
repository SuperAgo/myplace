package com.myplace.models.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myplace.common.page.RequestModel;
import com.myplace.common.page.ResponseModel;
import com.myplace.common.utils.RedisUtil;
import com.myplace.common.utils.SensitiveWord;
import com.myplace.models.dao.*;
import com.myplace.models.entity.*;
import com.myplace.models.entity.enums.LabelGradeEnum;
import com.myplace.models.entity.enums.UserTypeEnum;
import com.myplace.models.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
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
    @Resource
    private MyAboutMapper myAboutMapper;
    @Autowired
    private RedisUtil redisUtil;


    @Override
    @Cacheable(value = "myUser", key = "#index")
    public Map getMyUserByType(int index) {
        return myUserMapper.selectByType(index);
    }

    @Override
    @Cacheable(value = "blogTab", key = "#blogId")
    public Map getBlogTabByBlogId(Integer blogId) {
        return myLabelMapper.getBlogTabByBlogId(blogId);
    }

    @Override
    @Cacheable(value = "thisLabelTab", key = "#tabId")
    public Map getTabByTabId(Integer tabId) {
        return myLabelMapper.getTabByTabId(tabId);
    }

    @Override
    @Cacheable(value = "myLabelsList", key = "#params")
    public List<MyLabel> selectMyLabelByPage(Map params) {
        return myLabelMapper.selectMyLabelByPage(params);
    }

    @Override
    @Cacheable(value = "myLabelDetail", key = "#myLabelId")
    public MyLabel selectMyLabelByPrimaryKey(Integer myLabelId) {
        return myLabelMapper.selectByPrimaryKey(myLabelId);
    }

    @Override
    @Cacheable(value = "messageBoard")
    public Map getMyMessageBoard(int index) {
        return myUserMapper.getMyMessageBoard(index);
    }

    @Override
    @CachePut(value = "sonMessageList", key = "#parentId")
    public List<MyLeavingMessage> selectLeavingMessageListByParentId(Integer parentId) {
        return myLeavingMessageMapper.selectLeavingMessageListByParentId(parentId);
    }

    @Override
    @CachePut(value = "myLeavingMessageList")
    public List<MyLeavingMessage> selectLeavingMessageList() {
        return myLeavingMessageMapper.selectLeavingMessageList();
    }

    @Override
    @CachePut(value = "sonLeavingMessageList", key = "#sonLeavingMessageListBlogId")
    public List<MyLeavingMessage> selectListByParentId(Integer myLeavingMessageId, Integer sonLeavingMessageListBlogId) {
        return myLeavingMessageMapper.selectListByParentId(myLeavingMessageId, sonLeavingMessageListBlogId);
    }

    @Override
    @CachePut(value = "commentList", key = "#commentListBlogId")
    public List<MyLeavingMessage> selectCommentByBlogId(Integer commentListBlogId) {
        return myLeavingMessageMapper.selectCommentByBlogId(commentListBlogId);
    }

    @Override
    public void updateReader(MyBlog myBlog) {
        myBlogMapper.updateReader(myBlog);
    }

    @Override
    @Cacheable(value = "articleSonLabels", key = "#articleSonLabelsId")
    public List<Map> selectArticleSonLabels(Integer articleSonLabelsId) {
        return myLabelMapper.selectArticleSonLabels(articleSonLabelsId);
    }

    @Override
    @CachePut(value = "myBlog", key = "#myBlogId")
    public MyBlog selectMyBlogByPrimaryKey(Integer myBlogId) {
        MyBlog myBlog = myBlogMapper.selectByPrimaryKey(myBlogId);
        myBlog.setReader(myBlog.getReader() + 1);
        return myBlog;
    }

    @Override
    @Cacheable(value = "blogLabelTab", key = "#blogLabelTabId")
    public Map getTabByBlogId(Integer blogLabelTabId) {
        return myLabelMapper.getTabByBlogId(blogLabelTabId);
    }

    @Override
    @CachePut(value = "aboutList")
    public List<MyAbout> selectAll() {
        return myAboutMapper.selectAll();
    }

    @Override
    @Cacheable(value = "labelTab", key = "#sonId")
    public Map getTabBySonId(Integer sonId) {
        return myLabelMapper.getTabBySonId(sonId);
    }

    @Override
    @Cacheable(value = "thisLabelTab", key = "#map")
    public Map getTabByTabUrl(Map map) {
        return myLabelMapper.getTabByTabUrl(map);
    }

    @Override
    @Cacheable(value = "myBlogList", key = "#params")
    public List<MyBlog> selectMyBlogByPage(Map params) {
        return myBlogMapper.selectMyBlogByPage(params);
    }

    @Override
    @Cacheable(value = "mostReadingArticles")
    public List<MyBlog> getMostReadingArticles() {
        return myBlogMapper.getMostReadingArticles();
    }

    @Override
    @Cacheable(value = "popularArticles")
    public List<MyBlog> getPopularArticles() {
        return myBlogMapper.getPopularArticles();
    }

    @Override
    @Cacheable(value = "latestArticles")
    public List<MyBlog> getLatestArticles() {
        return myBlogMapper.getLatestArticles();
    }

    @Override
    @Cacheable(value = "carouselArticle")
    public List<MyBlog> getCarouselArticle() {
        return myBlogMapper.getCarouselArticle();
    }

    @Override
    @Cacheable(value = "myLabelList")
    public List<Map> getMyLabelList() {
        List<Map> myLabelList = myLabelMapper.getLabelByGradeId(LabelGradeEnum.标题栏级.getIndex());
        for (Map myLabel : myLabelList) {
            List<Map> sonLabelList = myLabelMapper.getLabelByParentId((Integer) myLabel.get("id"));
            myLabel.put("sonLabelList", sonLabelList);
        }
        return myLabelList;
    }

    @Override
    @Cacheable(value = "mostCommentedArticles")
    public List<List> getMostCommentedArticles() {
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
        return mostCommentedArticleList;
    }

    @Override
    @Cacheable(value = "sonLabelList")
    public List<Map> getSonLabelList() {
        return myLabelMapper.getSonLabelList();
    }

    @Override
    @Cacheable(value = "selectedArticles")
    public List<MyBlog> getSelectedArticles() {
        return myBlogMapper.getSelectedArticles();
    }

    @Override
    public ModelAndView getAudio() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("mp3");
        return mv;
    }

    @Override
    @Cacheable(value = "classificationArticles", key = "#labelId")
    public List<MyBlog> getClassificationArticles(Integer labelId) {
        return myBlogMapper.getClassificationArticles(labelId);
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
        try {
            MyLeavingMessage myLeavingMessage = JSON.parseObject(JSON.toJSONString(requestModel.getParams()), MyLeavingMessage.class);
            String message = myLeavingMessage.getMessage();
            boolean flag = SensitiveWord.checkSenstiveWord(message);
            if (flag) {
                message = SensitiveWord.filterInfoAfter(message);
            }
            myLeavingMessage.setMessage(message);
            myLeavingMessage.setInsTime(new Date());
            int i = myLeavingMessageMapper.insertSelective(myLeavingMessage);
            if (i < 1) {
                return ResponseModel.error("留言失败！");
            }
            MyUser myUser = myUserMapper.selectByEmail(myLeavingMessage.getEmail());
            if (null == myUser) {
                myUser = new MyUser();
                myUser.setEmail(myLeavingMessage.getEmail());
                myUser.setNickName(myLeavingMessage.getName());
                String Path = "networkAvatar.json";
                BufferedReader reader = null;
                InputStreamReader read = null;
                String laststr = "";
                read = new InputStreamReader(SensitiveWord.class.getClassLoader().getResourceAsStream(Path), "utf-8");
                reader = new BufferedReader(read);
                String tempString = null;
                while ((tempString = reader.readLine()) != null) {
                    laststr += tempString;
                }
                reader.close();
                JSONArray urlList = JSON.parseArray(laststr);
                Random rd = new Random();
                //定义了一个整形a获得16以内的随机整数
                //通过更改nextInt()中的数字来更改获取随机数的范围
                int a = rd.nextInt(16);
                myUser.setNetworkAvatar(urlList.getString((a - 1)));
                myUser.setInsTime(new Date());
                myUser.setType(UserTypeEnum.其他.getIndex());
                myUserMapper.insertSelective(myUser);
            }
            return ResponseModel.success("留言成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseModel.error("留言失败！");
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseModel setCommont(RequestModel requestModel) {
        try {
            MyLeavingMessage myLeavingMessage = JSON.parseObject(JSON.toJSONString(requestModel.getParams()), MyLeavingMessage.class);
            String message = myLeavingMessage.getMessage();
            boolean flag = SensitiveWord.checkSenstiveWord(message);
            if (flag) {
                message = SensitiveWord.filterInfoAfter(message);
            }
            myLeavingMessage.setMessage(message);
            myLeavingMessage.setInsTime(new Date());
            MyUser myUser = myUserMapper.selectByEmail(myLeavingMessage.getEmail());
            if (null == myUser) {
                myUser = new MyUser();
                myUser.setEmail(myLeavingMessage.getEmail());
                myUser.setNickName(myLeavingMessage.getName());
                String Path = "networkAvatar.json";
                BufferedReader reader = null;
                InputStreamReader read = null;
                String laststr = "";
                read = new InputStreamReader(SensitiveWord.class.getClassLoader().getResourceAsStream(Path), "utf-8");
                reader = new BufferedReader(read);
                String tempString = null;
                while ((tempString = reader.readLine()) != null) {
                    laststr += tempString;
                }
                reader.close();
                JSONArray urlList = JSON.parseArray(laststr);
                Random rd = new Random();
                //定义了一个整形a获得16以内的随机整数
                //通过更改nextInt()中的数字来更改获取随机数的范围
                int a = rd.nextInt(16);
                myUser.setNetworkAvatar(urlList.getString((a - 1)));
                myUser.setInsTime(new Date());
                myUser.setType(UserTypeEnum.其他.getIndex());
                myUserMapper.insertSelective(myUser);
            }
            int i = myLeavingMessageMapper.insertSelective(myLeavingMessage);
            MyBlog myBlog = myBlogMapper.selectByPrimaryKey(myLeavingMessage.getBlogId());
            myBlog.setComments(myBlog.getComments() + 1);
            int x = myBlogMapper.updateByPrimaryKeySelective(myBlog);
            if (null != myLeavingMessage.getParentId()) {
                MyLeavingMessage myParentLeavingMessage = myLeavingMessageMapper.selectByPrimaryKey(myLeavingMessage.getParentId());
                myParentLeavingMessage.setComments(myParentLeavingMessage.getComments() + 1);
                int y = myLeavingMessageMapper.updateByPrimaryKeySelective(myParentLeavingMessage);
                if ((x + i + y) < 3) {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return ResponseModel.error("评论失败！");
                }
            } else {
                if ((x + i) < 2) {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return ResponseModel.error("评论失败！");
                }
            }
            return ResponseModel.success("评论成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseModel.error("评论失败！");
    }

}
