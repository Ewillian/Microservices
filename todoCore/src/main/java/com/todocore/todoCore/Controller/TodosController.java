package com.todocore.todoCore.Controller;

import com.todocore.todoCore.Entities.Todo;
import com.todocore.todoCore.Repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path="")
public class TodosController {
    @Autowired
    private TodoRepository repository;

    public TodoRepository getRepository() {
        return repository;
    }

    public void setRepository(TodoRepository repository) {
        this.repository = repository;
    }

    @GetMapping(path="/todos")
    public @ResponseBody
    List<Todo> getTodos() {
        System.out.println("Getting all Todos.");
        return repository.findAll();
    }

    @GetMapping("/todo/{id}")
    public @ResponseBody
    Todo getTodoById(@RequestParam Long id) {
        // This returns a JSON or XML with the user
        return repository.findById(id).get();
    }

    @GetMapping("/todos")
    public @ResponseBody
    Todo getTodoByUserId(@RequestParam Long userId) {
        // This returns a JSON or XML with the user
        return (Todo) repository.findAllByUserId(userId);
    }

    @PostMapping(path="/todo/add") // Map ONLY POST Requests
    public @ResponseBody Todo addTodo (@RequestBody Todo newTodo) {
        return repository.save(newTodo);
    }

    @PutMapping("/todo/{id}")
    Todo updateTodo(@RequestBody Todo newTodo, @PathVariable Long id) {

        return repository.findById(id).map(todo -> {
            todo.setUserId(newTodo.getUserId());
            todo.setTitle(newTodo.getTitle());
            todo.setCompleted(newTodo.getCompleted());
            return repository.save(todo);
        }).orElseGet(() -> {
            newTodo.setId(id);
            return repository.save(newTodo);
        });
    }

    @DeleteMapping("/todos/{id}")
    void deleteTodo(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
