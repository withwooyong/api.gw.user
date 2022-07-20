package com.spring.cloud.api.gw.user.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "order", url = "${external-api.order.url}")
public interface OrderClient {

    @GetMapping(path = "/orderName")
    String getOrderName(@RequestParam String orderName);

}
