package com.practice.demo.repository;

import com.practice.demo.model.Writer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IWriterRepository extends ElasticsearchRepository<Writer, UUID> {

    

}
