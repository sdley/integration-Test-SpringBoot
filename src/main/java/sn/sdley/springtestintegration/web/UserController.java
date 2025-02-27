package sn.sdley.springtestintegration.web;

import org.springframework.web.bind.annotation.*;
import sn.sdley.springtestintegration.model.User;
import sn.sdley.springtestintegration.service.UserService;

import java.util.List;

@RestController
@RequestMapping("test/integrations/v1")
public class UserController {
    private final UserService userService;

    // Injection de dependance via le constructeur
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/users")
    public void createUser(@RequestBody User user){
        userService.createUser(user);
    }

    @PutMapping("/users")
    public void updateUser(@RequestBody User user){
        userService.updateUser(user);
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id){
        return userService.getUserById(id).orElse(null);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

}
