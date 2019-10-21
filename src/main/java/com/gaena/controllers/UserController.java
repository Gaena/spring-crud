package com.gaena.controllers;

import com.gaena.domain.User;
import com.gaena.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public User findUserByID(@PathVariable Long id) {
        return userService.getUserByID(id);
    }

    @PostMapping
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PutMapping
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/find/{name}")
    public User findUserByName(@PathVariable String name) {
        return userService.getUserByName(name);
    }


    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

}
