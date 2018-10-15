package com.imooc.sell.service;

import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    ProductInfo findOne(String productId);

    //find all products that are on shelf for sale
    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    // find all products in a category
    Page<ProductInfo> findAllInCategory(Integer categoryType, Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    //add stock
    void increaseStock(List<CartDTO> cartDTOList);


    //reduce stock
    void decreaseStock(List<CartDTO> cartDTOList);

    //on sale 上架
    ProductInfo onSale(String productId);

    //off sale 下架
    ProductInfo offSale(String productId);
}
