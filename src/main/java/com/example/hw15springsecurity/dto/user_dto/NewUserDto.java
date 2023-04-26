package com.example.hw15springsecurity.dto.user_dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class NewUserDto {
    private String firstName;
    private String lastName;
    @Email
    private String email;
    @Size(min = 6)
    private String password;
    private String phoneNumber;
}
