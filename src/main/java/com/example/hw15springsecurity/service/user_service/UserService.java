package com.example.hw15springsecurity.service.user_service;

import com.example.hw15springsecurity.dto.user_dto.LoginDto;
import com.example.hw15springsecurity.dto.user_dto.NewUserDto;
import com.example.hw15springsecurity.dto.user_dto.UserInfoDto;

import java.util.List;

public interface UserService {

    UserInfoDto create(NewUserDto newUserDto);

    List<UserInfoDto> getAll();

    UserInfoDto getById(Long id);

    void delete(Long id);

    LoginDto getByEmail(String email);
}
