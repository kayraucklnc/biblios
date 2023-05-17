package com.biblios.huceng.usecases.author.dto;

import lombok.Data;

@Data
public class AuthorRequest {
    String ID;
    String name;
    String surname;
    String dateOfBirth;
    String nationality;
}
