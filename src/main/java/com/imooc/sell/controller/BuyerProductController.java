//package com.imooc.sell.controller;
//
////buyer product
//
//import com.imooc.sell.VO.ProductInfoVO;
//import com.imooc.sell.VO.ProductVO;
//import com.imooc.sell.VO.ResultVO;
//import com.imooc.sell.dataobject.ProductCategory;
//import com.imooc.sell.dataobject.ProductInfo;
//import com.imooc.sell.service.CategoryService;
//import com.imooc.sell.service.ProductService;
//import com.imooc.sell.utils.ResultVOUtil;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("/buyer/product")
//public class BuyerProductController {
//
//    @Autowired
//    private ProductService productService;
//
//    @Autowired
//    private CategoryService categoryService;
//
//    @GetMapping("/list")
////    @Cacheable(cacheNames = "product",key="123")
//    public ResultVO list() {
//
//        //1.find all products on shelf
//        List<ProductInfo> productInfoList = productService.findUpAll();
//
//
//        //2.find category (one time)
//        // traditional method
////        List<Integer> categoryTypeList = new ArrayList<>();
////        for (ProductInfo productInfo:productInfoList){
////            categoryTypeList.add(productInfo.getCategoryType());
////        }
//        //easy method (java 8, lambda)
//        List<Integer> categoryTypeList = productInfoList.stream()
//                .map(e -> e.getCategoryType())
//                .collect(Collectors.toList());
//        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
//
//
//        //3.data assemble
//        //将查出来的所有商品按照品类-商品拼装好，与前端文档格式保持一致
//        List<ProductVO> productVOList = new ArrayList<>();
//        for(ProductCategory productCategory:productCategoryList){
//            ProductVO productVO = new ProductVO();
//            productVO.setCategoryType(productCategory.getCategoryType());
//            productVO.setCategoryName(productCategory.getCategoryName());
//
//
//
//            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
//            for (ProductInfo productInfo:productInfoList){
//                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
//                    ProductInfoVO productInfoVO = new ProductInfoVO();
//                    //copy object information from source to target
//                    BeanUtils.copyProperties(productInfo,productInfoVO);
//                    productInfoVOList.add(productInfoVO);
//                }
//            }
//            productVO.setProductInfoVOList(productInfoVOList);
//            productVOList.add(productVO);
//        }
//
//
//        return ResultVOUtil.success(productVOList);
//    }
//
//}
















package com.imooc.sell.controller;

//buyer product

import com.imooc.sell.VO.ProductInfoVO;
import com.imooc.sell.VO.ProductVO;
import com.imooc.sell.VO.ResultVO;
import com.imooc.sell.dataobject.ProductCategory;
import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.enums.ProductStatusEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.service.CategoryService;
import com.imooc.sell.service.ProductService;
import com.imooc.sell.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/product")
@Slf4j
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    /**
     *
     * @param page
     * @param size
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "3") Integer size,
                             Map<String, Object> map) {

        PageRequest request = PageRequest.of(page - 1, size);
        Page<ProductInfo> products = productService.findAll(request);
        map.put("products", products);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("Buyer_Product/list",map);
        //return ResultVOUtil.success(productVOList);
    }


    @GetMapping("/productInfo")
    public ModelAndView showOne(@RequestParam("productId") String productId,
                          Map<String, Object> map) {

        ProductInfo productInfo = new ProductInfo();
        try{
            productInfo = productService.findOne(productId);
        }catch (SellException e) {
            log.error("[Exception] Finding product error!", e);
            map.put("msg", e.getMessage());
            map.put("url", "/sell/buyer/product/list");
            return new ModelAndView("common/error", map);
        }

        map.put("productInfo", productInfo);
        return new ModelAndView("Buyer_Product/show",map);


    }


    @GetMapping("/category/{type}")
    public ModelAndView showOneCategory(@PathVariable("type") Integer categoryType,
                                @RequestParam(value = "page", defaultValue = "1") Integer page,
                                @RequestParam(value = "size", defaultValue = "3") Integer size,
                                Map<String, Object> map) {

        ProductCategory res = categoryService.findOne(categoryType);
        PageRequest request = PageRequest.of(page - 1, size);
        Page<ProductInfo> productInCategory = productService.findAllInCategory(categoryType, request);
        map.put("category", res);
        map.put("products", productInCategory);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("Buyer_Product/list", map);
    }

}
