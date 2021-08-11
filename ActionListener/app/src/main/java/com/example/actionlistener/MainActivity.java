package com.example.actionlistener;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView note = (TextView) findViewById(R.id.Note);
        final Button button_1 = (Button)findViewById(R.id.button1); // bind ข้อมูลของปุ่มใน .xml ที่เป็น UI เข้ากับ java
        final Button button_2 = (Button)findViewById(R.id.button2); // bind ข้อมูลของปุ่มใน .xml ที่เป็น UI เข้ากับ java
        final Button button_3 = (Button)findViewById(R.id.button3); // bind ข้อมูลของปุ่มใน .xml ที่เป็น UI เข้ากับ java
        final EditText edt = (EditText)findViewById(R.id.editText);
        edt.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) { // keyEvent คือ การกดปุ่ม / ปล่อยปุ่ม
                if(event.getAction()==KeyEvent.ACTION_DOWN){
                    if(keyCode==KeyEvent.KEYCODE_ENTER){
                        note.setText(note.getText()+" | "+edt.getText());
                        edt.setText("");
                    }
                }
                return false;
            }
        });
    }

    public void showNewDate(View v){
        final TextView txt3 = (TextView) findViewById(R.id.date_info);
        txt3.setText("Time :"+new Date().toString() +" from Button2");
    }
    public void clearText(View v){
        final TextView txt2 = (TextView) findViewById(R.id.Note);
        final TextView txt3 = (TextView) findViewById(R.id.date_info);
        txt2.setText("");
        txt3.setText("");
    }
}
