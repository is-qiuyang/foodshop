package com.java.foodshop.dao;

import com.java.foodshop.mapper.ShopcarMapper;
import com.java.foodshop.pojo.Shopcar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Repository
public class ShopCarDao {
    @Autowired
    private ShopcarMapper shopcarMapper;

    public List<Shopcar> getAllShopCarByUserId(int userId) {
        Example example = new Example(Shopcar.class);
        example.createCriteria().andEqualTo("userId",userId);
        return shopcarMapper.selectByExample(example);
    }

    public Integer addArticleToShopCar(Shopcar shopcar) {
        return shopcarMapper.insert(shopcar);
    }

    public Integer updateShopcar(Shopcar shopcar) {
        Example example = new Example(Shopcar.class);
        example.createCriteria().andEqualTo("userId",shopcar.getUserId())
                .andEqualTo("articleId",shopcar.getArticleId());
        return shopcarMapper.updateByExample(shopcar,example);
    }

    public Integer deleteShopcar(int userId, List<Long> articleIds) {
        Example example = new Example(Shopcar.class);
        example.createCriteria().andEqualTo("userId",userId).andIn("articleId",articleIds);
        return shopcarMapper.deleteByExample(example);
    }

    public Shopcar selectShopcarByIds(int userId, int articleId) {
        Example example = new Example(Shopcar.class);
        example.createCriteria().andEqualTo("userId",userId)
                .andEqualTo("articleId",articleId);
        return shopcarMapper.selectOneByExample(example);
    }

    public List<Shopcar> selectShopcars(int userId, List<Integer> articleIds) {
        Example example = new Example(Shopcar.class);
        example.createCriteria().andEqualTo("userId",userId).andIn("articleId",articleIds);
        return shopcarMapper.selectByExample(example);
    }
}
