package org.example.service;

import org.example.entities.UserEntity;
import java.util.List;

public interface UserService {
    UserEntity createUser(UserEntity user);
    List<UserEntity> getAllUsers();
    UserEntity getUserById(Long id);
    void deleteUser(Long id);
}
