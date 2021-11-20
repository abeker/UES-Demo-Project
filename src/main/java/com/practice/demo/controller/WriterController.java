package com.practice.demo.controller;

import com.practice.demo.dto.BiographyRequestDto;
import com.practice.demo.dto.WriterRequestDto;
import com.practice.demo.dto.WriterResponseDto;
import com.practice.demo.service.interfaces.IWriterService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @GetMapping("")
    public List<WriterResponseDto> getByFirstAndLastName(@RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName) {
        return _writerService.getWritersByFirstNameAndLastName(firstName, lastName);
    }

    @GetMapping("/books")
    public List<WriterResponseDto> getByMoreThanPublishedBooks(@RequestParam(name = "minBooks") int nrPublishedBooks) {
        return _writerService.getWritersWithMoreThanPublishedBooks(nrPublishedBooks);
    }

    @GetMapping("/date-of-birth")
    public List<WriterResponseDto> getByDateOfBirth(@RequestParam(name = "minDate") String date) {
        return _writerService.getWritersYoungerThan(LocalDate.parse(date));
    }

    @GetMapping("/biography")
    public List<WriterResponseDto> getByBiography(@RequestBody BiographyRequestDto biography) {
        return _writerService.getWritersByBiography(biography.getText());
    }
}
