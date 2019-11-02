package com.nc.backend.controller;

import com.nc.backend.model.UserEntity;
import com.nc.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("show")
    Iterable<UserEntity> getAllUsers() {
        return userService.findAll();
    }

    @RequestMapping(path="/update", method= RequestMethod.GET)
    public String updateUser(@RequestParam int id, @RequestParam String newDescription) {
        Optional<UserEntity> user = userService.findById(id);
        UserEntity user1 = user.get();
        user1.setProfileDescription(newDescription);
        userService.save(user1);
        return "Updated successfully";
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/regnewuser", consumes = "application/json", produces = "application/json")
    public UserEntity regNewUser(@RequestBody UserEntity userEntity) {
        userService.regNewUser(userEntity);
        return userEntity;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/nickname/{nickname}", method = RequestMethod.GET)
    public UserEntity getUserByNickname(@PathVariable(name = "nickname") String nickname) {
        Optional<UserEntity> userEntity = userService.getUserByNickname(nickname);
        return userEntity.get();
    }
}
