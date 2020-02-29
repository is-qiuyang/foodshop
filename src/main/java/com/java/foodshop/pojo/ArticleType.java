package com.java.foodshop.pojo;

import javax.persistence.*;

@Table(name = "article_type")
public class ArticleType {
    /**
     * 商品类型编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 商品类型名称
     */
    private String name;

    /**
     * 获取商品类型编号
     *
     * @return id - 商品类型编号
     */
    public String getId() {
        return id;
    }

    /**
     * 设置商品类型编号
     *
     * @param id 商品类型编号
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取商品类型名称
     *
     * @return name - 商品类型名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置商品类型名称
     *
     * @param name 商品类型名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}