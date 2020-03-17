package com.java.foodshop.service.impl;

import com.java.foodshop.dao.ArticleDao;
import com.java.foodshop.dao.ShopCarDao;
import com.java.foodshop.pojo.Article;
import com.java.foodshop.pojo.Shopcar;
import com.java.foodshop.request.AddShopCarRequest;
import com.java.foodshop.response.ShowShopCarsResponse;
import com.java.foodshop.service.ShopCarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ShopCarServiceImpl implements ShopCarService {
    @Autowired
    private ShopCarDao shopCarDao;
    @Autowired
    private ArticleDao articleDao;

    @Override
    public List<ShowShopCarsResponse> getAllShopCarByUserId(int userId) {
        List<ShowShopCarsResponse> responses = new ArrayList<>();
        //根据已登录用户的Id获取该用户的购物车详情
        List<Shopcar> shopcars=shopCarDao.getAllShopCarByUserId(userId);

        for (Shopcar shopcar:shopcars) {
            ShowShopCarsResponse response = new ShowShopCarsResponse();
            BeanUtils.copyProperties(shopcar,response);
            responses.add(response);
        }

        return responses;
    }

    /**
     * 方法描述
     * @ 这里应该有如果数据库里能查询到用户有这个商品，那么直接更新数据库里的数量和总价
     * @return
     * @date 2020/3/16
     */
    @Override
    public Integer addArticleToShopCar(AddShopCarRequest request) {
        Shopcar shopcar = new Shopcar();
        BeanUtils.copyProperties(request,shopcar);
        //通过商品id和用户id查询用户是否已经将商品添加至购物车，如果已经添加过，更改数量
        Shopcar shopcar1 = shopCarDao.selectShopcarByIds(request.getUserId(), request.getArticleId());
        Integer integer = 0;
        if (!shopcar1.equals(null)){
            int orderNum = shopcar1.getOrdernum() + request.getOrdernum();
            integer = updateShopcar(request.getUserId(), request.getArticleId(), orderNum);
        }
        if(integer==1){
            return 1;
        }
        Integer id = request.getArticleId();
        Article article = articleDao.selectArticleById(id);
        Double totalPrice = sumTotal(request.getOrdernum(), article.getDiscont(), article.getPrice());

        shopcar.setConuntPrice(totalPrice);

        log.info("shopcar:",shopcar);
        log.info("添加：",shopCarDao.addArticleToShopCar(shopcar));
        return shopCarDao.addArticleToShopCar(shopcar);
    }

    @Override
    public Integer updateShopcar(int userId,int articleId, int ordernum) {
        Shopcar shopcar = new Shopcar();
        Article article = articleDao.selectArticleById(articleId);
        Double totalPrice = sumTotal(ordernum, article.getDiscont(), article.getPrice());
        shopcar.setOrdernum(ordernum);
        shopcar.setUserId(userId);
        shopcar.setArticleId(articleId);
        shopcar.setConuntPrice(totalPrice);
        return shopCarDao.updateShopcar(shopcar);
    }

    @Override
    public Integer deleteShopcar(int userId, List<Long> articleIds) {
        return shopCarDao.deleteShopcar(userId,articleIds);
    }

    /**
     * 方法描述
     * @ 计算总金额
     * @return
     * @date 2020/3/16
     */
    public Double sumTotal(int num,double discont,double price){
        double totalPrice = price*discont* num;
        return totalPrice;
    }
}
