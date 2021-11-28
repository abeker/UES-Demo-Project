package com.practice.demo.service.implementation;

import com.practice.demo.dto.BookRequestDto;
import com.practice.demo.dto.BookResponseDto;
import com.practice.demo.dto.mapper.BookMapper;
import com.practice.demo.model.Book;
import com.practice.demo.repository.IBookRepository;
import com.practice.demo.service.interfaces.IBookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService implements IBookService {

    private final IBookRepository _bookRepository;

    public BookService(IBookRepository bookRepository) {
        _bookRepository = bookRepository;
    }


    @Override
    public void index(BookRequestDto bookRequestDto) {
        _bookRepository.save(BookMapper.mapModel(bookRequestDto));
    }

    @Override
    public List<BookResponseDto> getBooksByText(String text) {
        List<Book> books = _bookRepository.findAllByText(text);
        return mapBooksToBookResponseDto(books);
    }

    private List<BookResponseDto> mapBooksToBookResponseDto(List<Book> books) {
        List<BookResponseDto> bookDtos = new ArrayList<>();
        for (Book book: books) {
            bookDtos.add(BookMapper.mapResponseDto(book));
        }

        return bookDtos;
    }
}
