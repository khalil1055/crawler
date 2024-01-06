package com.crawler.crawlerJob;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class ReadPageData {

    private Document doc;
    private String link;

    public ReadPageData(String link) {
        this.link = link;
        this.setLinkDocument(link);
    }

    public boolean isValidPage(){
        return this.doc != null;
    }

    private void setLinkDocument(String link){
        try {
            this.doc = Jsoup.connect(link)
                    .timeout(3000)
                    .get();
        }catch (Exception e){
            this.doc = null;
        }
    }


    public String getDocumentTitle(){
        return doc.title();
    }

    public String getDocumentDesc(){
        String desc = "";

        Elements descElements = doc.select("meta[name*=description]");
        if(!descElements.isEmpty()){
            desc = descElements.get(0).attr("content");
        }
        return desc;
    }

    public String getDocumentKeywords(){
        String keywords = "";

        Elements keywordElements = doc.select("meta[name*=keywords]");
        if (!keywordElements.isEmpty()){
            keywords = keywordElements.get(0).attr("content");
        }

        return keywords;
    }

    public ArrayList<String> getDocumentLinks(){
        ArrayList<String> links = new ArrayList<>();

        Elements pageLinks = doc.select("a");
        for (Element e : pageLinks){
            links.add(e.attr("href"));
        }

        return links;
    }


}
