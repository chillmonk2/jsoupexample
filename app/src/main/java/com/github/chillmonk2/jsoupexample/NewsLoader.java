package com.github.chillmonk2.jsoupexample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<NewsObject>> {
    public static final String url = "http://rvrjc.ac.in/";

    public NewsLoader(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Nullable
    @Override
    public List<NewsObject> loadInBackground() {
        List<NewsObject> newsList = new ArrayList<>();
        try {
            Document document = Jsoup.connect(url).get();
            Elements lnews = document.select("ul.newsticker li b");
            //Get the title of the website
            for (Element news : lnews) {
                String descNews = news.select("p").text();
                String newsUrl = news.select("a[href]").toString();
                newsList.add(new NewsObject(descNews, newsUrl));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return newsList;
    }
}
