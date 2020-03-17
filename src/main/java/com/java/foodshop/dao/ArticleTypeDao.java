package com.java.foodshop.dao;

import com.java.foodshop.mapper.ArticleTypeMapper;
import com.java.foodshop.pojo.ArticleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Repository
public class ArticleTypeDao {
    @Autowired
    private ArticleTypeMapper articleTypeMapper;

    public Integer addArticleType(String name) {
        ArticleType articleType = new ArticleType();
        articleType.setName(name);
        return articleTypeMapper.insert(articleType);
    }

    public Integer delArticleType(List<Long> ids) {
        Example example = new Example(ArticleType.class);
        example.createCriteria().andIn("id",ids);
        return articleTypeMapper.deleteByExample(example);
    }

    public Integer updArticleType(ArticleType articleType) {
        Example example = new Example(ArticleType.class);
        example.createCriteria().andEqualTo("id",articleType.getId());
        return articleTypeMapper.updateByExampleSelective(articleType,example);
    }

    public List<ArticleType> selectAllArticleType() {
        return articleTypeMapper.selectAll();
    }
}
