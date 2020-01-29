package com.jsonplaceholdrr.usertodos.Clients;


import com.jsonplaceholdrr.usertodos.Entities.User;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@Component
@FeignClient(name = "UserClient")
public interface IUserClient {

    @RequestLine("GET /{id}")
    User getUserField(@Param("id") Long id);
}
