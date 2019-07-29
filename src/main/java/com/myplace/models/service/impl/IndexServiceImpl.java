package com.myplace.models.service.impl;

import com.myplace.common.page.RequestModel;
import com.myplace.common.page.ResponseModel;
import com.myplace.models.dao.MyBlogMapper;
import com.myplace.models.dao.MyLabelMapper;
import com.myplace.models.entity.MyBlog;
import com.myplace.models.entity.MyLabel;
import com.myplace.models.service.IndexService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("indexService")
public class IndexServiceImpl implements IndexService {
    @Resource
    private MyLabelMapper myLabelMapper;
    @Resource
    private MyBlogMapper myBlogMapper;

    @Override
    public ResponseModel getIndex(RequestModel requestModel) {
        Map indexData = new HashMap();
        //标题栏
        List<Map> myLabelList = myLabelMapper.getLabelByParentId(0);
        for (Map myLabel : myLabelList) {
            List<Map> sonLabels = myLabelMapper.getLabelByParentId((Integer) myLabel.get("id"));
            myLabel.put("sonLabels", sonLabels);
        }
        indexData.put("myLabelList", myLabelList);
        //轮播*3
        List<MyBlog> carouselArticle = myBlogMapper.getCarouselArticle();
        indexData.put("carouselArticle", carouselArticle);
        //最新*3
        List<MyBlog> latestArticles = myBlogMapper.getLatestArticles();
        indexData.put("latestArticles", latestArticles);
        //热门*3
        List<MyBlog> popularArticles = myBlogMapper.getPopularArticles();
        indexData.put("popularArticles", popularArticles);
        //阅读量最多*5
        List<MyBlog> mostReadingArticles = myBlogMapper.getMostReadingArticles();
        indexData.put("mostReadingArticles", mostReadingArticles);
        //精选*2
        List<MyBlog> selectedArticles = myBlogMapper.getSelectedArticles();
        indexData.put("selectedArticles", selectedArticles);
        //分类文章
        List<Map> sonLabelList = myLabelMapper.getSonLabelList();
        List<MyBlog> classificationArticles = myBlogMapper.getClassificationArticles((Integer)sonLabelList.get(0).get("id"));
        indexData.put("sonLabelList", sonLabelList);
        indexData.put("classificationArticles", classificationArticles);
        //评论最多文章
        List<MyBlog> mostCommentedArticles = myBlogMapper.getMostCommentedArticles();
        indexData.put("mostCommentedArticles", mostCommentedArticles);
        return ResponseModel.success(indexData);
    }
}
