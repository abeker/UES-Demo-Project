package com.practice.demo.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BookResponseDto {

    private String id;

    private String headline;

    private String text;

}
