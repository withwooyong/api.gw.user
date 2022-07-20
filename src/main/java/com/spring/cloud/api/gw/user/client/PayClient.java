package com.spring.cloud.api.gw.user.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "pay", url = "${external-api.order.url}")
public interface PayClient {

    @GetMapping(path = "/payName")
    String getPayName(@RequestParam String payName);

}
