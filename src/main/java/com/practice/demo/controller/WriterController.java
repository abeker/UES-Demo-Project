package com.practice.demo.controller;

import com.practice.demo.dto.WriterRequestDto;
import com.practice.demo.dto.WriterResponseDto;
import com.practice.demo.service.interfaces.IWriterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/writers")
public class WriterController {

    private final IWriterService _writerService;

    public WriterController(IWriterService writerService) {
        _writerService = writerService;
    }

    @PostMapping
    public void index(@RequestBody WriterRequestDto writerDto) {
        _writerService.index(writerDto);
    }

    @GetMapping("/{firstName}")
    public List<WriterResponseDto> getByFirstName(@PathVariable String firstName) {
        return _writerService.getWritersByFirstName(firstName);
    }

    @GetMapping("/{firstName}/{lastName}")
    public List<WriterResponseDto> getByFirstAndLastName(@PathVariable String firstName, @PathVariable String lastName) {
        return _writerService.getWritersByFirstNameAndLastName(firstName, lastName);
    }

    @GetMapping("/books/{nrPublishedBooks}")
    public List<WriterResponseDto> getByMoreThanPublishedBooks(@PathVariable int nrPublishedBooks) {
        return _writerService.getWritersWithMoreThanPublishedBooks(nrPublishedBooks);
    }
}
