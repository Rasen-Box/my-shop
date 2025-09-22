package com.shop.service;


import com.shop.dto.UserRequestDto;
import com.shop.exception.AppException;
import com.shop.mapper.UserMapper;
import com.shop.model.User;
import com.shop.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    public String  registerUser(UserRequestDto userRequestDto) {
        // проверка: есть ли такой username
        if (userRepository.findByUserName(userRequestDto.getUserName()).isPresent()) {
            throw new RuntimeException("Пользователь с таким именем уже существует");
        }

        // хэшируем пароль
        userRequestDto.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));

        // по умолчанию роль USER
        if (userRequestDto.getRole() == null) {
            userRequestDto.setRole("USER");
        }

        userRepository.save(userMapper.toEntity(userRequestDto));

        return "User created";
    }

    public User getUserById(Long userId) {

        return userRepository.findById(userId)
                .orElseThrow(() -> new AppException("Такого пользователя нет", HttpStatus.BAD_REQUEST));
    }
}
