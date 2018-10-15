package com.imooc.sell.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {

    // 公众平台ID
    private String mpAppId;

    //公众平台密钥
    private String mpAppSecret;

    //微信开放平台的配置
    //开放平台ID
    private String openAppId;

    //开放平台密钥
    private String openAppSecret;

    //微信支付的配置
    // mechanist ID
    private String mchId;

    // 商户号
    private String mchKey;

    // 商户证书路径
    private String keyPath;

    //微信支付异步通知地址
    private String notifyUrl;
}
