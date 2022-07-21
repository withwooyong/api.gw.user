package com.spring.cloud.api.gw.user.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Feign client에 연동되어 있는 서버 실행되어 있어야 함.
 */
@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getHello() {
        String hello_world = userService.getHello("hello world");
        System.out.println(hello_world);
    }

    @Test
    void getAuth() {
        String userName = userService.getAuth("userName");
        System.out.println(userName);
    }

    @Test
    void getOrder() {
        String orderName = userService.getOrder("orderName");
        System.out.println(orderName);
    }

    @Test
    void getPay() {
        String kakao = userService.getPay("kakao", 1000);
        System.out.println(kakao);
    }

    @Test
    void getGwHello() {
        String gwHello = userService.getGwHello("userName", "orderName", "payType", 1000);
        System.out.println(gwHello);
    }
}