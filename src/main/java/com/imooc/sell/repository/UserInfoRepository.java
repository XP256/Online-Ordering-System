package com.imooc.sell.repository;

import com.imooc.sell.dataobject.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface UserInfoRepository extends JpaRepository<UserInfo, String> {
    UserInfo findByEmail(String email);
    Collection<UserInfo> findAllByRole(String role);

}
