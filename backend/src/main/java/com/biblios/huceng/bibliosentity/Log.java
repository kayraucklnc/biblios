package com.biblios.huceng.bibliosentity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;



@Entity
@Table
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String ID;
    private Date timestamp;
    private String log;

    public Log(String log) {
        this.timestamp = new Date();
        this.log = log;
    }
}
