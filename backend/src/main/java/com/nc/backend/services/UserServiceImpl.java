package com.nc.backend.services;

import com.nc.backend.model.UserEntity;
import com.nc.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Iterable<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public String regNewUser(UserEntity userEntity) {
        userRepository.save(userEntity);
        return null;
    }

    @Override
    public Optional<UserEntity> findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<UserEntity> getUserByNickname(String nickname) {
        Optional<UserEntity> userEntity = Optional.of(userRepository.findByNickname(nickname));
        return userEntity;
    }

    @Override
    public void save(UserEntity u) {
        userRepository.save(u);
    }
}
