package com.imooc.sell.service.impl;

import com.imooc.sell.dataobject.SellerInfo;
import com.imooc.sell.repository.SellerInfoRepository;
import com.imooc.sell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoRepository repository;

    /**
     *@Description: findSellerInfoByOpenid
     *@Param: [openid]
     *@return: com.imooc.sell.dataobject.SellerInfo
     *@Author: XINPENG ZHU
     *@Date: 2018/11/1
     *@Time: 22:33
     */
    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return repository.findByOpenid(openid);
    }
}
