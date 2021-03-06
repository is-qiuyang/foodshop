package com.java.foodshop.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java.foodshop.dao.ArticleDao;
import com.java.foodshop.pojo.Article;
import com.java.foodshop.request.ArticleRequest;
import com.java.foodshop.request.SelArticleRequest;
import com.java.foodshop.request.SelectAllArticleRequest;
import com.java.foodshop.request.SelectArticleByTypeIdRequest;
import com.java.foodshop.response.ArticleResponse;
import com.java.foodshop.response.ArticleResponseAndAllCount;
import com.java.foodshop.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;
    @Override
    public Integer addArticle(ArticleRequest addArticleRequest) {
        Article article = new Article();
        BeanUtils.copyProperties(addArticleRequest,article);
        if(article.getDiscont()>1 || article.getDiscont()<0){
            article.setDiscont((double) 1);
        }
        article.setCreateTime(new Date());
        return articleDao.addArticle(article);
    }

    @Override
    public Integer deleteAllArticle(List<Long> ids) {
        return articleDao.deleteAllArticle(ids);
    }

    @Override
    public Integer updateArticleById(ArticleRequest articleRequest, Long id) {
        Article article = new Article();
        BeanUtils.copyProperties(articleRequest,article);
        return articleDao.updateArticleById(article,id);
    }

    @Override
    public ArticleResponseAndAllCount selArticle(SelArticleRequest request) {
        Integer pageSize = 10;
        Integer pageNumber = request.getPageNumber();
        PageHelper.startPage(pageNumber,pageSize);
        List<Article> all = articleDao.selArticle(request.getTitle());
        PageInfo<Article> pageInfo=new PageInfo<>(all);
        long allCount = pageInfo.getTotal();
        List<Article> list = pageInfo.getList();
        ArticleResponseAndAllCount articleResponseAndPageNum = articleListAndPageNum(list, allCount);
        return articleResponseAndPageNum;
    }

    @Override
    public ArticleResponseAndAllCount selAllArticle(SelectAllArticleRequest selectAllArticleRequest) {
        Integer pageSize = 10;
        Integer pageNumber = selectAllArticleRequest.getPageNumber();
        PageHelper.startPage(pageNumber,pageSize);
        List<Article> all = articleDao.selectAllArticle();
        PageInfo<Article> pageInfo=new PageInfo<>(all);
        long allCount = pageInfo.getTotal();
        List<Article> list = pageInfo.getList();
        ArticleResponseAndAllCount articleResponseAndPageNum = articleListAndPageNum(list, allCount);
        log.info("all-{}",all);
        log.info("pageInfo.getList()-{}",pageInfo.getList());
        log.info("pagesize-{},pageNumber-{}",pageSize,pageNumber);
        return articleResponseAndPageNum;
    }

    @Override
    public ArticleResponseAndAllCount selectArticleByTypeId(SelectArticleByTypeIdRequest request) {
        Integer pageSize = 10;
        Integer pageNumber = request.getPageNumber();
        PageHelper.startPage(pageNumber,pageSize);
        List<Article> all = articleDao.selectArticleByTypeId(request.getTypeId());
        PageInfo<Article> pageInfo=new PageInfo<>(all);
        long allCount = pageInfo.getTotal();
        List<Article> list = pageInfo.getList();
        ArticleResponseAndAllCount articleResponseAndPageNum = articleListAndPageNum(list, allCount);
        return articleResponseAndPageNum;
    }

    @Override
    public Article selectArticleById(Integer articleId) {
        return articleDao.selectArticleById(articleId);
    }

    @Override
    public List<Article> selectdiscountArticle() {
        List<Article> articles = articleDao.selectAllArticle();
        List<Article> articleList = new ArrayList<>();
        for (Article article : articles) {
            if (article.getDiscont()<1 && article.getDiscont()>0){
                articleList.add(article);
            }
        }
        return articleList;
    }

    public ArticleResponseAndAllCount articleListAndPageNum(List<Article> list,Long allCount){
        ArticleResponseAndAllCount articleResponseAndPageNum = new ArticleResponseAndAllCount();
        List<ArticleResponse> articleResponses = new ArrayList<>();
        for (Article article : list) {
            ArticleResponse response = new ArticleResponse();
            BeanUtils.copyProperties(article,response);
            articleResponses.add(response);
        }
        articleResponseAndPageNum.setArticleResponses(articleResponses);
        articleResponseAndPageNum.setAllCount(allCount);
        return articleResponseAndPageNum;
    }
}
