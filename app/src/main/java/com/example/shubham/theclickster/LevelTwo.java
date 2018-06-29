package com.example.shubham.theclickster;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.Random;

public class LevelTwo extends Activity {
    int varX,varY;
    int flag=0; //GAME END
    long  Rtimer=10000;       ////changed

    final int LevelOneTarget=10;////changed
    int counter=0;
    int press=0;
    CountDownTimer NewTimer;
    LevelsData  db= new LevelsData(this,"LevelD.db",null,1);

    static int hscore;


    private InterstitialAd mInterstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_level_two);
        final  int temp3=db.checkHS(2);
        hscore=temp3;
        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(this, "ca-app-pub-3940256099942544/1033173712");


        // Create the InterstitialAd and set the adUnitId.
        mInterstitialAd = new InterstitialAd(this);
        // Defined in res/values/strings.xml
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                //// start the new activity
                Intent i=new Intent(LevelTwo.this,LevelThree.class);
                startActivity(i);
                finish();
            }
        });

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        final int Pwidth=dm.widthPixels;;
        final int Pheight=dm.heightPixels;
        final TextView timer=(TextView)findViewById(R.id.TIMgame);
        final TextView score=(TextView)findViewById(R.id.SCRgame);
        final ImageView bg=(ImageView)findViewById(R.id.gamescreen);
        final Button pause=(Button)findViewById(R.id.PASgame);
        final ImageButton circle=(ImageButton)findViewById(R.id.CIRClgame);
        //for changing color of circle
        final GradientDrawable bgShape = (GradientDrawable) circle.getDrawable();
        final MediaPlayer mediaPlayer=MediaPlayer.create(this,R.raw.button);
        final MediaPlayer mediaPlayer1=MediaPlayer.create(this,R.raw.psbutton);
        final MediaPlayer Win=MediaPlayer.create(this,R.raw.win);
        final MediaPlayer loss=MediaPlayer.create(this,R.raw.lose);
        final TextView target=(TextView)findViewById(R.id.TARGETgame);
        final TextView levelText=(TextView)findViewById(R.id.LEVELgmae);

        final ImageView arrow=(ImageView) findViewById(R.id.ARROWgame);
        target.setText("Target : "+LevelOneTarget);
        pause.setText("Start");
        pause.setBackgroundColor(Color.RED);
        final int Ix=(Pwidth/2);
        final int Iy =(Pheight/2);
        levelText.setTextColor(Color.BLACK);

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                levelText.setVisibility(View.GONE);
                AdRequest adRequest = new AdRequest.Builder().build();
                mInterstitialAd.loadAd(adRequest);
                mediaPlayer1.start();
                if(flag!=1)
                {      press=press+1;
                    if((press%2)==1)
                    {






                        NewTimer= new CountDownTimer(Rtimer, 1000) {

                            public void onTick(long millisUntilFinished) {
                                timer.setText("0:" + (millisUntilFinished / 1000));
                                target.setText("Target : "+LevelOneTarget);
                                pause.setText("pause");
                                Rtimer=millisUntilFinished;

                                arrow.setVisibility(View.GONE);
                                pause.setBackgroundColor(Color.GREEN);
                            }

                            public void onFinish() {
                                final int temp=counter;
                                final  int temp2=hscore;
                                if(temp<=LevelOneTarget)
                                {    loss.start();
                                    target.setText("Game Over");
                                    target.setBackgroundColor(Color.RED);
                                    pause.setText("play again");
                                       /*  Intent i=new Intent(StartGame.this,StartGame.class);
                                         startActivity(i);
                                         finish();
                                         */

                                }
                                else{
                                    Win.start();
                                    pause.setText("Next Level >");
                                }

                                bgShape.setColor(Color.GRAY);
                                timer.setTextColor(Color.BLACK);
                                score.setTextColor(Color.BLACK);
                                bg.setBackgroundColor(Color.WHITE);
                                circle.setX(Ix);
                                circle.setY(Iy);
                                timer.setText("Highscore: "+temp2);
                                score.setText("YourScore: "+temp);
                                arrow.setVisibility(View.VISIBLE);
                                target.setTextColor(Color.BLACK);



                                flag=1;


                            }};
                        NewTimer.start();


                    }
                    else
                    { pause.setText("Resume");
                        bgShape.setColor(Color.GRAY);
                        timer.setTextColor(Color.BLACK);
                        circle.setX(Ix);
                        circle.setY(Iy);
                        arrow.setVisibility(View.VISIBLE);
                        pause.setBackgroundColor(Color.RED);
                        score.setTextColor(Color.BLACK);
                        bg.setBackgroundColor(Color.WHITE);
                        NewTimer.cancel();
                    }

                }
                else
                {
                    if(counter<LevelOneTarget)
                    { Intent i=new Intent(LevelTwo.this,LevelTwo.class);
                        startActivity(i);
                        finish();
                    }
                    else{
                      showInterstitial();

                    }


                }
            }
        });

        //for changing background color
        bg.setBackgroundColor(Color.WHITE);

        final int height=Pheight;
        final int width=Pwidth;


        circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if((flag!=1)&&(press%2)==1) {
                    int esp = 0, esp2 = 0;
                    mediaPlayer.start();
                    int CHeight = circle.getHeight();
                    int CWidth = circle.getWidth();
                    final Random r = new Random();
                    int UbdX = width - (CWidth / 2);
                    int LbdX = (CWidth / 2);
                    int UbdY = height - (CHeight / 2);
                    int LbdY = (CHeight / 2);
                    int Px, Py;
                    int x = r.nextInt(UbdX - LbdX);
                    int y = r.nextInt(UbdY - LbdY);
                    varX = x + CWidth;
                    varY = y + CHeight;

                    counter = counter + 1;
                    while (esp2 == 0) {
                        if ((varY >= Pheight)) {
                            if (varX >= (Pwidth)) {
                                x = r.nextInt(UbdX - LbdX);
                                y = r.nextInt(UbdY - LbdY);
                                varX = x + CWidth;
                                varY = y + CHeight;
                                UbdX = width - (CWidth / 2);
                                LbdX = (CWidth / 2);
                                UbdY = height - (CHeight / 2);
                                LbdY = (CHeight / 2);
                                esp2 = 0;
                            } else {
                                x = r.nextInt(width - 0);
                                y = r.nextInt((height) - 0);
                                varX = x + CWidth;
                                varY = y + CHeight;
                                esp2 = 0;
                            }
                        } else {


                            circle.setX(x);
                            circle.setY(y);
                            esp2 = 1;
                        }
                    }
                    while (esp == 0) {
                        int colorC = Color.argb(255, r.nextInt(256), r.nextInt(256), r.nextInt(256));

                        int colorBG = Color.argb(255, r.nextInt(256), r.nextInt(256), r.nextInt(256));

                        if (colorBG == colorC) {
                            esp = 0;
                        } else {
                            bgShape.setColor(colorC);
                            timer.setTextColor(colorC);
                            score.setTextColor(colorC);
                            bg.setBackgroundColor(colorBG);
                            score.setText("Score : " + counter);
                            target.setTextColor(colorC);
                            esp = 1;
                        }
                    }
                    if (counter > hscore) {
                        while (hscore != counter) {
                            hscore++;
                        }
                        db.updateHS(2,hscore);
                    }

                }

            }
        });




    }
    private void showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and restart the game.
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();
            startGame();
        }
    }

    private void startGame() {
        // Request a new ad if one isn't already loaded, hide the button, and kick off the timer.
        if (!mInterstitialAd.isLoading() && !mInterstitialAd.isLoaded()) {


        }
















    }
}
