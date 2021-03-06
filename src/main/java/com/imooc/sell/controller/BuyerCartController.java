package com.imooc.sell.controller;


import com.imooc.sell.dataobject.UserInfo;
import com.imooc.sell.dto.CartDTO;
import com.imooc.sell.dto.Item;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.service.CartService;
import com.imooc.sell.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/buyer/cart")
@Slf4j
public class BuyerCartController {

    @Autowired
    CartService cartService;
    @Autowired
    UserService userService;

    /**
     *@Description: findAll
     *@Param: [map]
     *@return: org.springframework.web.servlet.ModelAndView
     *@Author: XINPENG ZHU
     *@Date: 2018/11/1
     *@Time: 0:25
     */
    @GetMapping("/list")
    public ModelAndView findAll(Map<String, Object> map){
        Collection<Item> items = cartService.findAll();
        BigDecimal total = cartService.getTotal();


        map.put("items", items);
        map.put("total", total);
        return new ModelAndView("Buyer_Cart/list",map);
    }

    /**
     *@Description: addToCart
     *@Param: [cartDTO, bindingResult, map]
     *@return: org.springframework.web.servlet.ModelAndView
     *@Author: XINPENG ZHU
     *@Date: 2018/11/1
     *@Time: 0:25
     */
    @PostMapping("/save")
    public ModelAndView addToCart(@Valid CartDTO cartDTO,
                                  BindingResult bindingResult,
                                  Map<String, Object> map) {
        if(bindingResult.hasErrors()){
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "sell/buyer/product/list");
            return new ModelAndView("common/error",map);
        }



        try {
            cartService.addItem(cartDTO);
        } catch (SellException e) {
            log.error("[Add to cart] Exception{}", e);
            map.put("msg", e.getMessage());
            map.put("url", "/sell/buyer/product/list");
            return new ModelAndView("common/error", map);
        }

        map.put("msg", ResultEnum.CART_ADD_SUCCESS.getMessage());
        map.put("url", "/sell/buyer/cart/list");
        return new ModelAndView("common/success",map);
    }


    /**
     *@Description: checkout
     *@Param: [model, principal]
     *@return: java.lang.String
     *@Author: XINPENG ZHU
     *@Date: 2018/11/1
     *@Time: 0:25
     */
    @PostMapping("/checkout")
    public  ModelAndView checkout(Model model, Principal principal,Map<String, Object> map) {
        UserInfo user = userService.findOne(principal.getName());// Email as username
        cartService.checkout(user);
//
//        model.addAttribute("msg", ResultEnum.CART_CHECKOUT_SUCCESS.getMessage());
//        model.addAttribute("url", "/sell/buyer/cart/list");
//        //TODO
//        return "/common/success";

        map.put("msg", ResultEnum.CART_CHECKOUT_SUCCESS.getMessage());
        map.put("url", "/sell/buyer/order/list");
        return new ModelAndView("common/success",map);
    }

    /**
    *@Description: remove
    *@Param: [productId, map]
    *@return: org.springframework.web.servlet.ModelAndView
    *@Author: XINPENG ZHU
    *@Date: 2018/11/1
    *@Time: 0:25
    */
    @GetMapping("/remove")
    public ModelAndView remove(@RequestParam("product_id") String productId,
                               Map<String, Object> map) {


        try {
            cartService.removeItem(productId);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/cart/list");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/sell/buyer/cart/list");
        return new ModelAndView("common/success", map);
    }

    /**
     *@Description: plus
     *@Param: [poductId, quantity, map]
     *@return: org.springframework.web.servlet.ModelAndView
     *@Author: XINPENG ZHU
     *@Date: 2018/11/1
     *@Time: 0:26
     */
    @GetMapping("/change")
    public ModelAndView plus(@RequestParam("product_id") String poductId,
                       @RequestParam("quantity") Integer quantity,
                       Map<String, Object> map) {


        try {
            cartService.updateQuantity(poductId, quantity);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/cart/list");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/sell/buyer/cart/list");
        return new ModelAndView("common/success", map);
    }
}
