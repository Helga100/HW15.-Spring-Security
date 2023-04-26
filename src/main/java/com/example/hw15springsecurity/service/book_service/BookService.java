package com.example.hw15springsecurity.service.book_service;

import com.example.hw15springsecurity.dto.book_dto.BookInfoDto;
import com.example.hw15springsecurity.dto.book_dto.NewBookDto;

import java.util.List;

public interface BookService {

    BookInfoDto create(NewBookDto newBookDto);

    List<BookInfoDto> getAllBooks();

    BookInfoDto getBookById(Long id);

    void deleteBook(Long id);
}

