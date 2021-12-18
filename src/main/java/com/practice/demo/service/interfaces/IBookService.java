package com.practice.demo.service.interfaces;

import com.practice.demo.dto.BookRequestDto;
import com.practice.demo.dto.BookResponseDto;
import com.practice.demo.dto.ReaderDto;
import com.practice.demo.dto.WriterResponseDto;
import com.practice.demo.model.Book;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface IBookService {

    void index(BookRequestDto bookRequestDto);

    void index(Book book);

    List<BookResponseDto> findByText(String text);

    void reindex();

    int indexUnitFromFile(File file);

    void indexUploadedFile(BookRequestDto bookRequestDto) throws IOException;

    File getResourceFilePath(String path);

    List<BookResponseDto> findByPrice(double from, double to);

}
