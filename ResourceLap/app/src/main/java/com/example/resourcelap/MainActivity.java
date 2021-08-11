package com.example.resourcelap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Resources res = getResources();
        int color = res.getColor(R.color.opaque_red);
        final String[] array = res.getStringArray(R.array.Heroes);
        final TextView txt1 = (TextView)findViewById(R.id.textView1);
        txt1.setText(array[1]);
        txt1.setTextColor(color);
        //ImageView img = (ImageView)findViewById(R.id.imageView1);
        //img.setImageResource(R.drawable.lowpolyblacksmith);
        VideoView mVideoView = (VideoView) findViewById(R.id.videoView);
        mVideoView.setVideoURI(Uri.parse("android.resource://" +
                getPackageName() + "/" + R.raw.blacksmithshop));
        mVideoView.setMediaController(new MediaController(this));
        mVideoView.requestFocus();
        //getWindow().setBackgroundDrawableResource(R.drawable.field);
    }
}
