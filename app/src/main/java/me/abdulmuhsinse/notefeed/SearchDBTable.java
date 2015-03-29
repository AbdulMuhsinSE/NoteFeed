package me.abdulmuhsinse.notefeed;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.nfc.Tag;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by AbdulMuhsin on 3/28/2015.
 */
public class SearchDBTable {
    private final dbOpenHelper mDatabaseOpenHelper;
    public static final String ID = "id";
    public static final String AVAILTAGS = "availtags";

   private static String DATABASE_NAME = "SearchDB";
    private static int DATABASE_VERSION = 1;
    private static String TABLE_NAME = "SEARCH";


    public SearchDBTable(Context context){
        mDatabaseOpenHelper = new dbOpenHelper(context);
    }

    public Cursor getWordMatches(String query, String[] columns) {
        String selection = ID + " Match ?";
        String[] selectionArgs = new String[] {query+"*"};

        return query(selection, selectionArgs, columns);
    }

    private Cursor query(String selection, String[] selectionArgs, String[] columns) {
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(TABLE_NAME);

        Cursor cursor = builder.query(mDatabaseOpenHelper.getReadableDatabase(),columns,selection,selectionArgs,null,null,null);

        if(cursor==null){
            return null;
        } else if (!cursor.moveToFirst()){
            cursor.close();
            return null;
        }
        return cursor;

    }

    private static class dbOpenHelper extends SQLiteOpenHelper{
        private final Context mHelperContext;
        private SQLiteDatabase mDatabase;

        dbOpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            mHelperContext = context;
        }

        public void onCreate(SQLiteDatabase db){
            mDatabase = db;
            mDatabase.execSQL("CREATE VIRTUAL TABLE " + TABLE_NAME + " USING fts3 ( " + ID + " STRING PRIMARY KEY, "+ AVAILTAGS + " TEXT");
            loadDataDictionary();
        }

        public void onUpgrade(SQLiteDatabase db, int oldV, int newV){
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }

        private void loadDataDictionary() {
            new Thread(new Runnable(){
                public void run() {
                    try{
                        loadData();
                    } catch (IOException e){
                        System.out.println("This failed and it is all your fault.");
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        private void loadData() throws IOException {
            final Resources resources = mHelperContext.getResources();
            InputStream inputStream = resources.openRawResource(R.raw.data);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            try {
                String line;
                while ((line = br.readLine())!=null){
                    String[] strings = TextUtils.split(line, "-");
                    if (strings.length<2) continue;
                    long id = addWord(strings[0].trim(),strings[1].trim());
                    if(id<0){
                        continue;
                    }
                }
            } finally {
                br.close();
            }
        }

        public long addWord(String id, String availTags) {
            ContentValues iniValues = new ContentValues();
            iniValues.put(ID, id);
            iniValues.put(AVAILTAGS, availTags);

            return mDatabase.insert(TABLE_NAME, null, iniValues);

        }
    }
}
