package com.jsonplaceholdrr.usertodos.Clients;

import com.jsonplaceholdrr.usertodos.Entities.Todos;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@FeignClient(name = "TodoClient")
public interface ITodosClient {

    @RequestLine("GET ")
    List<Todos> getAll();

    @RequestLine("GET ?userId={userId}")
    List<Todos> getAllForUser(@Param("userId") Long userId);
}
