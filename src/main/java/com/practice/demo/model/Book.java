package com.practice.demo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(indexName = "books")
@Setting(settingPath = "/analyzers/serbianAnalyzer.json")
public class Book {

    @Id
    private String id;

    @Field(type = FieldType.Keyword)
    private String title;

    @Field(type = FieldType.Text)
    private String text;

    private String author;

    @Field(type = FieldType.Nested)
    private List<String> genres;

    @Field(type = FieldType.Keyword)
    private String keywords;

    @Field(type = FieldType.Boolean)
    private boolean approved = false;

    @Field(type = FieldType.Double)
    private double price;

    private String filename;

}
