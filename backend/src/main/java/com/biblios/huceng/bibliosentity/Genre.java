package com.biblios.huceng.bibliosentity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Data
@NoArgsConstructor
//@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Genre {

    @Id
    private String ID;
    private String name;
    private Integer numOfBooks;

    public Genre(String ID, String name, Integer numOfBooks) {
        this.ID = ID;
        this.name = name;
        this.numOfBooks = numOfBooks;
    }


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumOfBooks() {
        return numOfBooks;
    }

    public void setNumOfBooks(Integer numOfBooks) {
        this.numOfBooks = numOfBooks;
    }
}
