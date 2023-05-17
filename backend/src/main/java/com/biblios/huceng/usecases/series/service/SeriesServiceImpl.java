package com.biblios.huceng.usecases.series.service;


import com.biblios.huceng.bibliosentity.Publisher;
import com.biblios.huceng.bibliosentity.Series;
import com.biblios.huceng.bibliosentity.bibliosrepository.PublisherRepository;
import com.biblios.huceng.bibliosentity.bibliosrepository.SeriesRepository;
import com.biblios.huceng.usecases.publisher.service.PublisherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class SeriesServiceImpl implements SeriesService {

    private final SeriesRepository seriesRepository;

    @Override
    public Series getSeries(String ID) { return seriesRepository.getSeriesByID(ID); }

    @Override
    public void createSeries(Series series) {seriesRepository.save(series);}

    @Override
    public void createAllSeries(List<Series> series) {seriesRepository.saveAll(series);}

    @Override
    public void deleteSeries(String ID) {seriesRepository.deleteById(ID);}

    @Override
    public String getNameSeries(String ID) { return seriesRepository.getSeriesNameByID(ID);}

    @Override
    public List<Series> getAllSeriesByID() {return seriesRepository.getAllSeriesByID();}

    @Override
    public Series getSeriesByName(String name) {return seriesRepository.getSeriesByName(name);}


}
