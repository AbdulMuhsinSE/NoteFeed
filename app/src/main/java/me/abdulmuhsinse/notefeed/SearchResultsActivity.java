package me.abdulmuhsinse.notefeed;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

/**
 * Created by AbdulMuhsin on 3/28/2015.
 */
public class SearchResultsActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        handleIntent(getIntent());
    }

    protected void onNewIntent (Intent intent) {
        handleIntent(getIntent());
    }

    private void handleIntent(Intent intent){
        SearchDBTable db = new SearchDBTable(this);
        if(Intent.ACTION_SEARCH.equals(intent.getAction())){
            String query = intent.getStringExtra(SearchManager.QUERY);
            Cursor c = db.getWordMatches (query,null);
            //process cursor and display results
            ListView mListView = (ListView) findViewById(R.id.sListView);
            TagCursorAdapter cAdapter = new TagCursorAdapter(this,c);
            mListView.setAdapter(cAdapter);
        }
    }
}
