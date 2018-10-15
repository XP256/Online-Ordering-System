//package com.imooc.sell.repository;
//
//import com.imooc.sell.dataobject.UserInfo;
//import com.imooc.sell.utils.KeyUtil;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import static org.junit.Assert.*;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class UserInfoRepositoryTest {
//    @Autowired
//    private UserInfoRepository repository;
//
//    @Test
//    public void save(){
//        UserInfo user = new UserInfo();
//        user.setId(KeyUtil.getUniqueKey());
//        user.setActive(true);
//        user.setAddress("200 University West");
//        user.setEmail("123@uw.ca");
//        user.setPhone("123432142");
//        user.setName("Amy");
//        user.setPassword("qwertyui");
//        user.setRole("ROLE_CUSTOMER");
//
//        UserInfo result = repository.save(user);
//        Assert.assertNotNull(result);
//    }
//
//    @Test
//    public void findByEmail() {
//        UserInfo result = repository.findByEmail("123@uw.ca");
//        Assert.assertEquals("1538957962163339364",result.getId());
//    }
//}