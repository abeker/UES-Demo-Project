package com.practice.demo.repository;

import com.practice.demo.model.Reader;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReaderRepository extends ElasticsearchRepository<Reader, String> {
}
