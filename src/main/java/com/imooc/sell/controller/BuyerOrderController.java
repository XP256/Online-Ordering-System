//package com.imooc.sell.controller;
//
//
//import com.imooc.sell.VO.ResultVO;
//import com.imooc.sell.converter.OrderForm2OrderDTOConverter;
//import com.imooc.sell.dto.OrderDTO;
//import com.imooc.sell.enums.ResultEnum;
//import com.imooc.sell.exception.SellException;
//import com.imooc.sell.form.OrderForm;
//import com.imooc.sell.service.BuyerService;
//import com.imooc.sell.service.OrderService;
//import com.imooc.sell.utils.ResultVOUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.util.CollectionUtils;
//import org.springframework.util.StringUtils;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/buyer/order")
//@Slf4j
//public class BuyerOrderController {
//
//    @Autowired
//    private OrderService orderService;
//
//    @Autowired
//    private BuyerService buyerService;
//
//    //create order
//    @PostMapping("/create")
//    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm,
//                                               BindingResult bindingResult) {
//        if(bindingResult.hasErrors()){
//            log.error("[create order] parameters error, orderForm = {}", orderForm);
//            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
//                    bindingResult.getFieldError().getDefaultMessage());
//        }
//
//
//        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
//        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
//            log.error("[create order] cart cannot be empty");
//            throw new SellException(ResultEnum.CART_EMPTY);
//        }
//
//        OrderDTO createResult = orderService.create(orderDTO);
//
//        Map<String,String> map = new HashMap<>();
//
//        map.put("orderId",createResult.getOrderId());
//        return ResultVOUtil.success(map);
//
//
//    }
//
//    //order list
//    @GetMapping("/list")
//    public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openid,
//                                         @RequestParam(value = "page",defaultValue = "0") Integer page,
//                                         @RequestParam(value = "size", defaultValue = "10") Integer size) {
//        if (StringUtils.isEmpty(openid)){
//            log.error("[find order list] openid is empty");
//            throw new SellException(ResultEnum.PARAM_ERROR);
//        }
//
//
//        Pageable request = new PageRequest(page,size);
//        Page<OrderDTO> orderDTOPage = orderService.findList(openid,request);
//
//        return ResultVOUtil.success(orderDTOPage.getContent());
//
//    }
//
//
//    //order detail(find single order)
//    @GetMapping("/detail")
//    public ResultVO<OrderDTO> detail(@RequestParam("openid") String openid,
//                                     @RequestParam("orderId") String orderId){
//
//        OrderDTO orderDTO = buyerService.findOrderOne(openid, orderId);
//
//        return ResultVOUtil.success(orderDTO);
//
//    }
//
//    //cancel order
//    @PostMapping("/cancel")
//    public ResultVO  cancel(@RequestParam("openid") String openid,
//                            @RequestParam("orderId") String orderId) {
//
//
//        buyerService.cancelOrder(openid, orderId);
//
//        return ResultVOUtil.success();
//    }
//
//
//}



package com.imooc.sell.controller;


import com.imooc.sell.VO.ResultVO;
import com.imooc.sell.converter.OrderForm2OrderDTOConverter;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.form.OrderForm;
import com.imooc.sell.service.BuyerService;
import com.imooc.sell.service.OrderService;
import com.imooc.sell.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    //create order
    @PostMapping("/create")
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm,
                                               BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            log.error("[create order] parameters error, orderForm = {}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }


        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("[create order] cart cannot be empty");
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        OrderDTO createResult = orderService.create(orderDTO);

        Map<String,String> map = new HashMap<>();

        map.put("orderId",createResult.getOrderId());
        return ResultVOUtil.success(map);


    }

    //order list
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page",defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (StringUtils.isEmpty(openid)){
            log.error("[find order list] openid is empty");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }


        Pageable request = new PageRequest(page,size);
        Page<OrderDTO> orderDTOPage = orderService.findList(openid,request);

        return ResultVOUtil.success(orderDTOPage.getContent());

    }


    //order detail(find single order)
    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId){

        OrderDTO orderDTO = buyerService.findOrderOne(openid, orderId);

        return ResultVOUtil.success(orderDTO);

    }

    //cancel order
    @PostMapping("/cancel")
    public ResultVO  cancel(@RequestParam("openid") String openid,
                            @RequestParam("orderId") String orderId) {


        buyerService.cancelOrder(openid, orderId);

        return ResultVOUtil.success();
    }


}


