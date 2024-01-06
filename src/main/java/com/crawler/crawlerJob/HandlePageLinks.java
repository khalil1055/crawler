package com.crawler.crawlerJob;

import com.crawler.CrawlerQueue.CrawlerQueue;
import com.crawler.CrawlerQueue.CrawlerQueueService;
import com.crawler.LinkData.LinkDataService;
import com.mongodb.lang.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class HandlePageLinks {


    @Autowired
    private CrawlerQueueService crawlerQueueService;

    @Autowired
    private LinkDataService linkDataService;

    public void process(ArrayList<String> links){


        links = validateLinks(links);
        links.add("https://www.mongodb.com/docs/mongodb-shell/run-commands/");

//        System.out.println("links after validation :: " + links.toString());
        List<CrawlerQueue> inQueue = crawlerQueueService.findLinksInQueue(links);
        System.out.println( "in queue : " + inQueue);

        // update link data mongo

        // add the others to queue
        this.addTheNewLinksToCrawlerQueue(this.getTheNewLinks(links , inQueue));

    }

    private HashSet<String> getTheNewLinks(ArrayList<String> links , List<CrawlerQueue> dbCrawlerQueues){
        HashSet<String> dbLinks = new HashSet<>();
        for(CrawlerQueue dbCrawlerQueue : dbCrawlerQueues){
            dbLinks.add(dbCrawlerQueue.getLink().toLowerCase());
        }

        HashSet<String> newLinks = new HashSet<>();
        for(String link : links){
            if(dbLinks.contains(link.toLowerCase())){
//                System.out.println("in db " + link);
                continue;
            }
            newLinks.add(link);
        }

        return newLinks;
    }

    private void addTheNewLinksToCrawlerQueue(HashSet<String> links){
        crawlerQueueService.addNewLinks(links);
    }

    private void updateReferenceNumberToAlreadyVisited(List<CrawlerQueue> links){
        HashSet<String> linksArray = new HashSet<>();
        for(CrawlerQueue link : links){
            linksArray.add(link.getLink());
        }
        linkDataService.updateReferences(linksArray);
    }



    private ArrayList<String> validateLinks(ArrayList<String> links){
        ArrayList<String> validLinks = new ArrayList<>();

        for (String link : links){
            String validLink = isValid(link);

            if(validLink != null) {
                validLinks.add(validLink);
            }
        }

//        return links.stream().filter(this::isValid).toList();
        return  validLinks;
    }

    private @Nullable String isValid(String link){
        try{
            return new URL(link).toURI().toString();
        } catch (MalformedURLException e) {
        } catch (URISyntaxException e) {}

        return null;
    }
}
