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

    public String getHello(String userName, String orderName, String payType) {
        String resultUser = authClient.getToken(userName);
        String resultOrder = orderClient.getName(orderName);
        String resultPay = payClient.getType(payType);

        log.info("resultOrder={}", resultOrder);
        log.info("resultUser={}", resultUser);
        log.info("resultPay={}", resultPay);

        return new StringBuilder()
                .append(resultUser).append("-")
                .append(resultOrder).append("-")
                .append(resultPay).toString();
    }


}
