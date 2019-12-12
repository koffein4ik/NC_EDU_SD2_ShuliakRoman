package com.nc.fapi.service;

import com.nc.fapi.model.UserEntity;
import com.nc.fapi.model.UserViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

@Service("customUserDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserEntity findByLogin(String login) {
        RestTemplate restTemplate = new RestTemplate();
        UserEntity userEntity = restTemplate.getForObject("http://localhost:8080/api/users/getuserbynickname/" + login, UserEntity.class);
        return userEntity;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = findByLogin(username);
        System.out.println(userEntity.getPassword());
        if (userEntity == null) {
            throw  new UsernameNotFoundException("Invalid username or password");
        }
        return new org.springframework.security.core.userdetails.User(userEntity.getNickname(), userEntity.getPassword(), getAuthority(userEntity));
    }

    @Override
    public UserViewModel save(UserEntity userEntity) {
        userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));
        RestTemplate restTemplate = new RestTemplate();
        UserViewModel user = restTemplate.postForEntity("http://localhost:8080/api/users/regnewuser",
                userEntity, UserViewModel.class).getBody();
        boolean dirCreated = new File("src/main/resources/userphotos/users/" + user.getId()).mkdirs();
        if (dirCreated) {
            new File("src/main/resources/userphotos/users/" + user.getId() + "/avatar").mkdirs();
            new File("src/main/resources/userphotos/users/" + user.getId() + "/posts").mkdirs();
        }
        return user;
    }

    private Set<SimpleGrantedAuthority> getAuthority(UserEntity userEntity) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + userEntity.getRole()));
        return authorities;
    }
}
