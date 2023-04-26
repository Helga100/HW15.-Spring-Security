package com.example.hw15springsecurity.web.book_controller;

import com.example.hw15springsecurity.dto.book_dto.BookInfoDto;
import com.example.hw15springsecurity.dto.book_dto.NewBookDto;
import com.example.hw15springsecurity.repository.exception.BookNotFoundException;
import com.example.hw15springsecurity.service.book_service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookInfoDto> createBook(@RequestBody @Validated NewBookDto newBookDto) {
        if (newBookDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(bookService.create(newBookDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BookInfoDto>> getBooks() {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getBookById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(bookService.getBookById(id), HttpStatus.OK);
        } catch (BookNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    @Operation(description = "API for delete Book by Id")
    public ResponseEntity<BookInfoDto> delete(@PathVariable("id") Long id) {
        try {
            bookService.deleteBook(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (BookNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
