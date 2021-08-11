package com.example.multiactivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageButton btn1 =(ImageButton)findViewById(R.id.camera_btn);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(intent.resolveActivity(getPackageManager())!= null){
                    startActivityForResult(intent, 0);
                }
            }
        });

        final ImageButton btn2 =(ImageButton)findViewById(R.id.video_btn);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
                intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 10);
                if(intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, 1);
                }
            }
        });

        final ImageButton btn3 =(ImageButton)findViewById(R.id.gallery_btn);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                if(intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(Intent.createChooser(intent,
                            "Select photo from"), 2);
                }
            }
        });

        final ImageButton btn4 = (ImageButton) findViewById(R.id.audio_btn);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
                if(intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, 3);
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode== 0 && resultCode == Activity.RESULT_OK) {
            try {
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                ImageButton btn1=(ImageButton)findViewById(R.id.camera_btn);
                btn1.setImageBitmap(imageBitmap);
                TextView txt = (TextView) findViewById(R.id.helloMsg);
                txt.setText("Camera");
            } catch (Exception e) {
                Log.e("Log", "Error from Camera Activity");
            }
        }

        if (requestCode== 1 && resultCode == Activity.RESULT_OK && data!=null) {
            try {
                Uri videoUri = data.getData();
                VideoView videoView=(VideoView)findViewById(R.id.videoView);
                videoView.getLayoutParams().height = 400;
                ImageView imageView=(ImageView)findViewById(R.id.imageView2);
                imageView.getLayoutParams().height = 0;
                videoView.setVideoURI(videoUri);
                videoView.setMediaController(new MediaController(this));
                videoView.requestFocus();
                TextView txt = (TextView) findViewById(R.id.helloMsg);
                txt.setText("Video : "+videoUri);
            } catch (Exception e) {
                Log.e("Log", "Error from Video Activity");
            }
        }

        if (requestCode== 2 && resultCode == Activity.RESULT_OK && data!=null) {
            try {
                Uri uri = data.getData();
                try {
                    VideoView videoView=(VideoView)findViewById(R.id.videoView);
                    videoView.getLayoutParams().height = 0;
                    ImageView imageView=(ImageView)findViewById(R.id.imageView2);
                    imageView.getLayoutParams().height = 400;
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(

                            this.getContentResolver(), uri);

                    imageView.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                TextView txt = (TextView) findViewById(R.id.helloMsg);
                txt.setText("Gallery : "+uri);
            } catch (Exception e) {
                Log.e("Log", "Error from Gallery Activity");
            }
        }

        if (requestCode== 3 && resultCode == Activity.RESULT_OK && data!=null) {
            try {
                Uri uri = data.getData();
                try {
                    MediaPlayer mPlayer = new MediaPlayer();
                    mPlayer.setDataSource(getApplicationContext(), uri);
                    mPlayer.prepare();
                    mPlayer.start();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                TextView txt = (TextView) findViewById(R.id.helloMsg);
                txt.setText("Audio : "+uri);
            } catch (Exception e) {
                Log.e("Log", "Error from Audio Activity");
            }
        }


    }
}