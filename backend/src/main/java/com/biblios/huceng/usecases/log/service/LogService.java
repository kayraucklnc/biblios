package com.biblios.huceng.usecases.log.service;

import com.biblios.huceng.bibliosentity.Log;
import com.biblios.huceng.entity.AppUser;

import java.util.List;

public interface LogService {

    public void addLog(String s);

    List<Log> getLogs();
}
