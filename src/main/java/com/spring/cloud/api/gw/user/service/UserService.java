package com.spring.cloud.api.gw.user.service;

import com.spring.cloud.api.gw.user.client.AuthClient;
import com.spring.cloud.api.gw.user.client.OrderClient;
import com.spring.cloud.api.gw.user.client.PayClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final AuthClient authClient;
    private final OrderClient orderClient;
    private final PayClient payClient;

    public String getHello(String hello) {
        return hello;
    }

    public String getAuth(String userName) {
        return authClient.getToken(userName);
    }

    public String getOrder(String orderName) {
        return orderClient.getName(orderName);
    }

    public String getPay(String payType, int price) {
        return payClient.getPay(payType, price);
    }

    public String getGwHello(String userName, String orderName, String payType, int price) {
        String resultUser = authClient.getToken(userName);
        String resultOrder = orderClient.getName(orderName);
        String resultPay = payClient.getPay(payType, price);

        log.info("resultOrder={}", resultOrder);
        log.info("resultUser={}", resultUser);
        log.info("resultPay={}", resultPay);

        return resultUser + "-" +
                resultOrder + "-" +
                resultPay;
    }
}
