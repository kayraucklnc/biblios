package com.biblios.huceng.bibliosentity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table
@Data
@NoArgsConstructor
//@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Series {

    @Id
    private String ID;
    private String name;

    public Series(String ID, String name) {
        this.ID = ID;
        this.name = name;
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


    @OneToMany(mappedBy = "series", cascade = {CascadeType.REMOVE, CascadeType.DETACH, CascadeType.PERSIST}, orphanRemoval = true)
    @JsonIgnore
    private Collection<Book> books = new ArrayList<>();
}
