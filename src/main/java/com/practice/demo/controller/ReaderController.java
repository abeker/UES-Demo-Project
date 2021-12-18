package com.practice.demo.controller;

import com.practice.demo.dto.ReaderDto;
import com.practice.demo.service.interfaces.IReaderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public List<ReaderDto> readersByFirstNameLastName(@RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName) {
        return _readerService.readersByFirstNameAndLastName(firstName, lastName);
    }
}
