package com.example.inputcontrols3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ProgressDialog dialog;
    Handler pHandler;
    int progress;
    private static final int MAX_PROGRESS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView txtView = (TextView) findViewById(R.id.textRatingValue);
        final RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        final Button btnShow = (Button) findViewById(R.id.btnShow);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                txtView.setText(String.valueOf(rating));
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,String.valueOf(ratingBar.getRating()),
                        Toast.LENGTH_SHORT).show();
            }
        });

        final Button progress_button = (Button) findViewById(R.id.progessBar);
        progress_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new ProgressDialog(MainActivity.this);
                dialog.setTitle("Title");
                dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                dialog.setMax(MAX_PROGRESS);
                dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Hide",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,	int whichButton) {
                                /* User clicked Yes so do some stuff */
                            }
                        });
                dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,	int whichButton) {
                                /* User clicked No so do some stuff */
                            }
                        });
                progress = 0;
                dialog.show();
                dialog.setProgress(0);
                pHandler.sendEmptyMessage(0);
            }
        });

        pHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (progress >= MAX_PROGRESS) {
                    dialog.dismiss();
                } else {
                    progress=progress+5;
                    dialog.incrementProgressBy(5);
                    pHandler.sendEmptyMessageDelayed(0, 100);
                }
            }
        };

    }
}