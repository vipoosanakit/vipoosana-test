package com.example.bbltest.service;

import com.example.bbltest.dto.UserDTO;
import com.example.bbltest.dto.request.CreateUserRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Long id);
    UserDTO createUser(CreateUserRequest request);
    UserDTO updateUser(Long id, CreateUserRequest request);
    void deleteUser(Long id);
}
