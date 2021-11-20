package com.practice.demo.service.interfaces;

import com.practice.demo.dto.WriterRequestDto;
import com.practice.demo.dto.WriterResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface IWriterService {

    void index(WriterRequestDto writerDto);

    List<WriterResponseDto> getWritersByFirstName(String firstName);

    List<WriterResponseDto> getWritersByFirstNameAndLastName(String firstName, String lastName);

    List<WriterResponseDto> getWritersWithMoreThanPublishedBooks(int nrOfPublishedBooks);

    List<WriterResponseDto> getWritersYoungerThan(LocalDate date);

    List<WriterResponseDto> getWritersByBiography(String biography);
}
