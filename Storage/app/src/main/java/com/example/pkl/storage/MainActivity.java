package com.example.pkl.storage;

import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.storage.R;

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

        final Button button1 = (Button) findViewById(R.id.read_button1);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferences shared_pref = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                String tmp_str1 = shared_pref.getString("shrPref1", "No Pref");
                String tmp_str2 = shared_pref.getString("shrPref2", "No Pref");

                final TextView text1=(TextView)findViewById(R.id.text1_1);
                final TextView text2=(TextView)findViewById(R.id.text1_2);
                text1.setText(tmp_str1);
                text2.setText(tmp_str2);
            }
        });

        final Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferences shared_pref = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                SharedPreferences.Editor editor = shared_pref.edit();
                editor.putString("shrPref1", "Hobbit");
                editor.putString("shrPref2", "Mafia");
                editor.commit();
            }
        });

        final Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                    String filename = "cs355.txt";
                    String inputString;
                    try {
                        BufferedReader inputReader = new BufferedReader(new InputStreamReader(openFileInput(filename)));
                        StringBuffer stringBuffer = new StringBuffer();
                        while ((inputString = inputReader.readLine()) != null) {
                            stringBuffer.append(inputString + "\n");
                        }

                        final TextView text3=(TextView)findViewById(R.id.text3);
                        text3.setText(stringBuffer.toString());

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

            }
        });

        final Button button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String filename = "cs355.txt";
                String string = "Hello world!";
                FileOutputStream outputStream;

                try {
                    outputStream = openFileOutput(filename, MODE_APPEND);
                    outputStream.write(string.getBytes());
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        final Button button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
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

                        final TextView text4=(TextView)findViewById(R.id.text4);
                        text4.setText(stringBuffer2.toString());
                        inputReader2.close();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        final Button button6 = (Button) findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String filename2 = "cs355.txt";
                String string2 = "Hello world!";
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

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }
}
