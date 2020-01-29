package com.usercore.userCore.Controller;

import com.usercore.userCore.Entities.User;
import com.usercore.userCore.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@Controller
@RequestMapping(path="")
public class UserController {

    @Autowired
    private UserRepository repository;

    public UserRepository getRepository() {
        return repository;
    }

    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping(path="/users")
    public @ResponseBody
    List<User> getUsers() {
        System.out.println("Getting all Todos.");
        return repository.findAll();
    }

    @GetMapping("/users/{id}")
    public @ResponseBody
    User getUserById(@PathVariable Long id) throws Exception {
        // This returns a JSON or XML with the user
        return repository.findById(id)
                .orElseThrow(() -> new Exception("no user found with id : "+id));
    }

    @PostMapping(path="/users/add") // Map ONLY POST Requests
    public @ResponseBody User addUser (@RequestBody User newAnimal) {
        return repository.save(newAnimal);
    }

    @PutMapping("/users/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id) {

        return repository.findById(id).map(user -> {
            user.setName(newUser.getName());
            user.setUsername(newUser.getUsername());
            user.setEmail(newUser.getEmail());
            user.setPhone(newUser.getPhone());
            return repository.save(user);
        }).orElseGet(() -> {
            newUser.setId(id);
            return repository.save(newUser);
        });
    }

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
