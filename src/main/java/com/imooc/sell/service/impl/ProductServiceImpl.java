package com.imooc.sell.service.impl;

import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.dataobject.dao.ProductCategoryDao;
import com.imooc.sell.dataobject.mapper.ProductCategoryMapper;
import com.imooc.sell.dto.CartDTO;
import com.imooc.sell.enums.ProductStatusEnum;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.repository.ProductInfoRepository;
import com.imooc.sell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository repository;

    /**
     *@Description: findOne
     *@Param: [productId]
     *@return: com.imooc.sell.dataobject.ProductInfo
     *@Author: XINPENG ZHU
     *@Date: 2018/11/1
     *@Time: 22:32
     */
    @Override
//    @Cacheable(cacheNames = "product",key="123")
    public ProductInfo findOne(String productId) {
        return repository.findById(productId).orElse(null);
    }

    /**
     *@Description: findUpAll
     *@Param: []
     *@return: java.util.List<com.imooc.sell.dataobject.ProductInfo>
     *@Author: XINPENG ZHU
     *@Date: 2018/11/1
     *@Time: 22:32
     */
    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    /**
     *@Description: findAll
     *@Param: [pageable]
     *@return: org.springframework.data.domain.Page<com.imooc.sell.dataobject.ProductInfo>
     *@Author: XINPENG ZHU
     *@Date: 2018/11/1
     *@Time: 22:32
     */
    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    /**
     *@Description: findAllInCategory
     *@Param: [categoryType, pageable]
     *@return: org.springframework.data.domain.Page<com.imooc.sell.dataobject.ProductInfo>
     *@Author: XINPENG ZHU
     *@Date: 2018/11/1
     *@Time: 22:33
     */
    @Override
    public Page<ProductInfo> findAllInCategory(Integer categoryType, Pageable pageable) {
        return repository.findAllByCategoryTypeOrderByProductIdAsc(categoryType, pageable);
    }

    /**
     *@Description: save
     *@Param: [productInfo]
     *@return: com.imooc.sell.dataobject.ProductInfo
     *@Author: XINPENG ZHU
     *@Date: 2018/11/1
     *@Time: 22:33
     */
    @Override
//    @CachePut(cacheNames = "product",key="123")
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    /**
     *@Description: increaseStock
     *@Param: [cartDTOList]
     *@return: void
     *@Author: XINPENG ZHU
     *@Date: 2018/11/1
     *@Time: 22:33
     */
    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO:cartDTOList){
            ProductInfo productInfo = repository.findById(cartDTO.getProductId()).orElse(null);
            if(productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            repository.save(productInfo);


        }


    }

    /**
     *@Description: decreaseStock
     *@Param: [cartDTOList]
     *@return: void
     *@Author: XINPENG ZHU
     *@Date: 2018/11/1
     *@Time: 22:33
     */
    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for(CartDTO cartDTO:cartDTOList){
            ProductInfo productInfo = repository.findById(cartDTO.getProductId()).orElse(null);

            if(productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity() ;
            if(result<0){
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            productInfo.setProductStock(result);

            repository.save(productInfo);
        }
    }


    /**
     *@Description: onSale
     *@Param: [productId]
     *@return: com.imooc.sell.dataobject.ProductInfo
     *@Author: XINPENG ZHU
     *@Date: 2018/11/1
     *@Time: 22:33
     */
    @Override
    public ProductInfo onSale(String productId) {
        ProductInfo  productInfo =  repository.findById(productId).orElse(null);
        if(productInfo==null){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if(productInfo.getProductStatusEnum()== ProductStatusEnum.UP){
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        //update
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        return repository.save(productInfo);

    }

    /**
     *@Description: offSale
     *@Param: [productId]
     *@return: com.imooc.sell.dataobject.ProductInfo
     *@Author: XINPENG ZHU
     *@Date: 2018/11/1
     *@Time: 22:33
     */
    @Override
    public ProductInfo offSale(String productId) {
        ProductInfo  productInfo =  repository.findById(productId).orElse(null);
        if(productInfo==null){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if(productInfo.getProductStatusEnum()== ProductStatusEnum.DOWN){
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        //update
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        return repository.save(productInfo);
    }
}
