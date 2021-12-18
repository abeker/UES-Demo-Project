package com.practice.demo.dto.mapper;

import com.practice.demo.dto.BookRequestDto;
import com.practice.demo.dto.BookResponseDto;
import com.practice.demo.dto.ReaderDto;
import com.practice.demo.model.Book;
import com.practice.demo.model.Reader;
import org.springframework.data.elasticsearch.core.SearchHits;

import java.util.List;

public class BookMapper {

    public static Book mapModel(BookRequestDto bookRequestDto) {
        return Book.builder()
                .title(bookRequestDto.getHeadline())
                .author(bookRequestDto.getAuthor())
                .genres(bookRequestDto.getGenreNames())
                .price(bookRequestDto.getPrice())
                .build();
    }

    public static BookResponseDto mapResponseDto(Book book) {
        return BookResponseDto.builder()
                .headline(book.getTitle())
                .text(book.getText())
                .build();
    }

    public static List<BookResponseDto> mapDtos(SearchHits<Book> searchHits) {
        return searchHits
                .map(book -> mapResponseDto(book.getContent()))
                .toList();
    }
}
