package com.spring.cloud.api.gw.user.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "auth", url = "${external-api.auth.url}")
public interface AuthClient {

    @GetMapping(path = "/token")
    String getToken(@RequestParam String userName);
}
