package com.practice.demo.repository;

import com.practice.demo.model.Writer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IWriterRepository extends ElasticsearchRepository<Writer, String> {

    Writer findOneByUsername(String username);

    List<Writer> findAllByFirstName(String firstName);

    List<Writer> findAllByFirstNameAndLastName(String firstName, String lastName);

    List<Writer> findByNrPublishedBooksGreaterThan(int nrOfPublishedBooks);

    List<Writer> findByDateOfBirthGreaterThan(LocalDate date);

    List<Writer> findAllByBiography(String firstName);
}

