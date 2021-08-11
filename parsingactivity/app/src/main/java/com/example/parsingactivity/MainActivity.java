package com.example.parsingactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

@Override
protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            Button button = (Button) findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                  Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                   EditText edt = (EditText)findViewById(R.id.editText);
                   String value = edt.getText().toString();
                   intent.putExtra("extra", value);
                  startActivity(intent);
                 }
            });
        }
}
