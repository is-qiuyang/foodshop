package com.java.foodshop.controller;

import com.java.foodshop.common.SzpJsonResult;
import com.java.foodshop.pojo.Article;
import com.java.foodshop.request.ArticleRequest;
import com.java.foodshop.request.SelArticleRequest;
import com.java.foodshop.request.SelectAllArticleRequest;
import com.java.foodshop.request.SelectArticleByTypeIdRequest;
import com.java.foodshop.response.ArticleResponse;
import com.java.foodshop.service.ArticleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class ArticleController {
    @Autowired
    ArticleService articleService;

    /**
     * 添加零食
     * @param articleRequest
     * @return
     */
    @PostMapping("add/Article")
    public SzpJsonResult<String> addArticleByJson(@RequestBody ArticleRequest articleRequest) {
        if(articleService.addArticle(articleRequest)==1){
            return SzpJsonResult.ok("添加货物成功");
        }else {
            return SzpJsonResult.errorMsg("添加货物失败，请重新添加");
        }
    }

    /**
     * 批量删除货物
     * * @return
     */
    @DeleteMapping("delete/articleByIds")
    public SzpJsonResult<String> deleteAllArticle(@RequestBody List<Long> ids){
        Integer integer = articleService.deleteAllArticle(ids);
        if (integer>0){
            return SzpJsonResult.ok("成功删除"+integer+"个货物");
        }
        return SzpJsonResult.errorMsg("删除失败");
    }


    /**
     * 方法描述
     * @ 更新一个零食
     * @return
     * @date 2020/3/1
     */
    @PutMapping("update/article")
    public SzpJsonResult<Integer> updArticle(@RequestBody ArticleRequest articleRequest,Long id){
        Integer isSuccess = articleService.updateArticleById(articleRequest,id);
        return SzpJsonResult.ok(isSuccess);
    }

    /**
     * 商品名关键字搜索
     * @return
     */
    @GetMapping("get/Article")
    public SzpJsonResult<ArticleResponse> selectArticleByKeyWords(@RequestBody SelArticleRequest request){
        return SzpJsonResult.ok(articleService.selArticle(request));
    }


    /**
     * 分页查看货物
     *  Collections.shuffle()使列表顺序打乱
     * @return
     */
    @GetMapping("select/allArticle")
    public SzpJsonResult<ArticleResponse> selectAllArticleByRandom(@RequestBody SelectAllArticleRequest selectAllArticleRequest){
        List<Article> articles= articleService.selAllArticle(selectAllArticleRequest);
        List<ArticleResponse> articleResponses = new ArrayList<>();
        for (Article article : articles) {
            for (ArticleResponse articleResponse : articleResponses) {
                BeanUtils.copyProperties(article,articleResponse);
            }
        }
        Collections.shuffle(articleResponses);
        return SzpJsonResult.ok(articleResponses);
    }

    /**
     * 分类查找
     * @return
     */
    @GetMapping("select/articleTypeId")
    public SzpJsonResult<ArticleResponse> selectArticleByKindId(@RequestBody SelectArticleByTypeIdRequest request){
        return SzpJsonResult.ok(articleService.selectArticleByTypeId(request));
    }

}
