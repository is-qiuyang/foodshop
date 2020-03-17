package com.java.foodshop.request;

import lombok.Data;

@Data
public class SelectArticleByTypeIdRequest {
    Long typeId;
    Integer pageNumber ;
}
