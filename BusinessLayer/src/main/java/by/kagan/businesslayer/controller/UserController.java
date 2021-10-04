package by.kagan.businesslayer.controller;

import by.kagan.businesslayer.domain.User;
import by.kagan.businesslayer.dto.request.UserRequest;
import by.kagan.businesslayer.dto.response.UserDto;

import by.kagan.businesslayer.mapper.UserRequestToUserMapper;
import by.kagan.businesslayer.mapper.UserToUserDtoMapper;
import by.kagan.businesslayer.service.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags = "Information about users")
@RequestMapping(value = "/api/user/users")
public class UserController {
    private final UserService userService;

    private final UserToUserDtoMapper toUserDtoMapper;

    private final UserRequestToUserMapper toUserMapper;

    @GetMapping
    public List<UserDto> getAllUsers(){
        return userService
                .loadAllUsers()
                .stream()
                .collect(ArrayList::new,
                         (list, user)->list.add(toUserDtoMapper.map(user)),
                         ArrayList::addAll);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
            return ResponseEntity.ok(userService.loadUserById(id));
    }

    @GetMapping("/current")
    public UserDto getPrincipal(Principal principal){
        return toUserDtoMapper.map(userService.getUserByEmail(principal.getName()));
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<UserDto> update(@PathVariable Long id, UserRequest request){
        return new ResponseEntity<>(toUserDtoMapper.map(userService.update(id, toUserMapper.map(request))), HttpStatus.CREATED);
    }
}
