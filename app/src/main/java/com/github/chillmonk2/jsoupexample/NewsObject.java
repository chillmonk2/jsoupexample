package com.github.chillmonk2.jsoupexample;

public class NewsObject {

    String newsUrl = null;
    String newsDesc = null;
    String newsStatus = "Old";
    public NewsObject(String newsDesc,String newsUrl,String newsStatus)
    {
        this.newsDesc = newsDesc;
        this.newsUrl = newsUrl;
        this.newsStatus = newsStatus;
    }
    public String getNewsUrl()
    {
        return newsUrl;
    }
    public String getNewsDesc()
    {
        return newsDesc;
    }
    public String getNewsStatus() { return newsStatus;}

}
