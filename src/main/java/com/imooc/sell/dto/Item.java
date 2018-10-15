package com.imooc.sell.dto;

import com.imooc.sell.dataobject.ProductInfo;
import lombok.Data;

@Data
public class Item {
    private ProductInfo productInfo;

    private Integer productQuantity;

    public Item(ProductInfo productInfo, Integer productQuantity) {
        this.productInfo = productInfo;
        this.productQuantity = productQuantity;
    }
}
