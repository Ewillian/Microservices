package com.jsonplaceholdrr.usertodos.Controller;

import com.jsonplaceholdrr.usertodos.Entities.UserTodosDTO;
import com.jsonplaceholdrr.usertodos.Services.UserTodoService;
import feign.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsertodosController {

    @Autowired
    UserTodoService userTodoService;

    @RequestMapping("/userTodos/{id}")
    ResponseEntity<UserTodosDTO> getUsersTodos(@PathVariable("id") Long userId){
        return new ResponseEntity<>(userTodoService.getUserTodos(userId), HttpStatus.OK);
    }

}
