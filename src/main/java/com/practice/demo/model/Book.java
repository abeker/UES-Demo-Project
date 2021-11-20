package com.practice.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Document(indexName = "books")
@Setting(settingPath = "/analyzers/serbianAnalyzer.json")
public class Book {

    @Id
    private String id;

    @Field(type = FieldType.Keyword)
    private String headline;

    @Field(type = FieldType.Text)
    private String text;

}
