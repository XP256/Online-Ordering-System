package com.imooc.sell.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class OrderForm {

    // buyer name
    @NotEmpty(message = "name required")
    private String name;

    // buyer phone number
    @NotEmpty(message = "phone number required")
    private String phone;

    //buyer address
    @NotEmpty(message = "address required")
    private String address;

    //buyer openid
    @NotEmpty(message = "openid required")
    private String openid;

    //cart
    @NotEmpty(message = "cart cannot be empty")
    private String items;

}
