package com.biblios.huceng.usecases.shelf.service;

import com.biblios.huceng.bibliosentity.Shelf;
import com.biblios.huceng.bibliosentity.bibliosrepository.ShelfRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ShelfServiceImpl implements ShelfService{

    private final ShelfRepository shelfRepository;


    @Override
    public Shelf getShelf(String ID){return shelfRepository.getShelfByID(ID);}


    @Override
    public void createShelf(Shelf shelf){shelfRepository.save(shelf);}


    @Override
    public void createAllShelves(List<Shelf> shelves){shelfRepository.saveAll(shelves);}

    @Override
    public void deleteShelf(String ID){shelfRepository.deleteById(ID);}

    @Override
    public List<Shelf> getAllShelfByID(){return shelfRepository.getAllShelfByID();}
}
