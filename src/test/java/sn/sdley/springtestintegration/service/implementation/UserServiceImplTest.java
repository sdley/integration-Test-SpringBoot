package sn.sdley.springtestintegration.service.implementation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;
import sn.sdley.springtestintegration.model.User;
import sn.sdley.springtestintegration.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User(1L, "Fatou", "DIOP", 19, true);
    }

    @AfterEach
    void tearDown() {
        user = null;
    }

    @Test
    @DisplayName("Test de la méthode getAllUsers")
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
        Long userId = 1L;

        // Simuler le comportement du repository
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Appeler la méthode de service
        Optional<User> foundUser = userService.getUserById(userId);

        // Vérifications
        assertTrue(foundUser.isPresent()); // Vérifie qu'un utilisateur est bien retourné
        assertEquals(userId, foundUser.get().getId()); // Vérifie que l'ID correspond
        assertEquals("Fatou", foundUser.get().getPrenom()); // Vérifie le prénom
        assertEquals("DIOP", foundUser.get().getNom()); // Vérifie le nom

        // Vérifier que findById() a été appelé exactement une fois avec l'ID donné
        verify(userRepository, times(1)).findById(userId);
    }


    @Test
    void  shouldCallCreateUser(){
        // doNothing utilisé pour les methodes void

        // test de la creation cote repository
        when(userRepository.save(user)).thenReturn(user);

        // simule la creation
        userService.createUser(user);

        // verification de l'appel save du reposity
        verify(userRepository, times(1)).save(user);

    }

    @Test
    void updateUser() {
        //doNothing().when(userRepository).save(user);

        // test de la creation cote repository
        when(userRepository.save(user)).thenReturn(user);

        userService.updateUser(user);

        verify(userRepository, times(1)).save(user);
    }

    @Test
    void deleteUser() {
        Long userId = 1L;

        doNothing().when(userRepository).deleteById(userId);

        userService.deleteUser(userId);

        verify(userRepository, times(1)).deleteById(userId);
    }
}
