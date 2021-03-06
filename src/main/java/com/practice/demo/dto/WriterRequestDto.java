package com.practice.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class WriterRequestDto {

    private String username;

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    private int nrPublishedBooks;

    private String biography;
}
