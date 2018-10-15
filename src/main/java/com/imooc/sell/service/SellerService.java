package com.imooc.sell.service;


import com.imooc.sell.dataobject.SellerInfo;

//seller side
//find SellerInfo by openId
public interface SellerService {

    /**
     *
     * @param openid
     * @return
     */
    SellerInfo findSellerInfoByOpenid(String openid);
}
