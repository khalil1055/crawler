package com.crawler.LinkData;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class LinkDataService {

    @Autowired
    private LinkDataRepository linkDataRepository;

    public List<LinkData> saveAll(ArrayList<LinkData> linkDataArrayList){
        return linkDataRepository.saveAll(linkDataArrayList);
    }

    public void updateReferences(HashSet<String> links){
        // update  where link in (?,?,?,?) set reference = reference + 1

        //
    }


}
