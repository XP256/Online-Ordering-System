package com.imooc.sell.service;

import com.imooc.sell.dataobject.UserInfo;

import java.util.Collection;

public interface UserService {
    UserInfo findOne(String email);
    Collection<UserInfo> findByRole(String role);
    void save(UserInfo user);
    void update(UserInfo user);
}
