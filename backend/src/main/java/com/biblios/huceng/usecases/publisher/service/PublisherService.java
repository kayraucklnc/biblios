package com.biblios.huceng.usecases.publisher.service;

import com.biblios.huceng.bibliosentity.Book;
import com.biblios.huceng.bibliosentity.Publisher;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PublisherService {


    Publisher getPublisher(String ID);

    void createPublisher(Publisher publisher);

    void createAllPublishers(List<Publisher> publishers);

    void deletePublisher(String ID);

    String getNamePublisher(String ID);

    List<Publisher> getAllPublishersByID();

    Publisher getPublisherByName(String name);

}
