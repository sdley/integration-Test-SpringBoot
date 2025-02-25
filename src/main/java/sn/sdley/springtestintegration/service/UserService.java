package sn.sdley.springtestintegration.service;

import sn.sdley.springtestintegration.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    void createUser(User user);

    void updateUser(User user);

    void deleteUser(Long id);
}
