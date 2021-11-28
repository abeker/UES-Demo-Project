package com.practice.demo.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class BookRequestDto {

    private String headline;

    private String author;

    private double price;

    private List<String> genreNames;

    private MultipartFile[] files;
}
