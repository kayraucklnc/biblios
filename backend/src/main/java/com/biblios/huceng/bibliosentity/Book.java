package com.biblios.huceng.bibliosentity;


import com.biblios.huceng.entity.AppUser;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Book {

    @Id
    private Long ISBN;
    private String name;
    private String format;
    private String author;
    private String photoURL;
    private Integer totalCopies;
    private Integer copiesLeft;
    private String category;
    @Column(columnDefinition="TEXT")
    private String description;
    private Double rate;


    public Book(Long ISBN, String name, String format,  String author, String photoURL, Integer copiesLeft, Integer totalCopies, String category, String description, Double rate, Shelf shelf, Publisher publisher, Series series) {
        this.ISBN = ISBN;
        this.name = name;
        this.format = format;
        this.author = author;
        this.photoURL = photoURL;
        this.copiesLeft = copiesLeft;
        this.totalCopies = totalCopies;
        this.category = category;
        this.description = description;
        this.rate = rate;
        this.shelf = shelf;
        this.publisher = publisher;
        this.series = series;
    }

    public Book(Long ISBN, String name, String format,  String author, String photoURL, Integer copiesLeft, Integer totalCopies, String category, String description, Double rate, Shelf shelf, Publisher publisher ) {
        this.ISBN = ISBN;
        this.name = name;
        this.format = format;
        this.author = author;
        this.photoURL = photoURL;
        this.copiesLeft = copiesLeft;
        this.totalCopies = totalCopies;
        this.category = category;
        this.description = description;
        this.rate = rate;
        this.shelf = shelf;
        this.publisher = publisher;
    }


    @ManyToMany()
    @JsonIgnore
    @JoinTable(
            name = "BorrowedBooks",
            joinColumns = @JoinColumn(name = "ISBN"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Collection<AppUser> borrowedByUsers = new ArrayList<>();

    @OneToMany(mappedBy = "parentBook", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnore
    public Collection<Review> reviews = new ArrayList<>();

    public Long getISBN() {
        return ISBN;
    }

    public void setISBN(Long ISBN) {
        this.ISBN = ISBN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }



    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public Integer getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(Integer totalCopies) {
        this.totalCopies = totalCopies;
    }

    public Integer getCopiesLeft() {
        return copiesLeft;
    }

    public void setCopiesLeft(Integer copiesLeft) {
        this.copiesLeft = copiesLeft;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn=" + ISBN + '\'' +
                ", name='" + name + '\'' +
                ", photoLink='" + photoURL + '\'' +
                ", format='" + format + '\'' +
                ", author='" + author + '\'' +
                ", total number of copies=" + totalCopies + '\'' +
                ", number of copies left=" + copiesLeft + '\'' +
                ", rate=" + rate + '\'' +
                ", description=" + description + '\'' +
                ", category=" + category +

                '}';
    }


    @ManyToMany
    @JsonBackReference
    @JoinTable(name = "book_author",
            joinColumns = @JoinColumn(name = "author"),
            inverseJoinColumns = @JoinColumn(name = "ID"))
    private Set<Author> authors = new HashSet<>();


    @ManyToOne()
    @JoinColumn(name = "shelfID")
    private Shelf shelf;

    @ManyToOne()
    @JoinColumn(name = "publisherID")
    private Publisher publisher;

    @ManyToOne()
    @JoinColumn(name = "seriesID")
    private Series series;
}
