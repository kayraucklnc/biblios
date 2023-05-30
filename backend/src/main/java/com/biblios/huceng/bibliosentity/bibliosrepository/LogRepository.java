package com.biblios.huceng.bibliosentity.bibliosrepository;

import com.biblios.huceng.bibliosentity.Book;
import com.biblios.huceng.bibliosentity.Log;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LogRepository  extends PagingAndSortingRepository<Log, Long> {
}
