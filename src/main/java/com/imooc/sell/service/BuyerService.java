package com.imooc.sell.service;


import com.imooc.sell.dto.OrderDTO;

//buyer
public interface BuyerService {

    // find one order
    OrderDTO findOrderOne(String openid, String orderId) ;

    // cancel order
    OrderDTO cancelOrder(String openid, String orderId) ;

}
