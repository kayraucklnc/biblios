package com.biblios.huceng.usecases.book.dto;

import com.biblios.huceng.bibliosentity.Book;
import com.biblios.huceng.entity.AppUser;

public class Borrows {
    String username;
    String bookname;

    public Borrows(String username, String bookname) {
        this.username = username;
        this.bookname = bookname;
    }
}
