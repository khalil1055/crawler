package com.crawler.CrawlerQueue;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity(name = "crawler_queue")
@Table(indexes = {
        @Index(columnList = "link" ,unique = true)
})
public class CrawlerQueue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @URL
    @NotBlank(message = "Name is mandatory")
    @Column(nullable = false)
    private String link;

    public CrawlerQueue() {
    }

    public CrawlerQueue(String link) {
        this.link = link;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "CrawlerQueue{" +
                "id=" + id +
                ", link='" + link + '\'' +
                '}';
    }
}
