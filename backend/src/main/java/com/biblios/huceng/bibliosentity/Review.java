package com.biblios.huceng.bibliosentity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    public Review(Float rate, Date timestamp, Book parentBook) {
        this.rate = rate;
        this.timestamp = timestamp;
        this.parentBook = parentBook;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Float rate;
    private Date timestamp;

    @ManyToOne()
    @JoinColumn(name="book_id")
    @ToString.Exclude
    private Book parentBook;
}
