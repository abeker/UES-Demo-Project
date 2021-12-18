package com.practice.demo.dto.mapper;

import com.practice.demo.dto.ReaderDto;
import com.practice.demo.model.Reader;
import org.springframework.data.elasticsearch.core.SearchHits;

import java.util.List;

public class ReaderMapper {

    public static List<ReaderDto> mapDtos(SearchHits<Reader> searchHits) {
        return searchHits
                .map(reader -> mapDto(reader.getContent()))
                .toList();
    }

    public static ReaderDto mapDto(Reader reader) {
        return ReaderDto.builder()
                .firstName(reader.getFirstName())
                .lastName(reader.getLastName())
                .address(reader.getAddress())
                .build();
    }
}
