package com.imooc.sell.enums;


//product status

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

@Getter
public enum ProductStatusEnum implements CodeEnum{
    UP(0,"Normal, on board!"),
    DOWN(1,"Out of stock!")
    ;

    private Integer code;

    private String message;

    @JsonIgnore
    ProductStatusEnum(Integer code,String message) {
        this.code = code;
        this.message=message;
    }


}
