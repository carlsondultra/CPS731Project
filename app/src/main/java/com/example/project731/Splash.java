package com.example.project731;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {

    MediaPlayer mdSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mdSong = new MediaPlayer();
        mdSong = MediaPlayer.create(this,R.raw.love);
        mdSong.start();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                finish();
                mdSong.stop();
                startActivity(new Intent(Splash.this,LoginScreenActivity.class));
            }
        };
        //Setting timer for how long splash screen stays up
        Timer opening = new Timer();
        opening.schedule(task, 5000);
    }
}