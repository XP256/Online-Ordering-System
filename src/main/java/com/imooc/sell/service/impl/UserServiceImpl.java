package com.imooc.sell.service.impl;

import com.imooc.sell.PasswordEncoder.MyPasswordEncoder;
import com.imooc.sell.dataobject.UserInfo;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.repository.UserInfoRepository;
import com.imooc.sell.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;


@Service
@DependsOn("passwordEncoder")
public class UserServiceImpl implements UserService {
    @Autowired
    private MyPasswordEncoder passwordEncoder;
    @Autowired
    UserInfoRepository userInfoRepository;
    /**
     *@Description: findOne
     *@Param: [email]
     *@return: com.imooc.sell.dataobject.UserInfo
     *@Author: XINPENG ZHU
     *@Date: 2018/10/31
     *@Time: 13:20
     */
    @Override
    public UserInfo findOne(String email) {
        return userInfoRepository.findByEmail(email);
    }

    /**
     *@Description: findByRole
     *@Param: [role]
     *@return: java.util.Collection<com.imooc.sell.dataobject.UserInfo>
     *@Author: XINPENG ZHU
     *@Date: 2018/10/31
     *@Time: 22:37
     */
    @Override
    public Collection<UserInfo> findByRole(String role) {
        return userInfoRepository.findAllByRole(role);
    }

    /**
     *@Description: save
     *@Param: [user]
     *@return: void
     *@Author: XINPENG ZHU
     *@Date: 2018/10/31
     *@Time: 13:16
     */
    @Override
    @Transactional
    public void save(UserInfo user) {
//        System.out.println(passwordEncoder.encode(user.getPassword()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));

//        user.setPassword(user.getPassword());
        try {
            userInfoRepository.save(user);
        } catch (Exception e) {
            throw new SellException(ResultEnum.VALID_ERROR);
        }
    }

    /**
     *@Description: update
     *@Param: [user]
     *@return: void
     *@Author: XINPENG ZHU
     *@Date: 2018/10/31
     *@Time: 13:16
     */
    @Override
    public void update(UserInfo user) {
        UserInfo oldUser = userInfoRepository.findByEmail(user.getEmail());
//        oldUser.setPassword(user.getPassword());
        oldUser.setPassword(passwordEncoder.encode(user.getPassword()));
        oldUser.setName(user.getName());
        oldUser.setPhone(user.getPhone());
        oldUser.setAddress(user.getAddress());
        userInfoRepository.save(oldUser);
    }
}
