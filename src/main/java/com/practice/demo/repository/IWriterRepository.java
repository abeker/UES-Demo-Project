package com.practice.demo.repository;

import com.practice.demo.dto.WriterResponseDto;
import com.practice.demo.model.Writer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IWriterRepository extends ElasticsearchRepository<Writer, UUID> {

//    @Query("{"bool" : {"must" : {"field" : {"firstName" : "?0"}}}}")
    List<Writer> findAllByFirstName(String firstName);

    List<Writer> findAllByFirstNameAndLastName(String firstName, String lastName);

    List<Writer> findByNrPublishedBooksGreaterThan(int nrOfPublishedBooks);
}

