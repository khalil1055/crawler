package com.crawler.QueueIndex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueueIndexService {

    @Autowired
    private QueueIndexRepository queueIndexRepository;

    public Long getQueueIndex(){
        return queueIndexRepository.findById(1L).get().getNdx();
    }

    public void setQueueIndex(Long index){
        QueueIndex queueIndex = new QueueIndex();
        queueIndex.setId(1L);
        queueIndex.setNdx(index+1);
        queueIndexRepository.save(queueIndex);
    }

}
