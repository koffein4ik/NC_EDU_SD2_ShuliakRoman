package com.nc.backend.controller;

import com.nc.backend.model.UserEntity;
import com.nc.backend.model.LoginData;
import com.nc.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
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

    @PostMapping(value = "/regnewuser", consumes = "application/json", produces = "application/json")
    public UserEntity regNewUser(@RequestBody UserEntity userEntity) {
        userService.regNewUser(userEntity);
        return userEntity;
    }

    @PostMapping("updateuserinfo")
    public UserEntity updateUserInfo(@RequestBody UserEntity userEntity) {
        return userService.updateUser(userEntity);
    }

    @RequestMapping(value = "/getuserbynickname/{nickname}", method = RequestMethod.GET)
    public UserEntity getUserByNickname(@PathVariable(name = "nickname") String nickname) {
        Optional<UserEntity> userEntity = userService.findByNickname(nickname);
        return userEntity.get();
    }

    @RequestMapping(value = "authorization", method = RequestMethod.POST)
    public Optional authorize(@RequestBody LoginData userLoginData) {
        return userService.authorize(userLoginData.getLogin(), userLoginData.getPassword());
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
