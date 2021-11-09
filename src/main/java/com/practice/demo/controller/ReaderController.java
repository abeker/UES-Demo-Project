package com.practice.demo.controller;

import com.practice.demo.dto.ReaderDto;
import com.practice.demo.service.IReaderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/readers")
public class ReaderController {

    private final IReaderService _readerService;

    public ReaderController(IReaderService readerService) {
        _readerService = readerService;
    }

    @PostMapping
    public void index(@RequestBody ReaderDto readerDto) {
        _readerService.index(readerDto);
    }
}
