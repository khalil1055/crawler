package com.crawler.LinkData;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkDataRepository extends MongoRepository<LinkData,String> {
}
