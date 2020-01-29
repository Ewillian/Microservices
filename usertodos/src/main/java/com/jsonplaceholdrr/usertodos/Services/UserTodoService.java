package com.jsonplaceholdrr.usertodos.Services;

import com.jsonplaceholdrr.usertodos.Clients.ITodosClient;
import com.jsonplaceholdrr.usertodos.Clients.IUserClient;
import com.jsonplaceholdrr.usertodos.Entities.Todos;
import com.jsonplaceholdrr.usertodos.Entities.User;
import com.jsonplaceholdrr.usertodos.Entities.UserTodosDTO;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import jdk.internal.jline.internal.Log;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Data
public class UserTodoService {

    private ITodosClient iclientTodosClient = Feign.builder()
            .client(new OkHttpClient())
            .encoder(new GsonEncoder())
            .decoder(new GsonDecoder())
            .logger(new Slf4jLogger())
            .logLevel(Logger.Level.FULL)
            .target(ITodosClient.class, "http://localhost:8081/todos");

    private IUserClient iclientUsersClient = Feign.builder()
            .client(new OkHttpClient())
            .encoder(new GsonEncoder())
            .decoder(new GsonDecoder())
            .logger(new Slf4jLogger())
            .logLevel(Logger.Level.FULL)
            .target(IUserClient.class, "http://localhost:8082/users");


    public UserTodosDTO getUserTodos(Long userId){
        User user = iclientUsersClient.getUserField(userId);
        //log.info("GetAll");
        //log.info("GetAll");
        List<Todos> userTodos = iclientTodosClient.getAllForUser(userId);

        return new UserTodosDTO(user, userTodos);
    };
}
