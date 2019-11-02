package com.nc.backend.services;

import com.nc.backend.model.UserEntity;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserService {
    public Iterable<UserEntity> findAll();
    public Optional<UserEntity> findById(Integer id);
    public Optional<UserEntity> getUserByNickname(String nickname);
    public String regNewUser(UserEntity userEntity);
    public void save(UserEntity u);
}
