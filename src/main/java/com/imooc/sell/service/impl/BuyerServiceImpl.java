package com.imooc.sell.service.impl;

import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.service.BuyerService;
import com.imooc.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {

        return checkOrderOwner(openid,orderId);

    }

    /**
     *@Description: cancelOrder
     *@Param: [openid, orderId]
     *@return: com.imooc.sell.dto.OrderDTO
     *@Author: XINPENG ZHU
     *@Date: 2018/11/1
     *@Time: 22:31
     */
    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {

        OrderDTO orderDTO = checkOrderOwner(openid, orderId);
        if(orderDTO==null){
            log.error("[cancel order] cannot find order, orderId= {}", orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        return orderService.cancel(orderDTO);
    }


    /**
     *@Description: checkOrderOwner
     *@Param: [openid, orderId]
     *@return: com.imooc.sell.dto.OrderDTO
     *@Author: XINPENG ZHU
     *@Date: 2018/11/1
     *@Time: 22:31
     */
    private OrderDTO checkOrderOwner(String openid, String orderId){
        OrderDTO orderDTO = orderService.findOne(orderId);
        if(orderDTO == null){
            return null;
        }
        // check if this order belongs to current user
        if(!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)){
            log.error("[find order] openid not same , openid = {}, orderDTP = {}", openid, orderDTO);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
