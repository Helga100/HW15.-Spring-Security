package com.example.hw15springsecurity.service.user_service;

import com.example.hw15springsecurity.dto.user_dto.LoginDto;
import com.example.hw15springsecurity.dto.user_dto.NewUserDto;
import com.example.hw15springsecurity.dto.user_dto.UserInfoDto;
import com.example.hw15springsecurity.entity.User;
import com.example.hw15springsecurity.repository.UserRepository;
import com.example.hw15springsecurity.repository.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserInfoDto create(NewUserDto newUserDto) {
        User user = modelMapper.map(newUserDto, User.class);
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        return modelMapper.map(userRepository.save(user), UserInfoDto.class);
    }

    @Override
    public List<UserInfoDto> getAll() {
        List<User> users = (List<User>) userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserInfoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserInfoDto getById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User with such Id is not found"));
        return modelMapper.map(user, UserInfoDto.class);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public LoginDto getByEmail(String email) {
        User userFoundByEmail = userRepository.findByEmail(email).orElseThrow(() ->
                new UserNotFoundException("User with such email is not found"));
        return modelMapper.map(userFoundByEmail, LoginDto.class);
    }
}
