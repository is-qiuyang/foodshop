package com.java.foodshop.mapper;

import com.java.foodshop.pojo.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper extends CommonMapper<Article> {
    Integer updateArticleNum(List<Article> articles);


}