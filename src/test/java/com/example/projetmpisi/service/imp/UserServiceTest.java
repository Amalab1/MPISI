package com.example.projetmpisi.service.imp;

import com.example.projetmpisi.entity.User;
import com.example.projetmpisi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testSaveUser() {
        User user = new User("john", "john@mail.com");
        Mockito.when(userRepository.save(user)).thenReturn(user);

        User result = userService.saveUser(user);

        assertNotNull(result);
        assertEquals("john", result.getUsername());
    }

    @Test
    void testGetAllUsers() {
        Mockito.when(userRepository.findAll())
                .thenReturn(Arrays.asList(
                        new User("john", "john@mail.com"),
                        new User("alex", "alex@mail.com")
                ));

        var list = userService.getAllUsers();

        assertEquals(2, list.size());
    }

    @Test
    void testGetUserById() {
        User user = new User("john", "john@mail.com");
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user));

        Optional<User> result = userService.getUserById(1);

        assertTrue(result.isPresent());
        assertEquals("john", result.get().getUsername());
    }

    @Test
    void testUpdateUser_whenExists() {
        User user = new User("johnUpdated", "updated@mail.com");

        Mockito.when(userRepository.existsById(1)).thenReturn(true);
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        User result = userService.updateUser(1, user);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("johnUpdated", result.getUsername());
    }

    @Test
    void testUpdateUser_whenNotExists() {
        User user = new User("john", "mail");

        Mockito.when(userRepository.existsById(1)).thenReturn(false);

        User result = userService.updateUser(1, user);

        assertNull(result);
    }

    @Test
    void testDeleteUser() {
        userService.deleteUser(1);

        Mockito.verify(userRepository, Mockito.times(1)).deleteById(1);
    }
}
