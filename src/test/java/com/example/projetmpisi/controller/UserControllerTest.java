package com.example.projetmpisi.controller;

import com.example.projetmpisi.entity.User;
import com.example.projetmpisi.service.IUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private IUserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    void createUser() {
        // Given
        User user = new User();
        user.setId(1);
        when(userService.saveUser(any(User.class))).thenReturn(user);

        // When
        ResponseEntity<User> response = userController.createUser(user);

        // Then
        assertNotNull(response);
        assertEquals(201, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getId());

        // Vérifie juste que saveUser a été appelé une fois
        verify(userService, times(1)).saveUser(any(User.class));
    }

    @Test
    void getAllUsers() {
        // Given
        User user = new User();
        user.setId(1);  // int
        List<User> users = Arrays.asList(user);
        when(userService.getAllUsers()).thenReturn(users);

        // When
        ResponseEntity<List<User>> response = userController.getAllUsers();

        // Then
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void getUserById() {
        // Given
        User user = new User();
        user.setId(1);  // int
        when(userService.getUserById(1)).thenReturn(Optional.of(user));

        // When
        ResponseEntity<User> response = userController.getUserById(1);

        // Then
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getId());  // int
        verify(userService, times(1)).getUserById(1);
    }

    @Test
    void updateUser() {
        // Given
        User user = new User();
        user.setId(1);  // int
        User updatedUser = new User();
        updatedUser.setId(1);  // int
        when(userService.updateUser(eq(1), any(User.class))).thenReturn(updatedUser);

        // When
        ResponseEntity<User> response = userController.updateUser(1, user);

        // Then
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getId());  // int
        verify(userService, times(1)).updateUser(1, user);
    }

    @Test
    void deleteUser() {
        // Given
        doNothing().when(userService).deleteUser(1);

        // When
        ResponseEntity<Void> response = userController.deleteUser(1);

        // Then
        assertNotNull(response);
        assertEquals(204, response.getStatusCodeValue());
        assertNull(response.getBody());
        verify(userService, times(1)).deleteUser(1);
    }

    @Test
    void getAllUsers_WhenEmptyList_ShouldReturnEmptyList() {
        // Given
        when(userService.getAllUsers()).thenReturn(Collections.emptyList());

        // When
        ResponseEntity<List<User>> response = userController.getAllUsers();

        // Then
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isEmpty());
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void createUser_WithNullUser_ShouldHandleGracefully() {
        // Given
        when(userService.saveUser(null)).thenReturn(null);

        // When
        ResponseEntity<User> response = userController.createUser(null);

        // Then
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNull(response.getBody());
        verify(userService, times(1)).saveUser(null);
    }
    // Test pour le cas où getUserById ne trouve pas l'utilisateur
    @Test
    void getUserById_NotFound() {
        // Given
        when(userService.getUserById(99)).thenReturn(Optional.empty());

        // When
        ResponseEntity<User> response = userController.getUserById(99);

        // Then
        assertNotNull(response);
        assertEquals(404, response.getStatusCodeValue()); // Vérifie le statut 404
        assertNull(response.getBody());
    }

    // Test pour updateUser qui retourne null
    @Test
    void updateUser_NotFound() {
        // Given
        User user = new User();
        user.setId(1);
        when(userService.updateUser(eq(99), any(User.class))).thenReturn(null);

        // When
        ResponseEntity<User> response = userController.updateUser(99, user);

        // Then
        assertNotNull(response);
        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }
}