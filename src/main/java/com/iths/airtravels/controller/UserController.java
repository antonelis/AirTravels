package com.iths.airtravels.controller;

import com.iths.airtravels.entity.User;
import com.iths.airtravels.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/findall")
    public Iterable<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/id/{id}")
    public Optional<User> findUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        logger.info("Created User " + user.getUsername());
        return userService.createUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

    @GetMapping("/getauthenticateduser")
    public User getAuthenticatedUser() {
        return userService.getAuthenticatedUser();
    }
}
