package com.practice.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Data
public class WriterResponseDto {

    private String id;

    private String username;

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    private int nrPublishedBooks;

    private String biography;

}