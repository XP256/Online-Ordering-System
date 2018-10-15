package com.imooc.sell.service;

import com.imooc.sell.dataobject.OrderMaster;
import com.imooc.sell.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface OrderService {

    //create order
    OrderDTO create(OrderDTO orderDTO);

    //find single order
    OrderDTO findOne(String orderId);

    //find order list, search order lists of a single person
    Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);

    //cancel order
    OrderDTO cancel(OrderDTO orderDTO);

    //finish order
    OrderDTO finish(OrderDTO orderDTO);

    //pay order
    OrderDTO paid(OrderDTO orderDTO);

    //find order list, search for all order lists
    Page<OrderDTO> findList(Pageable pageable);
}
