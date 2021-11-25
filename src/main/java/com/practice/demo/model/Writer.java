package com.practice.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Document(indexName = "writers")
@Setting(settingPath = "/analyzers/serbianAnalyzer.json")
public class Writer {

    @Id
    private String id;

    @Field(type = FieldType.Keyword)
    private String username;

    @Field(type = FieldType.Keyword)
    private String firstName;

    @Field(type = FieldType.Keyword)
    private String lastName;

    @Field(type = FieldType.Date, format = DateFormat.date)
    private LocalDate dateOfBirth;

    @Field(type = FieldType.Integer)
    private int nrPublishedBooks;

    @Field(type = FieldType.Text)
    private String biography;
}

