package com.github.chillmonk2.jsoupexample.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;


public final class NewsContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private NewsContract() {}

    /**
     * The "Content authority" is a name for the entire content provider, similar to the
     * relationship between a domain name and its website.  A convenient string to use for the
     * content authority is the package name for the app, which is guaranteed to be unique on the
     * device.
     */
    public static final String CONTENT_AUTHORITY = "com.github.chillmonk2.jsoupexample";

    /**
     * Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
     * the content provider.
     */
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    /**
     * Possible path (appended to base content URI for possible URI's)
     * For instance, content://com.example.android.pets/pets/ is a valid path for
     * looking at pet data. content://com.example.android.pets/staff/ will fail,
     * as the ContentProvider hasn't been given any information on what to do with "staff".
     */
    public static final String PATH_NEWS = "news";

    /**
     * Inner class that defines constant values for the NEWS database table.
     * Each entry in the table represents a single NEWS.
     */
    public static final class NewsEntry {


        public final static String TABLE_NAME = "newsDrive";
        public final static String _ID = "newsLink";
        public final static String COLUMN_NEWS_STATUS = "newsStatus";

        public static final int NEWS_OLD = 0;
        public static final int NEWS_NEW = 1;
    }


}
