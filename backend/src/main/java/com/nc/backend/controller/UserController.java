package com.nc.backend.controller;

import com.nc.backend.model.UserEntity;
import com.nc.backend.model.LoginData;
import com.nc.backend.services.UserService;
import javafx.application.Application;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
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

    @PostMapping(value = "regnewuser")
    public UserEntity regNewUser(@RequestBody UserEntity userEntity) {
        return userService.regNewUser(userEntity);
    }

    @PostMapping("updateuserinfo")
    public UserEntity updateUserInfo(@RequestBody UserEntity userEntity) {
        return userService.updateUser(userEntity);
    }

    @RequestMapping(value = "/getuserbynickname/{nickname}", method = RequestMethod.GET)
    public UserEntity getUserByNickname(@PathVariable(name = "nickname") String nickname) {
        return userService.findByNickname(nickname);
    }

    @GetMapping("getallusers/{page}")
    public List<UserEntity> getAllUsers(@PathVariable(name = "page") String page) {
        return this.userService.getAllUsers(Integer.parseInt(page));
    }

    @GetMapping("blockuser/{userid}")
    public void blockUser(@PathVariable(name = "userid") String userId) {
        this.userService.banUser(Integer.parseInt(userId));
    }

    @GetMapping("unblockuser/{userid}")
    public void unblockUser(@PathVariable(name = "userid") String userId) {
        this.userService.unBanUser(Integer.parseInt(userId));
    }
}
