package com.example.hw15springsecurity.service.book_service;

import com.example.hw15springsecurity.dto.book_dto.BookInfoDto;
import com.example.hw15springsecurity.dto.book_dto.NewBookDto;
import com.example.hw15springsecurity.entity.Book;
import com.example.hw15springsecurity.repository.BookRepository;
import com.example.hw15springsecurity.repository.exception.BookNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    @Override
    public BookInfoDto create(NewBookDto newBookDto) {
        Book book = modelMapper.map(newBookDto, Book.class);
        return modelMapper.map(bookRepository.save(book), BookInfoDto.class);
    }

    @Override
    public List<BookInfoDto> getAllBooks() {
        List<Book> books = (List<Book>) bookRepository.findAll();
        return books.stream()
                .map(book -> modelMapper.map(book, BookInfoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public BookInfoDto getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Such book is not found"));
        return modelMapper.map(book, BookInfoDto.class);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}



