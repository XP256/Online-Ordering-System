
package com.imooc.sell.controller;

//import com.imooc.sell.config.ProjectUrlConfig;
import com.imooc.sell.config.ProjectUrlConfig;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;


@Controller
@RequestMapping("/wechat")
@Slf4j
public class WechatController {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WxMpService wxOpenService;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;


    //从外部访问/authorize，构造网页授权url，跳转并同意授权后会再跳转到redirect_uri/?code=CODE&state=STATE,即获取了code


    /**
     *@Description: authorize
     *@Param: [returnUrl]
     *@return: java.lang.String
     *@Author: XINPENG ZHU
     *@Date: 2018/11/1
     *@Time: 22:30
     */
    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl) {
        //1. 配置
        //2. 调用方法

        String redirect_uri = projectUrlConfig.getWechatMpAuthorize() + "/sell/wechat/userInfo";

        //构造授权url
        String result = wxMpService.oauth2buildAuthorizationUrl(redirect_uri, WxConsts.OAUTH2_SCOPE_BASE, URLEncoder.encode(returnUrl));
        //https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx8135a8cde0d04fda&redirect_uri=http://sellxp.mynatapp.cc/sell/weixin/auth&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect

        //下面这个“redirect：”的字符串固定，不可修改！ http固定跳转格式
        //跳转到上面构造的授权url
        //即访问authorize方法会跳转到授权界面
        return "redirect:" + result;
    }



    //同意授权后跳转至redirect_uri/?code=CODE&state=STATE即/userInfo方法，已得到code，换取access_token,openid


    /**
     *@Description: userInfo
     *@Param: [code, returnUrl]
     *@return: java.lang.String
     *@Author: XINPENG ZHU
     *@Date: 2018/11/1
     *@Time: 22:30
     */
    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code,
                           @RequestParam("state") String returnUrl) {
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();

        log.info("1. code = {}",code);
        log.info("1. returnUrl = {}",returnUrl);

        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.error("【微信网页授权】{}", e);
            throw new SellException(ResultEnum.WX_MP_ERROR.getCode(), e.getError().getErrorMsg());
        }

        String openId = wxMpOAuth2AccessToken.getOpenId();

        log.info("2. code = {}",code);
        log.info("openId = {}",openId);
        String tmp = returnUrl + "?openid=" + openId;
        log.info("redirect:",tmp);
        //此处的跳转地址为api文档内约定好的
        return "redirect:" + "www.baidu.com";

//        return "redirect:" + returnUrl + "?openid=" + openId;
    }



    /**
     *@Description: qrAuthorize
     *@Param: [returnUrl]
     *@return: java.lang.String
     *@Author: XINPENG ZHU
     *@Date: 2018/11/1
     *@Time: 22:30
     */
    @GetMapping("/qrAuthorize")
    public String qrAuthorize(@RequestParam("returnUrl") String returnUrl) {
        // redirect url
        String url = projectUrlConfig.getWechatOpenAuthorize() + "/sell/wechat/qrUserInfo";

        //构造
        String redirectUrl = wxOpenService.buildQrConnectUrl(url, WxConsts.QRCONNECT_SCOPE_SNSAPI_LOGIN, URLEncoder.encode(returnUrl));
        return "redirect:" + redirectUrl;
    }



    /**
     *@Description: qrUserInfo
     *@Param: [code, returnUrl]
     *@return: java.lang.String
     *@Author: XINPENG ZHU
     *@Date: 2018/11/1
     *@Time: 22:31
     */
    @GetMapping("/qrUserInfo")
    public String qrUserInfo(@RequestParam("code") String code,
                             @RequestParam("state") String returnUrl) {
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();

        try {
            wxMpOAuth2AccessToken = wxOpenService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.error("【微信网页授权】{}", e);
            throw new SellException(ResultEnum.WX_MP_ERROR.getCode(), e.getError().getErrorMsg());
        }

        log.info("wxMpOAuth2AccessToken={}",wxMpOAuth2AccessToken);
        String openId = wxMpOAuth2AccessToken.getOpenId();

        //此处的跳转地址为api文档内约定好的
        return "redirect:" + returnUrl + "?openid=" + openId;
    }

}