package com.biblios.huceng.usecases.log.service;

import com.biblios.huceng.bibliosentity.Log;
import com.biblios.huceng.bibliosentity.bibliosrepository.LogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;

    @Override
    public void addLog(String s) {
        Log log = new Log(s);
        logRepository.save(log);
    }

    @Override
    public List<Log> getLogs() {
        return logRepository.findAll();
    }
}
