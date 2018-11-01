package com.imooc.sell.service.impl;

import com.imooc.sell.dataobject.OrderDetail;
import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.dataobject.UserInfo;
import com.imooc.sell.dto.CartDTO;
import com.imooc.sell.dto.Item;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.ProductStatusEnum;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.service.CartService;
import com.imooc.sell.service.OrderService;
import com.imooc.sell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.*;


@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartServiceImpl implements CartService {
    @Autowired
    ProductService productService;
//    @Autowired
//    OrderRepository orderRepository;
    @Autowired
    OrderService orderService;

    private Map<String, Item> map = new LinkedHashMap<>();


    /**
     *@Description: addItem
     *@Param: [cartDTO]
     *@return: void
     *@Author: XINPENG ZHU
     *@Date: 2018/11/1
     *@Time: 0:24
     */
    @Override
    public void addItem(CartDTO cartDTO) {
        ProductInfo productInfo = productService.findOne(cartDTO.getProductId());

        if (productInfo.getProductStatus() == ProductStatusEnum.DOWN.getCode()) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        // Check whether is in the cart
        if(map.containsKey(cartDTO.getProductId())){
            // Update quantity
            Integer old = map.get(cartDTO.getProductId()).getProductQuantity();
            cartDTO.setProductQuantity(old + cartDTO.getProductQuantity());
        }

        map.put(cartDTO.getProductId(), new Item(productInfo, cartDTO.getProductQuantity()));
    }

    /**
     *@Description: removeItem
     *@Param: [productId]
     *@return: void
     *@Author: XINPENG ZHU
     *@Date: 2018/11/1
     *@Time: 0:25
     */
    @Override
    public void removeItem(String productId) {
        if (!map.containsKey(productId)) throw new SellException(ResultEnum.PRODUCT_NOT_IN_CART);
        map.remove(productId);
    }

    /**
     *@Description: updateQuantity
     *@Param: [productId, quantity]
     *@return: void
     *@Author: XINPENG ZHU
     *@Date: 2018/11/1
     *@Time: 0:25
     */
    @Override
    public void updateQuantity(String productId, Integer quantity) {
        if (!map.containsKey(productId)) throw new SellException(ResultEnum.PRODUCT_NOT_IN_CART);
        Item item = map.get(productId);
        Integer max = item.getProductInfo().getProductStock();
        if(quantity > 0) {
            item.setProductQuantity(quantity > max ? max : quantity);
        }
    }

    /**
     *@Description: findAll
     *@Param: []
     *@return: java.util.Collection<com.imooc.sell.dto.Item>
     *@Author: XINPENG ZHU
     *@Date: 2018/11/1
     *@Time: 0:25
     */
    @Override
    public Collection<Item> findAll() {
        return map.values();
    }

// @Override
//    @Transactional
//    public void checkout(UserInfo user) {
//        OrderMain orderMain = new OrderMain(user);
//        for (String productId : map.keySet()) {
//            Item item = map.get(productId);
//            ProductInOrder productInOrder = new ProductInOrder(item.getProductInfo(), item.getQuantity());
//            productInOrder.setOrderMain(orderMain);
//            orderMain.getProducts().add(productInOrder);
//            productService.decreaseStock(productId, item.getQuantity());
//        }
//        orderMain.setOrderAmount(getTotal());
//        orderRepository.save(orderMain);
//        map.clear();
//    }
    /**
     *@Description: checkout
     *@Param: [user]
     *@return: void
     *@Author: XINPENG ZHU
     *@Date: 2018/11/1
     *@Time: 0:25
     */
    @Override
    @Transactional
    public void checkout(UserInfo user) {
        Collection<Item> items = findAll();

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(user.getName());
        orderDTO.setBuyerPhone(user.getPhone());
        orderDTO.setBuyerAddress(user.getAddress());
        orderDTO.setBuyerOpenid(user.getId());
        Date date = new Date();
        orderDTO.setCreateTime(date);
        List<OrderDetail> orderDetailList = new ArrayList<>();
        for (Item item : items) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setProductId(item.getProductInfo().getProductId());
            orderDetail.setProductQuantity(item.getProductQuantity());
            orderDetailList.add(orderDetail);
        }


        orderDTO.setOrderDetailList(orderDetailList);

        orderService.create(orderDTO);

        map.clear();
    }


    /**
     *@Description: getTotal
     *@Param: []
     *@return: java.math.BigDecimal
     *@Author: XINPENG ZHU
     *@Date: 2018/11/1
     *@Time: 0:25
     */
    @Override
    public BigDecimal getTotal() {
        Collection<Item> items = findAll();
        BigDecimal total = new BigDecimal(0);
        for (Item item : items) {
            BigDecimal price = item.getProductInfo().getProductPrice();
            BigDecimal quantity = new BigDecimal(item.getProductQuantity());
            total = total.add(price.multiply(quantity));
        }
        return total;
    }

}
