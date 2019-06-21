package com.github.chillmonk2.jsoupexample;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import java.util.ArrayList;


public class NewsAdapter extends ArrayAdapter<NewsObject> {
    public NewsAdapter(Context context, ArrayList<NewsObject> words) {
        super(context, 0, words);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_list_item, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        final NewsObject currentNews = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID miwok_text_view.
        TextView mNewsDesc = (TextView) listItemView.findViewById(R.id.newsDescTV);

        mNewsDesc.setText(currentNews.getNewsDesc());

        // Find the TextView in the list_item.xml layout with the ID default_text_view.
        final TextView mNewsUrl = (TextView) listItemView.findViewById(R.id.newsUrlTV);
        // Get the default translation from the currentWord object and set this text on
        // the default TextView.
        mNewsUrl.setText(currentNews.getNewsUrl());
        Log.e(MainActivity.class.getSimpleName(),mNewsDesc+" "+ mNewsUrl);


        return listItemView;
    }

    }
