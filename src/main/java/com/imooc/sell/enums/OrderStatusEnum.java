package com.imooc.sell.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnum implements CodeEnum {
    NEW(0,"NEW ORDER!"),
    FINISHED(1,"FINISHED ORDER!"),
    CANCELLED(2,"CANCELED!"),
    ;

    private Integer code;

    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
