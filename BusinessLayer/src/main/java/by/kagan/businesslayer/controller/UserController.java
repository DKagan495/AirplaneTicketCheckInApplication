package by.kagan.businesslayer.controller;

import by.kagan.businesslayer.domain.User;
import by.kagan.businesslayer.dto.response.UserDto;

import by.kagan.businesslayer.mapper.UserToUserDtoMapper;
import by.kagan.businesslayer.service.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags = "Information about users")
@RequestMapping(value = "/api/user/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserDto> getAllUsers(){
        return userService.loadAllUsers().stream().collect(ArrayList::new, (list, user)->list.add(UserToUserDtoMapper.mapToResponse(user)), ArrayList::addAll);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
            return ResponseEntity.ok(userService.loadUserById(id));
    }

    @GetMapping("/current")
    public UserDto getPrincipal(Principal principal){
        return UserToUserDtoMapper.mapToResponse(userService.getUserByEmail(principal.getName()));
    }
}
