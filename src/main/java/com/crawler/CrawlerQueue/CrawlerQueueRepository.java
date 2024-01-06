package com.crawler.CrawlerQueue;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface CrawlerQueueRepository extends JpaRepository<CrawlerQueue,Long> {

    @Query(value = "SELECT * FROM crawler_queue WHERE id >= ?1 limit 1", nativeQuery = true)
    Optional<CrawlerQueue> findNextById(Long aLong);


        List<CrawlerQueue> findByLinkIn(List<String> links);
}
