package com.example.storedemo.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BookRequest {

    private long id;
    private String name;
    private String author;

}
