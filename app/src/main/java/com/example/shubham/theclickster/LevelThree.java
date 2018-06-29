package com.example.shubham.theclickster;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;

import java.util.Random;

public class LevelThree extends Activity {
    int varX,varY;
    int flag=0; //GAME END
    long  Rtimer=10000;
   final float rotMin=0,rotMAX=360;
    final int LevelOneTarget=10;
    int counter=0;
    int press=0;
    CountDownTimer NewTimer;
    LevelsData  db= new LevelsData(this,"LevelD.db",null,1);
    int clr;

    static int hscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_level_three);
        final  int temp3=db.checkHS(3);
        hscore=temp3;

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        final int Pwidth=dm.widthPixels;;
        final int Pheight=dm.heightPixels;
        final TextView timer=(TextView)findViewById(R.id.TIMgame);
        final TextView score=(TextView)findViewById(R.id.SCRgame);
        final ImageView bg=(ImageView)findViewById(R.id.gamescreen);
        final Button pause=(Button)findViewById(R.id.PASgame);
        final ImageButton circle=(ImageButton)findViewById(R.id.CIRClgame);
        final ImageButton halfcircle=(ImageButton)findViewById(R.id.Halfcircle);
        //for changing color of circle
        final GradientDrawable bgShape = (GradientDrawable) circle.getDrawable();
        final GradientDrawable bgShape2 = (GradientDrawable) halfcircle.getDrawable();
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
        clr=Color.GRAY;
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                levelText.setVisibility(View.GONE);
                AdRequest adRequest = new AdRequest.Builder().build();

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
                                {loss.start();
                                    target.setText("Game Over");
                                    target.setBackgroundColor(Color.RED);
                                    pause.setText("play again");
                                       /*  Intent i=new Intent(StartGame.this,StartGame.class);
                                         startActivity(i);
                                         finish();
                                         */

                                }
                                else{Win.start();
                                    pause.setText("Next Level >");
                                }

                                bgShape.setColor(Color.GRAY);
                                bgShape2.setColor(Color.parseColor("#39FF14"));
                                timer.setTextColor(Color.BLACK);
                                score.setTextColor(Color.BLACK);
                                bg.setBackgroundColor(Color.BLACK);
                                circle.setX(Ix);
                                circle.setY(Iy);
                                halfcircle.setX(Ix);
                                halfcircle.setY(Iy);
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
                        bgShape2.setColor(Color.parseColor("#39FF14"));
                        timer.setTextColor(Color.BLACK);
                        circle.setX(Ix);
                        circle.setY(Iy);
                        halfcircle.setX(Ix);
                        halfcircle.setY(Iy);
                        arrow.setVisibility(View.VISIBLE);
                        pause.setBackgroundColor(Color.RED);
                        score.setTextColor(Color.BLACK);
                        bg.setBackgroundColor(Color.BLACK);
                        NewTimer.cancel();
                    }

                }
                else
                {
                    if(counter<LevelOneTarget)
                    { Intent i=new Intent(LevelThree.this,LevelThree.class);
                        startActivity(i);
                        finish();
                    }
                    else{

                            Intent i = new Intent(LevelThree.this, LevelThree.class);
                            startActivity(i);
                            finish();

                    }


                }
            }
        });

        //for changing background color
        bg.setBackgroundColor(Color.BLACK);

        final int height=Pheight;
        final int width=Pwidth;

         halfcircle.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view ) {
                 if((flag!=1)&&(press%2)==1) {

                     mediaPlayer.start();
                     int CHeight = halfcircle.getHeight();
                     int CWidth = halfcircle.getWidth();
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
                             float rot = r.nextFloat()* (rotMin - rotMAX) + rotMin;
                             halfcircle.setX(x);
                             halfcircle.setY(y);
                             circle.setX(x);
                             halfcircle.setRotation(rot-rot%3);
                             circle.setY(y);

                             esp2 = 1;
                         }
                     }

                             bgShape.setColor(Color.WHITE);
                     bgShape2.setColor(Color.parseColor("#39FF14"));
                             timer.setTextColor(Color.parseColor("#39FF14"));
                             score.setTextColor(Color.parseColor("#39FF14"));

                             score.setText("Score : " + counter);
                             target.setTextColor(Color.parseColor("#39FF14"));




                     if (counter > hscore) {
                         while (hscore != counter) {
                             hscore++;
                         }
                         db.updateHS(3,hscore);
                     }

                 }
             }
         });




    }
}
