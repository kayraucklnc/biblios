package com.biblios.huceng.bibliosentity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Campus {

    @Id
    private String ID;
    private String name;


    public Campus(String ID, String name) {
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


    @OneToMany(mappedBy = "campus", cascade = {CascadeType.REMOVE, CascadeType.DETACH, CascadeType.PERSIST}, orphanRemoval = true)
    @JsonIgnore
    private Collection<Shelf> shelves = new ArrayList<>();
}
