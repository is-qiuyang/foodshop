package com.java.foodshop;

import com.java.foodshop.dao.ArticleDao;
import com.java.foodshop.pojo.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FoodshopApplicationTests {
    @Autowired
    private ArticleDao articleDao;

    @Test
    void contextLoads() {
    }

    @Test
    public void selectArticleById(){
        Integer id = 3;
        Article article = articleDao.selectArticleById(id);
        System.out.println(article.toString());
    }
}
