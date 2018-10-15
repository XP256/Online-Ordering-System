package com.imooc.sell.VO;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

//no relationship with ProductInfo class

@Data
public class ProductVO implements Serializable {

    private static final long serialVersionUID = 3355332619015620687L;

    //when return to front end its called 'name'
    //backend called "categoryName"
    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;


}
