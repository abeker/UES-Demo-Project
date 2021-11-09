package com.practice.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Getter
@Setter
@AllArgsConstructor
@Document(indexName = "readers")
public class Reader {

    @Id
    private String id;

    private String firstName;

    private String lastName;

    private String address;
}
