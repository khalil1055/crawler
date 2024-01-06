package com.crawler.CrawlerQueue;

import com.crawler.QueueIndex.QueueIndexService;
import com.crawler.crawlerJob.CrawlerJob;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/crawler/link")
public class CrawlerQueueController {

    @Autowired
    private CrawlerQueueService crawlerQueueService;

    @Autowired
    private CrawlerJob crawlerJob;

    @Autowired
    private QueueIndexService queueIndexService;

    @PostMapping
    public String add(@Valid @RequestBody CrawlerQueue crawlerQueue){

//        this.crawlerQueueService.add(crawlerQueue);
        crawlerJob.process();

        return "ok";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
