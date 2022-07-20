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
     * http://localhost:8081/user/execute?userName=wooyong&orderName=MacBook&
     */
    @GetMapping(path = "/execute")
    public ResponseEntity<String> getHello(@RequestParam String userName, @RequestParam String orderName, @RequestParam String payType) {
        String result = userService.getHello(userName, orderName, payType);
        return ResponseEntity.ok(result);
    }
}
