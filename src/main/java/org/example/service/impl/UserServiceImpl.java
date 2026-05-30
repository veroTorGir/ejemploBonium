package org.example.service.impl;

import org.example.entities.UserEntity;
import org.example.repositories.UserRepository;
import org.example.service.UserService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserEntity createUser(UserEntity user) {
        return userRepository.save(user);
    }
    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
    @Override
    public UserEntity getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
