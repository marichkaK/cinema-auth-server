package com.kunyk.cinemaauthserver.auth;

import com.kunyk.cinemaauthserver.dto.UserDto;
import com.kunyk.cinemaauthserver.model.User;
import com.kunyk.cinemaauthserver.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/register")
public class RegistrationRestController {

    private final UserService userService;

    public RegistrationRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> register(@RequestBody UserDto userDto) {
        User user = userService.registerUser(userDto);

        return ResponseEntity.ok(user.toDto());
    }
}
