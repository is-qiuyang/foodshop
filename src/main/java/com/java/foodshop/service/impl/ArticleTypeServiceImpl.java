package com.java.foodshop.service.impl;

import com.java.foodshop.dao.ArticleTypeDao;
import com.java.foodshop.pojo.ArticleType;
import com.java.foodshop.service.ArticleTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ArticleTypeServiceImpl implements ArticleTypeService {
    @Autowired
    private ArticleTypeDao articleTypeDao;
    @Override
    public Integer addArticleType(String name) {
        List<ArticleType> articleTypes = articleTypeDao.selectAllArticleType();
        for (ArticleType articleType : articleTypes) {
            if(articleType.getName().equals(name)){
                return -1;
            }
        }
        return articleTypeDao.addArticleType(name);
    }

    @Override
    public Integer delArticleType(List<Long> ids) {
        return articleTypeDao.delArticleType(ids);
    }

    @Override
    public Integer updArticleType(ArticleType articleType) {
        return articleTypeDao.updArticleType(articleType);
    }

    @Override
    public List<ArticleType> selectAllArticleType() {
        return articleTypeDao.selectAllArticleType();
    }
}
