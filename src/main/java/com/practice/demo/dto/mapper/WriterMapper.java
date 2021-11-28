package com.practice.demo.dto.mapper;

import com.practice.demo.dto.WriterRequestDto;
import com.practice.demo.dto.WriterResponseDto;
import com.practice.demo.model.Writer;

public class WriterMapper {

    public static Writer mapModel(WriterRequestDto writerRequestDto) {
        return Writer.builder()
                .username(writerRequestDto.getUsername())
                .firstName(writerRequestDto.getFirstName())
                .lastName(writerRequestDto.getLastName())
                .dateOfBirth(writerRequestDto.getDateOfBirth())
                .nrPublishedBooks(writerRequestDto.getNrPublishedBooks())
                .biography(writerRequestDto.getBiography())
                .build();
    }

    public static WriterRequestDto mapRequestDto(Writer writer) {
        return WriterRequestDto.builder()
                .username(writer.getUsername())
                .firstName(writer.getFirstName())
                .lastName(writer.getLastName())
                .dateOfBirth(writer.getDateOfBirth())
                .nrPublishedBooks(writer.getNrPublishedBooks())
                .biography(writer.getBiography())
                .build();
    }

    public static WriterResponseDto mapResponseDto(Writer writer) {
        return WriterResponseDto.builder()
                .id(writer.getId())
                .username(writer.getUsername())
                .firstName(writer.getFirstName())
                .lastName(writer.getLastName())
                .dateOfBirth(writer.getDateOfBirth())
                .nrPublishedBooks(writer.getNrPublishedBooks())
                .biography(writer.getBiography())
                .build();
    }
}
