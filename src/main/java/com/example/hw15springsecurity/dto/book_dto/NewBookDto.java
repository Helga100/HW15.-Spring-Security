package com.example.hw15springsecurity.dto.book_dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class NewBookDto {
    @Size(max = 8)
    private String name;
    @Size(min = 4)
    private String author;
}
