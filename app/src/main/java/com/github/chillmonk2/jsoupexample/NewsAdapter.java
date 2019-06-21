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
        TextView magnitudeView = (TextView) listItemView.findViewById(R.id.magnitude);

        mNewsDesc.setText(currentNews.getNewsDesc());
        magnitudeView.setText(currentNews.getNewsStatus());



        return listItemView;
    }

    }
