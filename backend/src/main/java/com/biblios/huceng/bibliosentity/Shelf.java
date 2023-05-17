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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Shelf {

    @Id
    private String ID;
    private String section;
    private String floor;



    public Shelf(String ID, String section, String floor, Campus campus) {
        this.ID = ID;
        this.section = section;
        this.floor = floor;
        this.campus = campus;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }


    @ManyToOne()
    @JoinColumn(name = "campusID")
    private Campus campus;

    @OneToMany(mappedBy = "shelf", cascade = {CascadeType.REMOVE, CascadeType.DETACH, CascadeType.PERSIST}, orphanRemoval = true)
    @JsonIgnore
    private Collection<Book> books = new ArrayList<>();

}
