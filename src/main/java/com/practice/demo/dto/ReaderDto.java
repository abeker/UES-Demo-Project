package com.practice.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReaderDto {

    private String firstName;

    private String lastName;

    private String address;
}
