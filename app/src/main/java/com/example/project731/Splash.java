package com.example.project731;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {

    ImageView ivTop, ivHeart, ivBeat, ivBottom;
    MediaPlayer mdSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ivTop = findViewById(R.id.iv_top);
        ivHeart = findViewById(R.id.iv_heart);
        ivBeat = findViewById(R.id.iv_beat);
        ivBottom = findViewById(R.id.iv_bottom);


        Animation animation1 = AnimationUtils.loadAnimation(this,R.anim.top_wave);
        ivTop.setAnimation(animation1);

        //Initialize object animator
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(ivHeart,
                PropertyValuesHolder.ofFloat("scaleX", 1.2f),
                PropertyValuesHolder.ofFloat("scaleY", 1.2f)
        );

        //Set duration
        objectAnimator.setDuration(500);
        //Set repeat count
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        //setRepeat mode
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        //Start animator
        objectAnimator.start();


        Animation animation2 = AnimationUtils.loadAnimation(this,R.anim.bottom_wave);
        ivBottom.setAnimation(animation2);

        // Code to make music play while splash screen is shown.
        mdSong = new MediaPlayer();
        mdSong = MediaPlayer.create(this,R.raw.love);
        mdSong.start();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                finish();
                mdSong.stop();
                startActivity(new Intent(Splash.this,FirebaseLoginScreenActivity.class));
            }
        };
        //Setting timer for how long splash screen stays up, in this case 5 seconds.
        Timer opening = new Timer();
        opening.schedule(task, 5000);
    }


}