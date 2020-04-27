package com.nc.fapi.service;

import com.nc.fapi.model.UserEntity;
import com.nc.fapi.model.UserViewModel;
import org.springframework.http.ResponseEntity;

public interface UserService {
    UserEntity findByLogin(String login);
    UserViewModel getUserViewModelByNickname(String nickname);
    byte[] getUserAvatar(Integer userId);
    UserViewModel save(UserEntity userEntity);
}
