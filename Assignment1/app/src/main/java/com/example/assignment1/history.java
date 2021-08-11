package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;

import java.util.ArrayList;


public class history extends AppCompatActivity {
    private ArrayList<String> row1 ;
    private ArrayList<String> row2 ;
    private ArrayList<String> row3 ;
    private ArrayList<String> row4 ;
    private ArrayList<String> core ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_history);
        show_inform();
    }
    private void show_inform (){
        ListView column1 = (ListView) findViewById(R.id.lv1) ;
        ListView column2 = (ListView) findViewById(R.id.lv2) ;
        ListView column3 = (ListView) findViewById(R.id.lv3) ;
        ListView column4 = (ListView) findViewById(R.id.lv4) ;
        row1 = new ArrayList<>();
        row2 = new ArrayList<>();
        row3 = new ArrayList<>();
        row4 = new ArrayList<>();
        core = new ArrayList<>();
        String data;
        String file = "record_order.txt";
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(openFileInput(file)));
            while ((data=input.readLine())!=null){
                core.add(data);
            }
            input.close();
            for(int i=core.size()-1 ;i>=0;i--){
                String[] data_row = core.get(i).split(" ");
                row1.add(data_row[0]);
                row2.add(data_row[1]);
                row3.add(data_row[2]);
                row4.add(data_row[3]);
            }
        ArrayAdapter<String> set1 = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,row1);
        column1.setAdapter(set1);
        ArrayAdapter<String> set2 = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,row2);
        column2.setAdapter(set2);
        ArrayAdapter<String> set3 = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,row3);
        column3.setAdapter(set3);
        ArrayAdapter<String> set4 = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,row4);
        column4.setAdapter(set4);
        }catch (FileNotFoundException e){
            Toast.makeText(this, "There are no record file", Toast.LENGTH_SHORT).show();
        }catch (IOException e){
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

}