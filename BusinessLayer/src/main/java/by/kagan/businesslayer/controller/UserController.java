package by.kagan.businesslayer.controller;

import by.kagan.businesslayer.domain.User;
import by.kagan.businesslayer.exception.UserNotFoundException;
import by.kagan.businesslayer.service.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collection;

@RestController
@RequiredArgsConstructor
@Api("UserApi")
@RequestMapping(value = "/api/user/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public Collection<User> getAllUsers(){
        return userService.loadAllUsers();
    }

    @GetMapping("/users/id")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        try{
            return ResponseEntity.ok(userService.loadUserById(id));
        } catch (UserNotFoundException exception) {
            exception.printStackTrace();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/users/current")
    public User getPrincipal(Principal principal){
        return userService.getUserByEmail(principal.getName());
    }


}
