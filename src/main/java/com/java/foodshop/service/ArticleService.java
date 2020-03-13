package com.java.foodshop.service;

import com.java.foodshop.pojo.Article;
import com.java.foodshop.request.ArticleRequest;
import com.java.foodshop.request.SelArticleRequest;
import com.java.foodshop.request.SelectAllArticleRequest;
import com.java.foodshop.request.SelectArticleByTypeIdRequest;

import java.util.List;

public interface ArticleService {

    Integer addArticle(ArticleRequest addArticleRequest);

    Integer deleteAllArticle(List<Long> ids);

    Integer updateArticleById(ArticleRequest articleRequest, Long id);

    List<Article> selArticle(SelArticleRequest request);

    List<Article> selAllArticle(SelectAllArticleRequest selectAllArticleRequest);

    List<Article> selectArticleByTypeId(SelectArticleByTypeIdRequest request);
}
