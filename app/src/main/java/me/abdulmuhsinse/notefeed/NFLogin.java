package me.abdulmuhsinse.notefeed;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;


public class NFLogin extends ActionBarActivity {
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nflogin);
        image = (ImageView) findViewById(R.id.mainIcon);
        try{
            InputStream is = getAssets().open("nf_launcher.png");
            Drawable d = Drawable.createFromStream(is,null);
            image.setImageDrawable(d);
        } catch (IOException ex){
            ex.printStackTrace();
        }

        /*final EditText usernames = (EditText) findViewById(R.id.usernameLogin);
        final EditText passwords = (EditText) findViewById(R.id.passwordLogin);
        final CardView signIn = (CardView) findViewById(R.id.signinButton);

        usernames.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == 66) {
                    passwords.requestFocus();
                }
                return false;
            }
        });

        passwords.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == 66) {
                    signIn.requestFocus();
                }
                return false;
            }
        });*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_nflogin, menu);
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
    public void signIn(View view){

        //for test purposes will assume automatic connection
       EditText username = (EditText) findViewById(R.id.usernameLogin);
       EditText password = (EditText) findViewById(R.id.passwordLogin);

        String userName = username.getText().toString();
        String passWord = password.getText().toString();



        System.out.println(passWord + userName);
        if(passWord.contains(" ")||passWord.contains("/n")||userName.contains(" ")||userName.length()==0||passWord.length()==0){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.userpassEmpty).setTitle(R.string.upError);
            AlertDialog dialog = builder.create();
            dialog.show();
        } else if (authenticateCredentials(userName,passWord)){
            Intent intent = new Intent(this,NFFeed.class);
            intent.putExtra("username",userName );
            intent.putExtra("email","abc@hotmail.com");
            startActivity(intent);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.userauthError).setTitle(R.string.upError);
            AlertDialog dialog = builder.create();
            dialog.show();
        }

    }

    public void signUp(View view){

        Intent intent = new Intent(this,NFSignUp.class);

        startActivity(intent);
    }

    private boolean authenticateCredentials(String userName, String passWord) {
        if(passWord.length()<=6){
            return false;
        } else if (!userName.toLowerCase().equals("abdulmuhsin") && !userName.toLowerCase().equals("jimmy")){
            return false;
        }
        return true;
    }
}
