package com.imooc.sell.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/weixin")
@Slf4j
public class WeixinController {



    /**
     *@Description: auth
     *@Param: [code]
     *@return: void
     *@Author: XINPENG ZHU
     *@Date: 2018/11/1
     *@Time: 22:31
     */
    @GetMapping("/auth")
    public void auth(@RequestParam("code") String code) {
        // get code
        log.info("entering method auth....");
        log.info("code = {}",code);

        // get access_token
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx8135a8cde0d04fda&secret=23530a5305c2774286cfd5cf081b93de&code=" + code + "&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url,String.class);
        log.info("response = {}", response);

    }
}
