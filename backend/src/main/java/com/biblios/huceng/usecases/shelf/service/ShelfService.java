package com.biblios.huceng.usecases.shelf.service;

import com.biblios.huceng.bibliosentity.Shelf;

import java.util.List;

public interface ShelfService {

    Shelf getShelf(String ID);

    void createShelf(Shelf shelf);

    void createAllShelves(List<Shelf> shelves);

    void deleteShelf(String ID);


    List<Shelf> getAllShelfByID();

}
