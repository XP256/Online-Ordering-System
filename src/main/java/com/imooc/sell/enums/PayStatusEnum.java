package com.imooc.sell.enums;

import lombok.Getter;

@Getter
public enum PayStatusEnum implements CodeEnum {

    WAIT(0,"NOT PAID YET!"),
    SUCCESS(1,"PAID SUCCESSFUL!"),

    ;


    private Integer code;

    private String message;


    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
