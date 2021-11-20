package com.practice.demo.repository;

import com.practice.demo.model.Book;
import com.practice.demo.model.Writer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBookRepository extends ElasticsearchRepository<Book, String> {

    List<Book> findAllByText(String text);

}
