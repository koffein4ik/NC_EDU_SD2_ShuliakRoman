package com.nc.backend.services;

import com.nc.backend.model.UserEntity;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserService {
    Iterable<UserEntity> findAll();

    Optional<UserEntity> findById(Integer id);

    Optional<UserEntity> authorize(String nickname, String password);

    String regNewUser(UserEntity userEntity);

    void save(UserEntity u);

    Optional<UserEntity> findByNickname(String nickname);

    void banUser(Integer userId);

    void unBanUser(Integer userId);

    UserEntity updateUser(UserEntity userEntity);

}
