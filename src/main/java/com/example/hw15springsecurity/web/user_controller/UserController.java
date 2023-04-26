package com.example.hw15springsecurity.web.user_controller;

import com.example.hw15springsecurity.dto.user_dto.NewUserDto;
import com.example.hw15springsecurity.dto.user_dto.UserInfoDto;
import com.example.hw15springsecurity.service.user_service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserInfoDto create(@RequestBody @Validated NewUserDto newUserDto) {
        return userService.create(newUserDto);
    }

    @GetMapping
    public List<UserInfoDto> getAll() {
        return userService.getAll();
    }

    @GetMapping("/user/{id}")
    public UserInfoDto getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @DeleteMapping("/user/{id}")
    @Operation(description = "API for delete User by Id")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
