package com.practice.demo.service.interfaces;

import com.practice.demo.dto.BookRequestDto;
import com.practice.demo.dto.BookResponseDto;
import com.practice.demo.dto.WriterResponseDto;

import java.util.List;

public interface IBookService {

    void index(BookRequestDto bookRequestDto);

    List<BookResponseDto> getBooksByText(String text);

}
