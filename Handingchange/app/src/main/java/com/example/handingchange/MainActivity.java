package com.example.handingchange;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import android.content.res.Configuration;
import android.widget.Toast;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        final TextView txtView=(TextView)findViewById(R.id.textView);
        txtView.setTextSize(newConfig.fontScale*32);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            txtView.setText(txtView.getText() + " landscape");
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
            } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            txtView.setText(txtView.getText() + " portrait");
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
             }


        }
}
