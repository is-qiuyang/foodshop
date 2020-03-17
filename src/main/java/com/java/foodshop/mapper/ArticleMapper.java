package com.java.foodshop.mapper;

import com.java.foodshop.pojo.Article;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper extends CommonMapper<Article> {
}