package com.example.shubham.theclickster;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Random;

public class GameMenu extends Activity {
    // Remove the below line after defining your own ad unit ID.
    private static final String TOAST_TEXT = "Test ads are being shown. "
            + "To show live ads, replace the ad unit ID in res/values/strings.xml with your own ad unit ID.";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game_menu);
        final MediaPlayer mediaPlayer1=MediaPlayer.create(this,R.raw.psbutton);
        // Load an ad into the AdMob banner view.
        AdView adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);

        // Toasts the test ad message on the screen. Remove this after defining your own ad unit ID.


        RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.gamescreen);



        Button newgame,highscore,levels,about;
        newgame=(Button)findViewById(R.id.NEWgame);
        //highscore=(Button)findViewById(R.id.HighScoregame);
        levels=(Button)findViewById(R.id.LEVELgame);
        about=(Button)findViewById(R.id.ABOUTgame);
        Random r = new Random();
        int colorB = Color.argb(255, r.nextInt(256), r.nextInt(256), r.nextInt(256));
        relativeLayout.setBackgroundColor(colorB);



        newgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer1.start();
                Intent i= new Intent(getApplicationContext(),StartGame.class);
                startActivity(i);
            }
        });
       /* highscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               mediaPlayer1.start();
                Intent i= new Intent(getApplicationContext(),HighsScore.class);
                startActivity(i);
            }
        });
           */
        levels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer1.start();
                Intent i= new Intent(getApplicationContext(),Levels.class);
                startActivity(i);
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer1.start();
                Intent i= new Intent(getApplicationContext(),About.class);
                startActivity(i);
            }
        });















    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
