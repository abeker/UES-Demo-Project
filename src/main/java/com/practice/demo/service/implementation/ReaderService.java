package com.practice.demo.service.implementation;

import com.practice.demo.dto.ReaderDto;
import com.practice.demo.dto.SimpleQueryEs;
import com.practice.demo.dto.mapper.ReaderMapper;
import com.practice.demo.model.Reader;
import com.practice.demo.repository.IReaderRepository;
import com.practice.demo.service.interfaces.IReaderService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReaderService implements IReaderService {

    private final IReaderRepository _readerRepository;
    private final ElasticsearchRestTemplate _elasticsearchRestTemplate;

    public ReaderService(IReaderRepository readerRepository, ElasticsearchRestTemplate elasticsearchRestTemplate) {
        _readerRepository = readerRepository;
        _elasticsearchRestTemplate = elasticsearchRestTemplate;
    }

    @Override
    public void index(ReaderDto readerDto) {
        _readerRepository.save(
            new Reader(
                    "123",
                    readerDto.getFirstName(),
                    readerDto.getLastName(),
                    readerDto.getAddress()
            )
        );
    }

    @Override
    public List<ReaderDto> readersByFirstNameLastName(String firstName, String lastName) {
        QueryBuilder firstNameQuery = SearchQueryGenerator.createMatchQueryBuilder(new SimpleQueryEs("firstName", firstName));
        QueryBuilder lastNameQuery = SearchQueryGenerator.createMatchQueryBuilder(new SimpleQueryEs("lastName", lastName));

        BoolQueryBuilder boolQueryFirstNameAndLastName = QueryBuilders
                .boolQuery()
                .must(firstNameQuery)
                .must(lastNameQuery);

        return ReaderMapper.mapDtos(searchByBoolQuery(boolQueryFirstNameAndLastName));
    }

    private SearchHits<Reader> searchByBoolQuery(BoolQueryBuilder boolQueryBuilder) {
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(boolQueryBuilder)
                .build();

        return _elasticsearchRestTemplate.search(searchQuery, Reader.class,  IndexCoordinates.of("readers"));
    }
}
