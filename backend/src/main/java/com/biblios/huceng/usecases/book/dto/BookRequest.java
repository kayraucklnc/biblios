package com.biblios.huceng.usecases.book.dto;

import lombok.Data;

@Data
public class BookRequest {
    Long ISBN;
    String name;
    String format;
    String LocationID;
    String author;
    String photoURL;
    Integer totalCopies;
    Integer copiesLeft;
    String category;
    String description;
    Double rate;
}