package com.nc.backend.repositories;

import com.nc.backend.model.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, Integer> {
    UserEntity findByNickname(String nickname);
    //String regNewUser(UserEntity userEntity);
}
