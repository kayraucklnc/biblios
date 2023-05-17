package com.biblios.huceng.usecases.campus.service;

import com.biblios.huceng.bibliosentity.Campus;

import java.util.List;

public interface CampusService {

    Campus getCampus(String ID);

    void createCampus(Campus campus);

    void createAllCampus(List<Campus> campus);

    void deleteCampus(String ID);


    List<Campus> getAllCampusByName();

    List<Campus> getAllCampusByID();

    Campus getCampusByName(String name);
}
