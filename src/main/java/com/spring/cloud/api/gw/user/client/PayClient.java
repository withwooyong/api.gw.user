package com.spring.cloud.api.gw.user.client;

import com.spring.cloud.api.gw.user.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "pay", url = "${external-api.pay.url}", configuration = FeignConfig.class)
public interface PayClient {

    @GetMapping(path = "/pay")
    String getPay(@RequestParam(name = "payType") String payType,
                  @RequestParam(name = "price") int price);

}
