package com.imooc.sell.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {

    SUCCESS(0,"SUCCESS!"),

    PARAM_ERROR(1,"Parameters error!"),

    PRODUCT_NOT_EXIST(10,"Product does not exist!"),

    PRODUCT_STOCK_ERROR(11,"Product stock error!"),

    PRODUCT_NOT_IN_CART(111,"Product not in cart!"),

    ORDER_NOT_EXIST(12,"Order does not exist!"),

    ORDERDETAIL_NOT_EXIST(13,"Order detail does not exist!"),

    ORDER_STATUS_ERROR(14,"Order status error!"),

    ORDER_UPDATE_FAILURE(15,"Order update fails!"),

    ORDER_DETAILS_EMPTY(16,"No details in order!"),

    ORDER_PAY_STATUS_ERROR(17,"Pay status error!"),

    CART_EMPTY(18,"Cart is empty!"),

    ORDER_OWNER_ERROR(19,"This order does not belong to current user!"),

    WX_MP_ERROR(20,"Wechat MP error!"),

    WXPAY_NOTIFY_MONEY_VERIFY_ERROR(21,"Wechat pay notify, amount verify error, does not match!"),

    ORDER_CANCEL_SUCCESS(22,"Order cancel success!"),

    ORDER_FINISH_SUCCESS(23,"Order finish success!"),

    PRODUCT_STATUS_ERROR(24,"Product status error!"),

    LOGIN_FAIL(25,"Login fail, information error!"),

    LOGOUT_SUCCESS(26,"Log out successfully!"),

    CART_ADD_SUCCESS(27,"Add to cart successfully!"),

    VALID_ERROR(28,"VALID ERROR!"),

    ;


    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


}
