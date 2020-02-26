package com.kunyk.cinemaauthserver.service;

import com.kunyk.cinemaauthserver.dto.UserDto;
import com.kunyk.cinemaauthserver.model.User;
import com.kunyk.cinemaauthserver.model.UserRole;
import com.kunyk.cinemaauthserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User registerUser(UserDto userDto) {
        User user = userDto.toModel();

        String hashedPassword = passwordEncoder.encode(userDto.getPassword());
        user.setPassword(hashedPassword);

        user.setRole(UserRole.USER);

        return userRepository.save(user);
    }
}
