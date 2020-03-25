package com.java.foodshop.service;

import com.java.foodshop.pojo.Article;
import com.java.foodshop.request.ArticleRequest;
import com.java.foodshop.request.SelArticleRequest;
import com.java.foodshop.request.SelectAllArticleRequest;
import com.java.foodshop.request.SelectArticleByTypeIdRequest;
import com.java.foodshop.response.ArticleResponseAndPageNum;

import java.util.List;

public interface ArticleService {

    Integer addArticle(ArticleRequest addArticleRequest);

    Integer deleteAllArticle(List<Long> ids);

    Integer updateArticleById(ArticleRequest articleRequest, Long id);

    ArticleResponseAndPageNum selArticle(SelArticleRequest request);

    ArticleResponseAndPageNum selAllArticle(SelectAllArticleRequest selectAllArticleRequest);

    ArticleResponseAndPageNum selectArticleByTypeId(SelectArticleByTypeIdRequest request);

    Article selectArticleById(Integer articleId);

    List<Article> selectdiscountArticle();
}
