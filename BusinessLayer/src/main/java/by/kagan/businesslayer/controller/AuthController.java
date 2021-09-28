package by.kagan.businesslayer.controller;

import by.kagan.businesslayer.auth.token.jwt.JwtAuthProvider;
import by.kagan.businesslayer.dto.AuthReponseTransferObject;
import by.kagan.businesslayer.dto.AuthRequestTransferObject;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
<<<<<<< HEAD
@RequestMapping(value = "/api/auth")
@Api(tags = "Authentication and Authorization")
public class AuthController {

    private final JwtAuthProvider authProvider;
=======
@Api(tags = "Auth")
//TODO: базовый url-префикс контроллера?
public class AuthController {
//    TODO: модификаторы доступа?
    final AccountAuthorizationService authorizationService;

    final UserServiceImpl userService;

    final JwtAuthProvider authProvider;
>>>>>>> db5e34a213d4d01fb0dfa01266314437ff3d106c

    private final AuthenticationManager authenticationManager;


    @PostMapping
    public ResponseEntity<AuthReponseTransferObject> tryToAuthUser(@RequestBody AuthRequestTransferObject authRequestDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDto.getEmail(),
                authRequestDto.getPassword()));

<<<<<<< HEAD
=======
    @GetMapping(value = "/test")
    public String hello(Principal principal){
        System.out.println(principal.getName());
        return authorizationService.loadUserByUsername(principal.getName()).toString();
    }

//    TODO: consumes = MediaType.APPLICATION_JSON_VALUE - зачем?
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    //    TODO: authRequestDto -> authRequest || request. Зачем throws?
    //    TODO: валидировать входные данные через javax.validation
    public ResponseEntity<AuthReponseTransferObject> tryToAuthUser(@RequestBody AuthRequestTransferObject authRequestDto) throws UserNotFoundException {
//        TODO: сервисная логика в контроллере. В общем случае контроллер должен выглядеть как return ResponseEntity.ok(sthService.sthMethod());
//         исключения  - валидация(нежелательно)/конвертация входных данных или конвертация данных респонса
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDto.getEmail(), authRequestDto.getPassword()));
        } catch (BadCredentialsException exception) {
            exception.printStackTrace();
        }
>>>>>>> db5e34a213d4d01fb0dfa01266314437ff3d106c
        String token = authProvider.getToken(authRequestDto.getEmail());
        return ResponseEntity.ok(new AuthReponseTransferObject(token));
    }

<<<<<<< HEAD

=======
    @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> registerUser(@RequestBody UserDto userDto, final HttpServletRequest request) {
        //        TODO: сервисная логика в контроллере.
        try{
            User user = userService.saveUserDto(userDto);
            user.setId(userService.loadUserByEmail(user.getEmail()).getId());
            String appUrl = request.getContextPath();
            eventPublisher.publishEvent(new AfterCompleteRegistrationEvent(user, request.getLocale(), appUrl));
        } catch (PasswordsNotMatchesException exception) {
            exception.printStackTrace();
//            TODO: return ResponseEntity.badRequest()
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }
//        TODO: ResponseEntity.ok(); || ResponseEntity.status(HttpStatus.OK);
        return ResponseEntity.ok(HttpStatus.OK);
    }

//    TODO: нечитаемый урл запроса
    @GetMapping(value = "/signupconfirmation", produces = MediaType.APPLICATION_JSON_VALUE)
//    TODO: зачем атрибут в RequestParam?
    public ResponseEntity<HttpStatus> confirmAccount(@RequestParam("token") String token){
        VerificationToken verificationToken;
        try{
           verificationToken = userService.loadVerificationToken(token);
        } catch (VerificationTokenNotFoundException | VerificationTokenExpiredException exception) {
            exception.printStackTrace();
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
        User user = verificationToken.getUser();
        user.setAccountEnabled(true);
        UserDto toUpdateEnabledStatusUserDto = map(user);
        userService.updateUser(user.getId(), toUpdateEnabledStatusUserDto);
        return ResponseEntity.accepted().body(HttpStatus.ACCEPTED);
    }
>>>>>>> db5e34a213d4d01fb0dfa01266314437ff3d106c
}
