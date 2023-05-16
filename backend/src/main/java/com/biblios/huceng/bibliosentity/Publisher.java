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
public class Publisher {

    @Id
    private String ID;
    private String name;
    private String address;
    private String email;


    public Publisher(String ID, String name, String address, String email) {
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.email = email;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @OneToMany(mappedBy = "publisher", cascade = {CascadeType.REMOVE, CascadeType.DETACH, CascadeType.PERSIST}, orphanRemoval = true)
    @JsonIgnore
    private Collection<Book> books = new ArrayList<>();
}
