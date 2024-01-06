package com.crawler.CrawlerQueue;

import com.crawler.QueueIndex.QueueIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class CrawlerQueueService {

    @Autowired
    private CrawlerQueueRepository crawlerQueueRepository;

    @Autowired
    private QueueIndexService queueIndexService;

    public void add(CrawlerQueue crawlerQueue){
        crawlerQueueRepository.save(crawlerQueue);
    }

    public void addNewLink(String link){
        crawlerQueueRepository.save(new CrawlerQueue(link));
    }

    public void addNewLinks(HashSet<String> links){
        crawlerQueueRepository.saveAll(
                links.stream().map(CrawlerQueue::new).toList()
        );
    }

    public @Nullable String getNextLink(){
        Long lastId = getLastId();
        Optional<CrawlerQueue> optional = crawlerQueueRepository.findNextById(lastId);


        if (optional.isEmpty()){
            return null;
        }

        incrementLastId(optional.get().getId());
        return optional.get().getLink();
    }

    private void incrementLastId(Long lastId){
        queueIndexService.setQueueIndex(lastId);
    }
    private Long getLastId(){
        return queueIndexService.getQueueIndex();
    }


    public List<CrawlerQueue> findLinksInQueue(ArrayList<String> links){
        return crawlerQueueRepository.findByLinkIn(links);
    }

}
