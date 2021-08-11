package com.example.runingtester;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void Play(View e){
        final MediaPlayer mp =  MediaPlayer.create(this,R.raw.click_play);
        mp.start();
        Intent intent = new Intent(MainActivity.this,UnityHolder.class);
        startActivity(intent);

    }

}