package com.example.parsingactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView txt = (TextView) findViewById(R.id.TextView3);
        String extra = getIntent().getStringExtra("extra");
        txt.setText(extra);
    }
}
