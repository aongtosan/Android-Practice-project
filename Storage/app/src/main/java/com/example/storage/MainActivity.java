package com.example.storage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button read_button1 = (Button) findViewById(R.id.read_button1);
        read_button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferences shared_pref = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                String tmp_str1 = shared_pref.getString("shrPref1", "No Pref");
                String tmp_str2 = shared_pref.getString("shrPref2", "No Pref");

                final TextView text1_1=(TextView)findViewById(R.id.text1_1);
                final TextView text1_2=(TextView)findViewById(R.id.text1_2);
                text1_1.setText(tmp_str1);
                text1_2.setText(tmp_str2);
            }
        });

        final Button write_button1 = (Button) findViewById(R.id.write_button1);
        write_button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferences shared_pref = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                SharedPreferences.Editor editor = shared_pref.edit();
                editor.putString("shrPref1", "Plus");
                editor.putString("shrPref2", "Pie");
                editor.apply();
            }
        });

        final Button read_button2 = (Button) findViewById(R.id.read_button2);
        read_button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String filename = "cs355.txt";
                String inputString;
                try {
                    BufferedReader inputReader = new BufferedReader(new InputStreamReader(openFileInput(filename)));
                    StringBuffer stringBuffer = new StringBuffer();
                    while ((inputString = inputReader.readLine()) != null) {
                        stringBuffer.append(inputString + "\n");
                    }

                    final TextView text2=(TextView)findViewById(R.id.text2);
                    text2.setText(stringBuffer.toString());

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        final Button write_button2 = (Button) findViewById(R.id.write_button2);
        write_button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String filename = "cs355.txt";
                String string = "Private Storage";
                FileOutputStream outputStream;

                try {
                    outputStream = openFileOutput(filename, MODE_PRIVATE);
                    outputStream.write(string.getBytes());
                    outputStream.close();
                    final TextView text2=(TextView)findViewById(R.id.text2);
                    text2.setText("Save to : " + getFileStreamPath(filename).toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        final Button read_button3 = (Button) findViewById(R.id.read_button3);
        read_button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String filename2 = "cs355.txt";
                String inputString2;
                String storageState = Environment.getExternalStorageState();
                if (storageState.equals(Environment.MEDIA_MOUNTED)) {
                    try {
                        //File file = new File(getExternalFilesDir(null), filename2);
                        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), filename2);
                        //File file = new File("/storage/extSdCard", filename2);
                        BufferedReader inputReader2 = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                        StringBuffer stringBuffer2 = new StringBuffer();
                        while ((inputString2 = inputReader2.readLine()) != null) {
                            stringBuffer2.append(inputString2 + "\n");
                        }

                        final TextView text3=(TextView)findViewById(R.id.text3);
                        text3.setText(stringBuffer2.toString());
                        inputReader2.close();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        final Button write_button3 = (Button) findViewById(R.id.write_button3);
        write_button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String filename2 = "cs355.txt";
                String string2 = "Internal and External Storages";
                FileOutputStream outputStream2;
                String storageState = Environment.getExternalStorageState();
                if (storageState.equals(Environment.MEDIA_MOUNTED)) {
                    try {
                        //File file = new File(getExternalFilesDir(null), filename2);
                        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), filename2);
                        //File file = new File("/storage/extSdCard", filename2);
                        outputStream2 = new FileOutputStream(file);
                        outputStream2.write(string2.getBytes());
                        outputStream2.close();

                        final TextView text3=(TextView)findViewById(R.id.text3);
                        text3.setText("Save to : "+file.getAbsolutePath().toString());

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }
}