package com.nc.backend.services;

import com.nc.backend.model.UserEntity;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserService {
    public Iterable<UserEntity> findAll();

    public Optional<UserEntity> findById(Integer id);

    public Optional<UserEntity> authorize(String nickname, String password);

    public String regNewUser(UserEntity userEntity);

    public void save(UserEntity u);

    Optional<UserEntity> findByNickname(String nickname);

    //Optional<UserEntity> findByNicknameAndPassword(String nickname, String password);
}
