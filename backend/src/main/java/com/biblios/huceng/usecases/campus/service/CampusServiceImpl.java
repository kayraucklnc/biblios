package com.biblios.huceng.usecases.campus.service;

import com.biblios.huceng.bibliosentity.Campus;
import com.biblios.huceng.bibliosentity.bibliosrepository.CampusRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CampusServiceImpl implements CampusService{

    private final CampusRepository campusRepository;

    @Override
    public Campus getCampus(String ID){return campusRepository.getCampusByID(ID);}

    @Override
    public void createCampus(Campus campus){campusRepository.save(campus);}

    @Override
    public void createAllCampus(List<Campus> campus){campusRepository.saveAll(campus);}

    @Override
    public void deleteCampus(String ID){campusRepository.deleteById(ID);}

    @Override
    public List<Campus> getAllCampusByName(){return campusRepository.getAllCampusByName();}

    @Override
    public List<Campus> getAllCampusByID(){return campusRepository.getAllCampusByID();}

    @Override
    public Campus getCampusByName(String name){return campusRepository.getCampusByName(name);}
}
