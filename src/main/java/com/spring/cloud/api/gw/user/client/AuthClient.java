package com.spring.cloud.api.gw.user.client;

import com.spring.cloud.api.gw.user.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "auth", url = "${external-api.auth.url}", configuration = FeignConfig.class)
public interface AuthClient {

    @GetMapping(path = "/token")
    String getToken(@RequestParam(name = "userName") String userName);
}
