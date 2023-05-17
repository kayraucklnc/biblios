package com.biblios.huceng.usecases.series.service;

import com.biblios.huceng.bibliosentity.Publisher;
import com.biblios.huceng.bibliosentity.Series;

import java.util.List;

public interface SeriesService {


    Series getSeries(String ID);

    void createSeries(Series series);

    void createAllSeries(List<Series> series);

    void deleteSeries(String ID);

    String getNameSeries(String ID);

    List<Series> getAllSeriesByID();

    Series getSeriesByName(String name);

}
