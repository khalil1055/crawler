package com.crawler.QueueIndex;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueueIndexRepository extends JpaRepository<QueueIndex,Long> {
}
