package com.github.chillmonk2.jsoupexample;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;

import android.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.chillmonk2.jsoupexample.data.NewsContract;
import com.github.chillmonk2.jsoupexample.data.NewsDbHelper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
   public TextView mNewsDescTV;
   public TextView mNewsUrlTV;
    public StringBuilder t;
    public  StringBuilder n;
    public String title;
    public ProgressDialog progressDialog;
    public ListView listView;
    public NewsAdapter mNewsAdapter;
    private SwipeRefreshLayout mSwipeRefresh;
    //public NewsDbHelper mNewsDbHelper ;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    new TaskLoader().execute();
                    return true;
                case R.id.navigation_dashboard:
                    //mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    //mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("News");
        getSupportActionBar().setTitle("News");
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //mNewsDbHelper = new NewsDbHelper(this);

        mSwipeRefresh = findViewById(R.id.swipeRefreshLayout);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mNewsDescTV = findViewById(R.id.newsDescTV);
        //progressDialog = new ProgressDialog(getApplicationContext());
        //mNewsUrlTV = findViewById(R.id.newsUrlTV);
        listView = findViewById(R.id.listview);
        mNewsAdapter = new NewsAdapter(this, new ArrayList<NewsObject>());
        listView.setAdapter(mNewsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewsObject mNewsObject = mNewsAdapter.getItem(position);
                String mNewsUrl = mNewsObject.getNewsUrl();
                if (mNewsUrl != null) {
                    Intent openLinkInBrowser = new Intent(Intent.ACTION_VIEW);
                    openLinkInBrowser.setData(Uri.parse(mNewsUrl));
                    startActivity(openLinkInBrowser);
                } else {
                    Toast.makeText(MainActivity.this, "Links are not provided", Toast.LENGTH_SHORT).show();
                }
            }
        });
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            if(isNetworkAvailable()==true)
                new TaskLoader().execute();
            else
            {
                Toast.makeText(MainActivity.this, "No Network", Toast.LENGTH_LONG).show();
            }
            mSwipeRefresh.setRefreshing(false);
        }
    });
        if(isNetworkAvailable()==true)
            new TaskLoader().execute();
        else
        {
            Toast.makeText(this, "No Network", Toast.LENGTH_LONG).show();
        }
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private class TaskLoader extends AsyncTask<Void,Void,ArrayList<NewsObject>> {
        public TaskLoader() {
            super();
        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
             progressDialog= new ProgressDialog(MainActivity.this);
             progressDialog.setMessage("Fetching News");
             progressDialog.show();
        }

        @Override
        protected ArrayList<NewsObject> doInBackground(Void... voids) {
            String url = "http://rvrjc.ac.in/";
            ArrayList<NewsObject> mList = new ArrayList<NewsObject>();
            Document document;
            try {
                Log.d(MainActivity.class.getSimpleName(),"Main Activity");
                document = Jsoup.connect(url).get();
                Elements lnews = document.select("ul.newsticker li b");
                Elements bnews = document.select("div#galleryimage p");
                //Get the title of the website

                for(Element news:bnews){

                    String descNews = news.select("p").text();
                    String newsGif = news.select("p img").attr("src");
                    String newsStatus = "Old" ;
                    if(newsGif.equals("new.gif"))
                    {
                        newsStatus = "New";
                        //System.out.println("News Status changed");
                    }
                    String newsUrl = news.select("a").attr("href");
                    if(newsUrl.startsWith("http"))
                    {
                        //do nothing
                        mList.add(new NewsObject(descNews,newsUrl,newsStatus));
                    }
                    else
                        newsUrl=null;
                }
                for (Element news : lnews) {//latest news Section
                    String descNews = news.select("p").text();
                    String newsUrl = news.select("a").attr("href");
                    if (newsUrl.startsWith("http")) {
                        //do nothing
                        mList.add(new NewsObject(descNews, newsUrl,"Old"));
                    } else
                        newsUrl = null;
                }


            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return mList;
        }
        @Override
        protected void onPostExecute(ArrayList<NewsObject> newsArrayList) {
            super.onPostExecute(newsArrayList);
            progressDialog.dismiss();
            mNewsAdapter.addAll(newsArrayList);
            mNewsAdapter.notifyDataSetChanged();
        }
    }
}






