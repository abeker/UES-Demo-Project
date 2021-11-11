package com.practice.demo.dto.mapper;

import com.practice.demo.dto.WriterDto;
import com.practice.demo.model.Writer;

public class WriterMapper {

    public static WriterDto mapDto(Writer writer) {
        return WriterDto.builder()
                .firstName(writer.getFirstName())
                .lastName(writer.getLastName())
                .dateOfBirth(writer.getDateOfBirth())
                .nrPublishedBooks(writer.getNrPublishedBooks())
                .biography(writer.getBiography())
                .build();
    }

    public static Writer mapModel(WriterDto writerDto) {
        return Writer.builder()
                .firstName(writerDto.getFirstName())
                .lastName(writerDto.getLastName())
                .dateOfBirth(writerDto.getDateOfBirth())
                .nrPublishedBooks(writerDto.getNrPublishedBooks())
                .biography(writerDto.getBiography())
                .build();
    }
}
