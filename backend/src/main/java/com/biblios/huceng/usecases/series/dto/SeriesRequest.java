package com.biblios.huceng.usecases.series.dto;


import lombok.Data;

@Data
public class SeriesRequest {

    String ID;
    String name;
    Integer numOfBooks;
}
