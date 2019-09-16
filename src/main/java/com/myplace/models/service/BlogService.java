package com.myplace.models.service;


import com.myplace.common.page.RequestModel;
import com.myplace.common.page.ResponseModel;
import com.myplace.models.entity.MyAbout;
import com.myplace.models.entity.MyBlog;
import com.myplace.models.entity.MyLabel;
import com.myplace.models.entity.MyLeavingMessage;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

public interface BlogService {
    List<MyBlog> getClassificationArticles(Integer labelId);

    ResponseModel getSubscribe(String email);

    ResponseModel setleaveAMessage(RequestModel requestModel);

    ResponseModel setCommont(RequestModel requestModel);

    ModelAndView getAudio();

    Map getMyUserByType(int index);

    List<MyBlog> getSelectedArticles();

    List<Map> getSonLabelList();

    List<List> getMostCommentedArticles();

    List<Map> getMyLabelList();

    List<MyBlog> getCarouselArticle();

    List<MyBlog> getLatestArticles();

    List<MyBlog> getPopularArticles();

    List<MyBlog> getMostReadingArticles();

    List<MyBlog> selectMyBlogByPage(Map params);

    Map getTabByTabUrl(Map map);

    Map getTabBySonId(Integer sonId);

    List<MyAbout> selectAll();

    Map getBlogTabByBlogId(Integer blogId);

    Map getTabByBlogId(Integer blogId);

    MyBlog selectMyBlogByPrimaryKey(Integer blogId);

    List<Map> selectArticleSonLabels(Integer blogId);

    void updateReader(MyBlog myBlog);

    List<MyLeavingMessage> selectCommentByBlogId(Integer blogId);

    List<MyLeavingMessage> selectListByParentId(Integer myLeavingMessageId, Integer blogId);

    List<MyLeavingMessage> selectLeavingMessageList();

    List<MyLeavingMessage> selectLeavingMessageListByParentId(Integer myLeavingMessageId);

    Map getMyMessageBoard(int index);

    MyLabel selectMyLabelByPrimaryKey(Integer myLabelId);

    List<MyLabel> selectMyLabelByPage(Map params);

    Map getTabByTabId(Integer tabId);

}
