package com.practice.demo.controller;

import com.practice.demo.dto.WriterDto;
import com.practice.demo.service.interfaces.IWriterService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/writers")
public class WriterController {

    private final IWriterService _writerService;

    public WriterController(IWriterService writerService) {
        _writerService = writerService;
    }

    @PostMapping
    public void index(@RequestBody WriterDto writerDto) {
        _writerService.index(writerDto);
    }
}
