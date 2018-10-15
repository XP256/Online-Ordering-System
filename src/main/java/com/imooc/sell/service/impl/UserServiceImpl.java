//package com.imooc.sell.service.impl;
//
//import com.imooc.sell.dataobject.UserInfo;
//import com.imooc.sell.enums.ResultEnum;
//import com.imooc.sell.exception.SellException;
//import com.imooc.sell.repository.UserInfoRepository;
//import com.imooc.sell.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.DependsOn;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Collection;
//
//
//@Service
//@DependsOn("passwordEncoder")
//public class UserServiceImpl implements UserService {
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//    @Autowired
//    UserInfoRepository userInfoRepository;
//    @Override
//    public UserInfo findOne(String email) {
//        return userInfoRepository.findByEmail(email);
//    }
//
//    @Override
//    public Collection<UserInfo> findByRole(String role) {
//        return userInfoRepository.findAllByRole(role);
//    }
//
//    @Override
//    @Transactional
//    public void save(UserInfo user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        //user.setPassword(user.getPassword());
//        try {
//            userInfoRepository.save(user);
//        } catch (Exception e) {
//            throw new SellException(ResultEnum.VALID_ERROR);
//        }
//    }
//
//    @Override
//    public void update(UserInfo user) {
//        UserInfo oldUser = userInfoRepository.findByEmail(user.getEmail());
//        //oldUser.setPassword(user.getPassword());
//        oldUser.setPassword(passwordEncoder.encode(user.getPassword()));
//        oldUser.setName(user.getName());
//        oldUser.setPhone(user.getPhone());
//        oldUser.setAddress(user.getAddress());
//        userInfoRepository.save(oldUser);
//    }
//}
