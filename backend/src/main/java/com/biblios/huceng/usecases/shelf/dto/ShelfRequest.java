package com.biblios.huceng.usecases.shelf.dto;


import lombok.Data;

@Data
public class ShelfRequest {

    String ID;
    String section;
    String floor;
    String campusID;

}
