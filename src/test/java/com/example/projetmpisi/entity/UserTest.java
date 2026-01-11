package com.example.projetmpisi.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void getId() {
        // Given
        User user = new User();
        user.setId(1);

        // When
        int result = user.getId();

        // Then
        assertEquals(1, result);
    }

    @Test
    void getUsername() {
        // Given
        User user = new User();
        user.setUsername("john_doe");

        // When
        String result = user.getUsername();

        // Then
        assertEquals("john_doe", result);
    }

    @Test
    void getEmail() {
        // Given
        User user = new User();
        user.setEmail("john@example.com");

        // When
        String result = user.getEmail();

        // Then
        assertEquals("john@example.com", result);
    }

    @Test
    void setId() {
        // Given
        User user = new User();

        // When
        user.setId(5);

        // Then
        assertEquals(5, user.getId());
    }

    @Test
    void setUsername() {
        // Given
        User user = new User();

        // When
        user.setUsername("jane_doe");

        // Then
        assertEquals("jane_doe", user.getUsername());
    }

    @Test
    void setEmail() {
        // Given
        User user = new User();

        // When
        user.setEmail("jane@example.com");

        // Then
        assertEquals("jane@example.com", user.getEmail());
    }
}