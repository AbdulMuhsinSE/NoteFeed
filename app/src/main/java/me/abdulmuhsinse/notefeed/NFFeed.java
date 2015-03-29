package me.abdulmuhsinse.notefeed;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.Fragment;

import java.io.IOException;
import java.io.InputStream;


public class NFFeed extends ActionBarActivity {
    private static final int CONTENT_VIEW_ID = 5642;
    ImageView image;
    //Declare Titles for NavDrawer
    String TITLES[] = {"Feed","Content","Contacts","Preferences"};
    int ICONS[];

    String NAME = "PLACEHOLDER";
    String EMAIL = "PLACEHOLDER@GMAIL.COM";
    int PROFILE = R.drawable.abc_btn_check_material;

    private SearchView searchView;
    private ActionBar actionBar;
    private Toolbar toolbar;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nffeed);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        setTitle("Feed");


        Intent intent = getIntent();
        NAME = intent.getStringExtra("username");
        EMAIL = intent.getStringExtra("email");



        //Set up navdrawer
        recyclerView = (RecyclerView) this.findViewById(R.id.RecyclerView);
        recyclerView.setHasFixedSize(true);

        adapter = new MyAdapter(TITLES,null,NAME,EMAIL,PROFILE);
        recyclerView.setAdapter(adapter);

        final GestureDetector gestureDetector = new GestureDetector(NFFeed.this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent event){
                return true;
            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener(){
            @Override
            public boolean onInterceptTouchEvent(RecyclerView recyclerView1, MotionEvent event){
                View child = recyclerView1.findChildViewUnder(event.getX(),event.getY());

                if(child!=null && gestureDetector.onTouchEvent(event)){
                    drawer.closeDrawers();
                    int pos = recyclerView.getChildPosition(child);
                    switch (pos){
                        case 1:
                            setTitle("Feed");
                            Fragment newFragment = new ItemFragment();
                            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                            ft.replace(R.id.pleasework, newFragment).commit();
                            break;
                        case 2:
                            setTitle("Content");
                            Fragment aFragment = new ItemFragment2();
                            FragmentTransaction fst = getSupportFragmentManager().beginTransaction();
                            fst.replace(R.id.pleasework, aFragment).commit();
                            break;
                    }

                    Toast.makeText(NFFeed.this, "The Item Clicked is: " + recyclerView.getChildPosition(child), Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView recyclerView1, MotionEvent event){}
        });

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);



        drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);
        drawerToggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.openDrawer,R.string.closeDrawer) {
            @Override
            public void onDrawerOpened(View view) {
                super.onDrawerOpened(view);
            }

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

        };

        drawer.setDrawerListener(drawerToggle);
        drawerToggle.syncState();


        FrameLayout frame = (FrameLayout) findViewById(R.id.pleasework);
        //frame.setId(CONTENT_VIEW_ID);
        //setContentView(frame, new FrameLayout.LayoutParams(
           //     FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));

        if(savedInstanceState == null) {
            Fragment newFragment = new ItemFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.pleasework,newFragment).commit();
        }



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_nffeed, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo((getComponentName())));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (item.getItemId()){
            case R.id.addNew:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                LayoutInflater inflater = this.getLayoutInflater();

                builder.setView(inflater.inflate(R.layout.customdialogadd,null))
                        .setNeutralButton(R.string.add,new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Switch mySwitch = (Switch) findViewById(R.id.mySwitch);
                                TextView switchStatus = (TextView) findViewById(R.id.switchStatus);
                                boolean x = mySwitch.isChecked();

                            }
                        });
        }

        return super.onOptionsItemSelected(item);
    }
}
