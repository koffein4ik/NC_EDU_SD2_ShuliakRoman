package com.nc.backend.services;

import com.nc.backend.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public Iterable<User> findAll();
    public Optional<User> findById(Integer id);
    public void save(User u);
}
