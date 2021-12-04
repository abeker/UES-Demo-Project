package com.practice.demo.service.interfaces;

import com.practice.demo.dto.ReaderDto;

import java.util.List;

public interface IReaderService {

    void index(ReaderDto readerDto);

    List<ReaderDto> readersByFirstNameLastName(String firstName, String lastName);
}
