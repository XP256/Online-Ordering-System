package com.imooc.sell.VO;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

//http请求返回的最外层对象

@Data
////below annotation means only return non-null attributes
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultVO<T> implements Serializable {


    private static final long serialVersionUID = 8848731630266574133L;

    //error code
    private Integer code;
    //notice message
    private String msg;
    //specific return results
    private T data;

}
