package com.imooc.sell.controller;


import com.imooc.sell.dataobject.ProductCategory;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.form.CategoryForm;
import com.imooc.sell.service.CategoryService;
import com.imooc.sell.utils.KeyUtil;
import com.lly835.bestpay.rest.type.Post;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {

    @Autowired
    private CategoryService categoryService;


    /**
     *@Description: list
     *@Param: [map]
     *@return: org.springframework.web.servlet.ModelAndView
     *@Author: XINPENG ZHU
     *@Date: 2018/11/1
     *@Time: 22:29
     */
    @GetMapping("/list")
    public ModelAndView list(Map<String,Object> map) {
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList", categoryList);
        return new ModelAndView("category/list",map);
    }



    /**
     *@Description: index
     *@Param: [categoryId, map]
     *@return: org.springframework.web.servlet.ModelAndView
     *@Author: XINPENG ZHU
     *@Date: 2018/11/1
     *@Time: 22:29
     */
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId", required = false) Integer categoryId,
                              Map<String, Object> map) {
        if (categoryId != null) {
            ProductCategory productCategory = categoryService.findOne(categoryId);
            map.put("category", productCategory);
        }

        return new ModelAndView("category/index", map);
    }



    /**
     *@Description: save
     *@Param: [form, bindingResult, map]
     *@return: org.springframework.web.servlet.ModelAndView
     *@Author: XINPENG ZHU
     *@Date: 2018/11/1
     *@Time: 22:29
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm form,
                             BindingResult bindingResult,
                             Map<String, Object> map) {
        if(bindingResult.hasErrors()){
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "sell/seller/category/index");
            return new ModelAndView("common/error",map);
        }

        try {
            ProductCategory productCategory = new ProductCategory();
            if(form.getCategoryId()!=null){
                productCategory =categoryService.findOne(form.getCategoryId());
                BeanUtils.copyProperties(form,productCategory);
            }
            else{
                //form.setCategoryId(KeyUtil.getUniqueKey());
                BeanUtils.copyProperties(form,productCategory);
                productCategory.setCreateTime(new Date());
                productCategory.setUpdateTime(new Date());
            }

            categoryService.save(productCategory);
        } catch (SellException e){
            map.put("msg", e.getMessage());
            map.put("url", "sell/seller/category/index");
            return new ModelAndView("common/error",map);
        }

        map.put("url", "/sell/seller/category/list");
        return new ModelAndView("common/success", map);
        
    }

}
