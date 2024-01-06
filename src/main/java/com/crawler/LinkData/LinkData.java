package com.crawler.LinkData;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "link_data")
@Getter
@Setter
@ToString
public class LinkData {

    @Id
    private String id;

    @NotBlank
    @URL
    @Indexed( unique = true, direction = IndexDirection.ASCENDING)
    private String link;

    private String title;
    private String desc;
    private String keywords;
    private Long visits;
    private Integer references;

    public LinkData() {
    }

    public LinkData(String link, String title, String desc, String keywords, Long visits, Integer references) {
        this.link = link;
        this.title = title;
        this.desc = desc;
        this.keywords = keywords;
        this.visits = visits;
        this.references = references;
    }

    public LinkData(String link, String title, String desc, String keywords) {
        this.link = link;
        this.title = title;
        this.desc = desc;
        this.keywords = keywords;
        this.visits = 0L;
        this.references = 1;
    }
}
