package com.biblios.huceng.usecases.requestUserInfo.dto;

import lombok.Data;

import java.util.Collection;


@Data
public class ProfileSummary {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String about;
    private double rating;


    public ProfileSummary(String firstName, String lastName, String email, String username, String about, double rating) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.about = about;
        this.rating = rating;
    }
}
