package com.nc.fapi.service;

import com.nc.fapi.model.UserEntity;
import com.nc.fapi.model.UserViewModel;

public interface UserService {
    UserEntity findByLogin(String login);
    UserViewModel save(UserEntity userEntity);
}
