package com.github.chillmonk2.jsoupexample;

public class NewsObject {

    String newsUrl = null;
    String newsDesc = null;
    public NewsObject(String newsDesc,String newsUrl)
    {
        this.newsDesc = newsDesc;
        this.newsUrl = newsUrl;
    }
    public String getNewsUrl()
    {
        return newsUrl;
    }
    public String getNewsDesc()
    {
        return newsDesc;
    }


}
