package me.abdulmuhsinse.notefeed;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;


public class NFSignUp extends ActionBarActivity {
    Context context = this;
    String username;
    String email;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfsign_up);
        CardView cview = (CardView)findViewById(R.id.toEmailEntryButton);
        cview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelativeLayout email = (RelativeLayout) findViewById(R.id.step2);
                RelativeLayout user = (RelativeLayout) findViewById(R.id.step1);

                EditText users = (EditText) findViewById(R.id.signuser);
                username = users.getText().toString();

                user.setVisibility(View.INVISIBLE);
                email.setVisibility(View.VISIBLE);
            }
        });

        CardView cview2 = (CardView)findViewById(R.id.toPasswordButton);
        cview2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                RelativeLayout emailme= (RelativeLayout) findViewById(R.id.step2);
                RelativeLayout pass= (RelativeLayout) findViewById(R.id.step3);

                EditText emails = (EditText) findViewById(R.id.signemail);
                email = emails.getText().toString();

                emailme.setVisibility(View.INVISIBLE);
                pass.setVisibility(View.VISIBLE);
            }
        });

        CardView cview3 = (CardView) findViewById(R.id.toFinishButton);
        cview3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText firstPassword = (EditText) findViewById(R.id.signpass);
                EditText secondPassword = (EditText) findViewById(R.id.signpass2);
                if(firstPassword.getText().toString().equals(secondPassword.getText().toString())){
                    Intent mIntent = new Intent(context,NFFeed.class);
                    mIntent.putExtra("username",username);
                    mIntent.putExtra("email",email);
                    mIntent.putExtra("password",password);
                    startActivity(mIntent);
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_nfsign_up, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void toEmailEntry(View view) {

    }

}
