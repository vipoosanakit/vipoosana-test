package com.example.bbltest.service.impl;

import com.example.bbltest.dto.UserDTO;
import com.example.bbltest.dto.request.CreateUserRequest;
import com.example.bbltest.exception.UserNotFoundException;
import com.example.bbltest.model.User;
import com.example.bbltest.repository.UserRepository;
import com.example.bbltest.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> getAllUsers() {

        return userRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id).map(this::toDTO).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public UserDTO createUser(CreateUserRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setWebsite(request.getWebsite());

        user = userRepository.save(user);
        return toDTO(user);
    }

    @Override
    public UserDTO updateUser(Long id, CreateUserRequest request) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        user.setUsername(request.getUsername());
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setWebsite(request.getWebsite());

        user = userRepository.save(user);

        return toDTO(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        userRepository.deleteById(id);
    }

    private UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setWebsite(user.getWebsite());
        return dto;
    }
}
