package com.spring.cloud.api.gw.user.controller;

import com.spring.cloud.api.gw.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * http://localhost:8081/user
     */
    @GetMapping
    ResponseEntity<String> getHello() {
        String hello = userService.getHello("hello");
        return ResponseEntity.ok(hello);
    }

    /**
     * http://localhost:8081/user/auth?userName=wooyong
     */
    @GetMapping(path = "/auth")
    ResponseEntity<String> getAuth(@RequestParam String userName) {
        String auth = userService.getAuth(userName);
        return ResponseEntity.ok(auth);
    }

    /**
     * http://localhost:8081/user/order?orderName=yanadoo
     */
    @GetMapping(path = "/order")
    ResponseEntity<String> getOrder(@RequestParam String orderName) {
        String order = userService.getOrder(orderName);
        return ResponseEntity.ok(order);
    }

    /**
     * http://localhost:8081/user/pay?payType=kakao&price=1000
     */
    @GetMapping(path = "/pay")
    ResponseEntity<String> getPay(@RequestParam String payType, @RequestParam int price) {
        String type = userService.getPay(payType, price);
        return ResponseEntity.ok(type);
    }

    /**
     * http://localhost:8081/user/execute?userName=Ted&orderName=MacBook&payType=kakao&price=1000
     */
    @GetMapping(path = "/execute")
    public ResponseEntity<String> getGwHello(@RequestParam String userName, @RequestParam String orderName, @RequestParam String payType, @RequestParam int price) {
        String result = userService.getGwHello(userName, orderName, payType, price);
        return ResponseEntity.ok(result);
    }
}
