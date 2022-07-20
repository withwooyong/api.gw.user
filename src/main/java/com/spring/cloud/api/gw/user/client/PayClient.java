package com.spring.cloud.api.gw.user.client;

import com.spring.cloud.api.gw.user.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "pay", url = "${external-api.order.url}", configuration = FeignConfig.class)
public interface PayClient {

    @GetMapping(path = "/type")
    String getType(@RequestParam String payType);

}
