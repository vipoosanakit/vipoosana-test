package com.example.bbltest.service;

import com.example.bbltest.dto.UserDTO;
import com.example.bbltest.dto.request.CreateUserRequest;
import com.example.bbltest.exception.UserNotFoundException;
import com.example.bbltest.model.User;
import com.example.bbltest.repository.UserRepository;
import com.example.bbltest.service.impl.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    @DisplayName("Should create user successfully")
    void testCreateUserSuccess() {
        CreateUserRequest request = new CreateUserRequest();
        request.setName("test");
        request.setUsername("test_username");
        request.setEmail("test@email.com");
        request.setWebsite("http://test.com");
        request.setPhone("081-111-1111");

        User user = new User();
        user.setId(1L);
        user.setName("test");
        user.setUsername("test_username");
        user.setEmail("test@email.com");
        user.setWebsite("http://test.com");
        user.setPhone("081-111-1111");

        when(userRepository.save(any(User.class))).thenReturn(user);

        UserDTO result = userService.createUser(request);

        assertNotNull(result);
    }

    @Test
    @DisplayName("Should throw error when update not found")
    void testCreateUserFailInvalidEmail() {
        CreateUserRequest request = new CreateUserRequest();
        request.setName("test");
        request.setUsername("test_username");
        request.setEmail("testcom");
        request.setWebsite("http://test.com");
        request.setPhone("081-111-1111");

        User user = new User();
        user.setId(1L);
        user.setName("test");
        user.setUsername("test_username");
        user.setEmail("testcom");
        user.setWebsite("http://test.com");
        user.setPhone("081-111-1111");

        doReturn(Optional.empty()).when(userRepository).findById(1L);

        assertThrows(UserNotFoundException.class, () -> userService.updateUser(1L, request));
    }
}
