package com.imooc.sell.service.impl;

import com.imooc.sell.dataobject.ProductCategory;
import com.imooc.sell.repository.ProductCategoryRepository;
import com.imooc.sell.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//category
@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private ProductCategoryRepository repository;

    /**
     *@Description: findOne
     *@Param: [categoryId]
     *@return: com.imooc.sell.dataobject.ProductCategory
     *@Author: XINPENG ZHU
     *@Date: 2018/11/1
     *@Time: 22:31
     */
    @Override
    public ProductCategory findOne(Integer categoryId) {
        return repository.findById(categoryId).orElse(null);
    }

    /**
     *@Description: findAll
     *@Param: []
     *@return: java.util.List<com.imooc.sell.dataobject.ProductCategory>
     *@Author: XINPENG ZHU
     *@Date: 2018/11/1
     *@Time: 22:31
     */
    @Override
    public List<ProductCategory> findAll() {
        return repository.findAll();
    }

    /**
     *@Description: findByCategoryTypeIn
     *@Param: [categoryTypeList]
     *@return: java.util.List<com.imooc.sell.dataobject.ProductCategory>
     *@Author: XINPENG ZHU
     *@Date: 2018/11/1
     *@Time: 22:31
     */
    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return repository.findByCategoryTypeIn(categoryTypeList);
    }

    /**
     *@Description: save
     *@Param: [productCategory]
     *@return: com.imooc.sell.dataobject.ProductCategory
     *@Author: XINPENG ZHU
     *@Date: 2018/11/1
     *@Time: 22:31
     */
    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return repository.save(productCategory);
    }
}
