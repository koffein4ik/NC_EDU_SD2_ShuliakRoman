package com.nc.backend.services;

import com.nc.backend.model.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Iterable<UserEntity> findAll();

    Optional<UserEntity> findById(Integer id);

    Optional<UserEntity> authorize(String nickname, String password);

    UserEntity regNewUser(UserEntity userEntity);

    List<UserEntity> getAllUsers(Integer page);

    void save(UserEntity u);

    UserEntity findByNickname(String nickname);

    void banUser(Integer userId);

    void unBanUser(Integer userId);

    UserEntity updateUser(UserEntity userEntity);

}
