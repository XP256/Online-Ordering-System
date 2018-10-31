package com.imooc.sell.controller;


import com.imooc.sell.dataobject.UserInfo;
import com.imooc.sell.repository.UserInfoRepository;
import com.imooc.sell.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;


@Controller
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserInfoRepository userInfoRepository;


    @GetMapping("/login")
    public ModelAndView login(Map<String, Object> map) {
        return new ModelAndView("/user/login",map);
    }


    @GetMapping("/home")
    public String Handler(Authentication authentication) {
        if (authentication == null || authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_CUSTOMER"))) {
            System.out.println(authentication.getName());
            return "forward:" + "/buyer/product/list";
        } else {
            return "forward:" + "/seller/order/list";
        }

    }

//    @GetMapping("/register")
//    public String showForm(UserInfo user) {
//
//        return "user/register";
//    }
    @GetMapping("/register")
    public ModelAndView showForm(Map<String, Object> map) {

        return new ModelAndView("/user/register",map);
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") UserInfo user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        UserInfo userWithEnteredEmailExists = userService.findOne(user.getEmail());
        if (userWithEnteredEmailExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }

        // 使用BindingResult来验证表单数据的正确性
        if (bindingResult.hasErrors()) {
            // 将提交的表单内容原封不动的返回到页面再展示出来
            redirectAttributes.addFlashAttribute("user", user);
            return "register";
        }
        userService.save(user);
        return "redirect:" + "/login";
    }

    @GetMapping("/profiles")
    public String showUser(UserInfo user) {
        return "/user/show";
    }

    @PostMapping("/profiles")
    public String editUser(@Valid @ModelAttribute("user") UserInfo user, BindingResult bindingResult, RedirectAttributes redirectAttributes, Principal principal, Model model) {
        // 使用BindingResult来验证表单数据的正确性
        if (bindingResult.hasErrors()) {
            // 将提交的表单内容原封不动的返回到页面再展示出来
            redirectAttributes.addFlashAttribute("user", user);
            return "/user/show";
        }
        //Access deny
        if (!principal.getName().equals(user.getEmail())) {
            return "redirect:" + "/403";
        }
        userService.update(user);
        model.addAttribute("msg", "Profils is updated!");
        model.addAttribute("url", "/profiles");
        return "common/success";
    }

    @GetMapping("/403")
    public String accessDeney(Model model) {
        model.addAttribute("msg", "Access denied!");
        return "/common/error";
    }
    @GetMapping("/404")
    public String notFound(Model model) {
        model.addAttribute("msg", "Page not found!");
        return "/common/error";
    }



}
