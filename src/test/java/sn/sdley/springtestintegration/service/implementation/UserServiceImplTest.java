package sn.sdley.springtestintegration.service.implementation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import sn.sdley.springtestintegration.model.User;
import sn.sdley.springtestintegration.repository.UserRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllUsers() {
        // static data
        List<User> users = List.of(
                new User(1L, "Fatou", "DIOP", 19, true),
                new User(2L, "Mamadou", "DIAGNE", 20, true),
                new User(3L, "Aminata", "FALL", 21, true)
        );

        // mock the findAll method
        when(userRepository.findAll()).thenReturn(users);

        // call the method to test
        List<User> allUsers = userService.getAllUsers();

        // check the result
        Assert.notEmpty(allUsers, "The list of users is not empty");
        Assert.isTrue(allUsers.size() == 3, "The list of users contains 3 elements");
        Assert.isTrue(allUsers.contains(users.get(0)), "The list of users contains the first user");
        Assert.isTrue(allUsers.get(0).getNom().equals("DIOP"), "The first user is DIOP");
    }

    @Test
    void getUserById() {
    }

    @Test
    void createUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }
}