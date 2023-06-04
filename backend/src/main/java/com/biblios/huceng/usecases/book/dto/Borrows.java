package com.biblios.huceng.usecases.book.dto;

import com.biblios.huceng.bibliosentity.Book;
import com.biblios.huceng.entity.AppUser;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
public class Borrows {
    String username;
    String bookname;

    public Borrows() {

    }

    @JsonCreator
    public Borrows(@JsonProperty("username") String username, @JsonProperty("bookname") String bookname) {
        this.username = username;
        this.bookname = bookname;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookName) {
        this.bookname = bookName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}


