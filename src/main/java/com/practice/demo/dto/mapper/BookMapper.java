package com.practice.demo.dto.mapper;

import com.practice.demo.dto.BookRequestDto;
import com.practice.demo.dto.BookResponseDto;
import com.practice.demo.model.Book;

public class BookMapper {

    public static Book mapModel(BookRequestDto bookRequestDto) {
        return Book.builder()
                .headline(bookRequestDto.getHeadline())
                .text(bookRequestDto.getText())
                .build();
    }

    public static BookResponseDto mapResponseDto(Book book) {
        return BookResponseDto.builder()
                .headline(book.getHeadline())
                .text(book.getText())
                .build();
    }

}
