package com.nc.backend.services;

import com.nc.backend.model.UserEntity;
import com.nc.backend.model.UserStatus;
import com.nc.backend.repositories.HashtagRepository;
import com.nc.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public UserEntity regNewUser(UserEntity userEntity) {
        if (!this.userRepository.findByNickname(userEntity.getNickname()).isPresent() &&
                !this.userRepository.findByEmail(userEntity.getEmail()).isPresent()) {
            return userRepository.save(userEntity);
        }
        return new UserEntity();
    }

    @Override
    public List<UserEntity> getAllUsers(Integer page) {
        int size = 20;
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findByRole("User", pageable);
    }

    @Override
    public Optional<UserEntity> findById(Integer id) {
        return userRepository.findById(id);
    }

//    @Override
//    public Optional<UserEntity> findByNicknameAndPassword(String nickname, String password) {
//        return userRepository.findByNicknameAndPassword(nickname, password);
//    }

    @Override
    public Optional<UserEntity> authorize(String nickname, String password) {
        return userRepository.findByNicknameAndPassword(nickname, password);
    }

    @Override
    public void save(UserEntity u) {
        userRepository.save(u);
    }

    @Override
    public UserEntity findByNickname(String nickname) {
        Optional<UserEntity> userEntity = userRepository.findByNickname(nickname);
        if (userEntity.isPresent()) {
            return userEntity.get();
        }
        return new UserEntity();
    }

    @Override
    public void banUser(Integer userId) {
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        if (userEntity.isPresent()) {
            UserEntity user = userEntity.get();
            user.setStatus(UserStatus.banned);
            userRepository.save(user);
        }
    }

    @Override
    public void unBanUser(Integer userId) {
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        if (userEntity.isPresent()) {
            UserEntity user = userEntity.get();
            user.setStatus(UserStatus.active);
            userRepository.save(user);
        }
    }

    @Override
    public UserEntity updateUser(UserEntity userEntity) {
        userRepository.save(userEntity);
        Optional<UserEntity> user = userRepository.findById(userEntity.getId());
        if (user.isPresent()) {
            return user.get();
        } else {
            return new UserEntity();
        }
    }
}
