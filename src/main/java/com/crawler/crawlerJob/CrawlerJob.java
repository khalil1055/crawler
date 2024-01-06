package com.crawler.crawlerJob;

import com.crawler.CrawlerQueue.CrawlerQueue;
import com.crawler.CrawlerQueue.CrawlerQueueService;
import com.crawler.LinkData.LinkData;
import com.crawler.LinkData.LinkDataRepository;
import com.crawler.LinkData.LinkDataService;
import com.mongodb.lang.Nullable;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@EnableScheduling
@Service
public class CrawlerJob {

    private static final int COUNT = 10;
    @Autowired
    private CrawlerQueueService crawlerQueueService;
    @Autowired
    private LinkDataService linkDataService;

    @Autowired
    private HandlePageLinks handlePageLinks ;
    @Autowired
    private LinkDataRepository linkDataRepository;


    //        @Scheduled(fixedRate = 1000*60)
    public void process() {
        ArrayList<LinkData> linkDataArrayList = new ArrayList<>();

        for (int i = 0; i < COUNT; i++) {

            String link = crawlerQueueService.getNextLink();
            System.out.println("link job :: " + link);
            if(link == null) break;;

            Optional<LinkData> o = crawlLink(link);
            if (o.isPresent()){
                linkDataArrayList.add(o.get());
            }

        }

        System.out.println(linkDataArrayList);
        List<LinkData> newLinks = linkDataService.saveAll(linkDataArrayList);
//        System.out.println(newLinks.toString());
    }


    private Optional<LinkData> crawlLink(String link){
        ReadPageData readPageData = new ReadPageData(link);

        if (!readPageData.isValidPage()) {
            System.out.println("the link is not valid");
            return Optional.empty() ;
        }
        handlePageLinks.process(readPageData.getDocumentLinks());


        return Optional.of(
                new LinkData(
                        link, readPageData.getDocumentTitle(),
                        readPageData.getDocumentDesc(),
                        readPageData.getDocumentKeywords()
                ));

    }

}
