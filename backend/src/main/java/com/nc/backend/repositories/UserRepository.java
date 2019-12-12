package com.nc.backend.repositories;

import com.nc.backend.model.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, Integer> {
//    @Query(value = "From UserEntity usr where usr.nickname = :nickname and usr.password = :password")
//    Optional<UserEntity> authorize(@Param("nickname") String nickname,
//                                   @Param("password") String password);
//
//    @Query(value = "From UserEntity usr where usr.nickname = :nickname")
//    Optional<UserEntity> findByNickname(@Param("nickname") String nickname);
    Optional<UserEntity> findByNickname(String nickname);
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByNicknameAndPassword(String nickname, String password);
    Optional<UserEntity> findById(Integer id);
    List<UserEntity> findByRole(String role, Pageable page);
//    Optional<UserEntity> findByNicknameAndPassword(String nickname, String password);
    //String regNewUser(UserEntity userEntity);
}
