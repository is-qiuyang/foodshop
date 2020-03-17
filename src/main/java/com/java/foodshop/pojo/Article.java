package com.java.foodshop.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "article")
public class Article {
    /**
     * 商品ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 商品名
     */
    private String title;

    /**
     * 供应商
     */
    private String supplier;

    /**
     * 商品价格
     */
    private Double price;

    /**
     * 折扣
     */
    private Double discont;

    /**
     * 产地
     */
    private String locality;

    /**
     * 存储数量
     */
    private Integer storagy;

    /**
     * 商品图片
     */
    private String image;

    /**
     * 描述
     */
    private String description;

    /**
     * 类型ID
     */
    @Column(name = "type_ID")
    private Long typeId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 获取商品ID
     *
     * @return id - 商品ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置商品ID
     *
     * @param id 商品ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取商品名
     *
     * @return title - 商品名
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置商品名
     *
     * @param title 商品名
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 获取供应商
     *
     * @return supplier - 供应商
     */
    public String getSupplier() {
        return supplier;
    }

    /**
     * 设置供应商
     *
     * @param supplier 供应商
     */
    public void setSupplier(String supplier) {
        this.supplier = supplier == null ? null : supplier.trim();
    }

    /**
     * 获取商品价格
     *
     * @return price - 商品价格
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 设置商品价格
     *
     * @param price 商品价格
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * 获取折扣
     *
     * @return discont - 折扣
     */
    public Double getDiscont() {
        return discont;
    }

    /**
     * 设置折扣
     *
     * @param discont 折扣
     */
    public void setDiscont(Double discont) {
        this.discont = discont;
    }

    /**
     * 获取产地
     *
     * @return locality - 产地
     */
    public String getLocality() {
        return locality;
    }

    /**
     * 设置产地
     *
     * @param locality 产地
     */
    public void setLocality(String locality) {
        this.locality = locality == null ? null : locality.trim();
    }

    /**
     * 获取存储数量
     *
     * @return storagy - 存储数量
     */
    public Integer getStoragy() {
        return storagy;
    }

    /**
     * 设置存储数量
     *
     * @param storagy 存储数量
     */
    public void setStoragy(Integer storagy) {
        this.storagy = storagy;
    }

    /**
     * 获取商品图片
     *
     * @return image - 商品图片
     */
    public String getImage() {
        return image;
    }

    /**
     * 设置商品图片
     *
     * @param image 商品图片
     */
    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    /**
     * 获取描述
     *
     * @return description - 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述
     *
     * @param description 描述
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 获取类型ID
     *
     * @return type_ID - 类型ID
     */
    public Long getTypeId() {
        return typeId;
    }

    /**
     * 设置类型ID
     *
     * @param typeId 类型ID
     */
    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}