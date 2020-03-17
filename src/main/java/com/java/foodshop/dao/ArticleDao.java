package com.java.foodshop.dao;

import com.java.foodshop.mapper.ArticleMapper;
import com.java.foodshop.pojo.Article;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Repository
public class ArticleDao {
    @Autowired
    private ArticleMapper articleMapper;

    public Integer addArticle(Article article) {
        return articleMapper.insert(article);
    }

    public Integer deleteAllArticle(List<Long> ids) {
        Example example = new Example(Article.class);
        example.createCriteria().andIn("id",ids);
        return articleMapper.deleteByExample(example);
    }

    public Integer updateArticleById(Article article, Long id) {
        Example example = new Example(Article.class);
        example.createCriteria().andEqualTo("id",id);
        return articleMapper.updateByExampleSelective(article,example);
    }

    public List<Article> selArticle(String title) {
        Example example = new Example(Article.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(title)){
            criteria.andLike("title","%" + title +"%");
        }
        return articleMapper.selectByExample(example);
    }

    public List<Article> selectAllArticle() {
        return articleMapper.selectAll();

    }

    public List<Article> selectArticleByTypeId(Long typeId) {
        Example example = new Example(Article.class);
        example.createCriteria().andEqualTo("typeId",typeId);
        return articleMapper.selectByExample(example);
    }

    public Article selectArticleById(Integer id){
        return articleMapper.selectByPrimaryKey(id);
    }
}
