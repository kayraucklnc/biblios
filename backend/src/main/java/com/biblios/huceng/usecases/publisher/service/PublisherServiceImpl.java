package com.biblios.huceng.usecases.publisher.service;


import com.biblios.huceng.bibliosentity.Publisher;
import com.biblios.huceng.bibliosentity.bibliosrepository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PublisherServiceImpl implements PublisherService{

    private final PublisherRepository publisherRepository;

    @Override
    public Publisher getPublisher(String ID) { return publisherRepository.getPublisherByID(ID); }

    @Override
    public void createPublisher(Publisher publisher) {publisherRepository.save(publisher);}

    @Override
    public void createAllPublishers(List<Publisher> publishers) {publisherRepository.saveAll(publishers);}

    @Override
    public void deletePublisher(String ID) {publisherRepository.deleteById(ID);}

    @Override
    public String getNamePublisher(String ID) { return publisherRepository.getPublisherNameByID(ID);}

    @Override
    public List<Publisher> getAllPublishersByID() {return publisherRepository.getAllPublishersByID();}

    @Override
    public Publisher getPublisherByName(String name) {return publisherRepository.getPublisherByName(name);}


}
