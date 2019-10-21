package com.nc.backend.controller;

import com.nc.backend.model.User;
import com.nc.backend.repositories.UserRepository;
import com.nc.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("show")
    Iterable<User> getAllUsers() {
        return userService.findAll();
    }

    @RequestMapping(path="/update", method= RequestMethod.GET)
    public String updateUser(@RequestParam int id, @RequestParam String newDescription) {
        Optional<User> user = userService.findById(id);
        User user1 = user.get();
        user1.setProfile_description(newDescription);
        userService.save(user1);
        return "Updated successfully";
    }
}
