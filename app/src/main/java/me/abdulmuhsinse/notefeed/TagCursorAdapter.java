package me.abdulmuhsinse.notefeed;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by AbdulMuhsin on 3/29/2015.
 */
public class TagCursorAdapter extends CursorAdapter {
    public TagCursorAdapter(Context context, Cursor cursor){
        super(context,cursor,0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent){
        return LayoutInflater.from(context).inflate(R.layout.activity_searchresults,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor){
        TextView id = (TextView) view.findViewById(R.id.resultsBody);
        TextView tags = (TextView) view.findViewById(R.id.tags);

        String ids = cursor.getString(cursor.getColumnIndexOrThrow("id"));
        String tag = cursor.getString(cursor.getColumnIndexOrThrow("availtags"));

        id.setText(ids);
        tags.setText(tag);
    }
}
