package me.abdulmuhsinse.notefeed;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by AbdulMuhsin on 3/28/2015.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private static final int TYPE_HEADER = 0;

    private static final int TYPE_ITEM = 1;

    private String navTitles[];
    private int icons[];

    private String name;
    private int profile;
    private String email;

    //Creating a ViewHolder
    //ViewHolder stores inflated view in order to recycle

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        int HolderID;

        TextView textView;
        ImageView imageView;
        ImageView profile;
        TextView name;
        TextView email;
        Context context;

        public ViewHolder(View itemView, int viewType, Context context){
            super(itemView);
            this.context = context;
            itemView.setClickable(true);
            itemView.setOnClickListener(this);
            //set appropriate view

            if(viewType == TYPE_ITEM){
                textView = (TextView) itemView.findViewById(R.id.rowText);
                imageView = (ImageView) itemView.findViewById(R.id.rowIcon);
                HolderID = 1;
            } else {
                name = (TextView) itemView.findViewById(R.id.name);
                email = (TextView) itemView.findViewById(R.id.email);
                profile = null;//(ImageView) itemView.findViewById(R.id.circleView);
                HolderID = 0;
            }
        }

        @Override
        public void onClick(View view){
            Toast.makeText(context, "The item clicked is: " + getPosition(), Toast.LENGTH_SHORT).show();
        }
    }


    MyAdapter(String Titles[],int icons[],String name, String email, int profile){
        this.navTitles = Titles;
        this.icons = icons;
        this.name = name;
        this.email = email;
        this.profile = profile;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        if(viewType == TYPE_ITEM) {
            View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false);
            return new ViewHolder(view,viewType,parent.getContext());
        } else if (viewType == TYPE_HEADER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.header,parent,false); //Inflating the layout
            return new ViewHolder(view,viewType,parent.getContext()); //Creating ViewHolder and passing the object of type view
        }

        return null;
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        if(holder.HolderID == 1){
            holder.textView.setText(navTitles[position-1]);
            //holder.imageView.setImageResource(icons[position-1]);
        } else {
            //holder.profile.setImageResource(profile);
            holder.name.setText(name);
            holder.email.setText(email);
        }
    }

    @Override
    public int getItemCount() {
        return navTitles.length+1;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position)){
            return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }
}