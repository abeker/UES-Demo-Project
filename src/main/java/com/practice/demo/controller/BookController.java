package com.practice.demo.controller;

import com.practice.demo.dto.*;
import com.practice.demo.service.interfaces.IBookService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final IBookService _bookService;

    public BookController(IBookService bookService) {
        _bookService = bookService;
    }

    @PostMapping
    public void index(@RequestBody BookRequestDto bookDto) {
        _bookService.index(bookDto);
    }

    @GetMapping("/text")
    public List<BookResponseDto> getByText(@RequestBody BookTextRequestDto text) {
        return _bookService.getBooksByText(text.getText());
    }

    @GetMapping("/reindex")
    public void reindex() {
        _bookService.reindex();
    }

    @PostMapping(path = "/pdf", consumes = { "multipart/form-data" })
    public void multiUploadFileModel(@ModelAttribute BookRequestDto uploadModel) throws IOException {
       _bookService.indexUploadedFile(uploadModel);
    }

}
