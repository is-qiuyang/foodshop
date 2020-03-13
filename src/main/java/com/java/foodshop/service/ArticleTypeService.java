package com.java.foodshop.service;

import com.java.foodshop.pojo.ArticleType;

import java.util.List;

public interface ArticleTypeService {
    Integer addArticleType(String name);

    Integer delArticleType(List<Long> ids);

    Integer updArticleType(ArticleType articleType);

    List<ArticleType> selectAllArticleType();
}
