package com.java.foodshop.service;

import com.java.foodshop.pojo.Article;
import com.java.foodshop.request.ArticleRequest;
import com.java.foodshop.request.SelArticleRequest;
import com.java.foodshop.request.SelectAllArticleRequest;
import com.java.foodshop.request.SelectArticleByTypeIdRequest;
import com.java.foodshop.response.ArticleResponseAndAllCount;
import java.util.List;

public interface ArticleService {

    Integer addArticle(ArticleRequest addArticleRequest);

    Integer deleteAllArticle(List<Long> ids);

    Integer updateArticleById(ArticleRequest articleRequest, Long id);

    ArticleResponseAndAllCount selArticle(SelArticleRequest request);

    ArticleResponseAndAllCount selAllArticle(SelectAllArticleRequest selectAllArticleRequest);

    ArticleResponseAndAllCount selectArticleByTypeId(SelectArticleByTypeIdRequest request);

    Article selectArticleById(Integer articleId);

    List<Article> selectdiscountArticle();
}
