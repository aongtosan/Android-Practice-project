package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.IOException;

import java.text.DecimalFormat;

public class main_activity extends AppCompatActivity {
     private  String file ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        initial_state();
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        TextView header  = (TextView) findViewById(R.id.Assigment1);
        header.setTextSize(newConfig.fontScale*32);
        TextView row1 = (TextView) findViewById(R.id.Sales);
        row1.setTextSize(newConfig.fontScale*32);
        TextView row2 = (TextView) findViewById(R.id.Share_percentage);
        row2.setTextSize(newConfig.fontScale*32);
        TextView row3 = (TextView) findViewById(R.id.Sales_minus_share);
        row3.setTextSize(newConfig.fontScale*32);
        TextView row4 = (TextView) findViewById(R.id.Sales_share);
        row4.setTextSize(newConfig.fontScale*32);
        EditText editSales = (EditText) findViewById(R.id.editSales);
        editSales.setTextSize(newConfig.fontScale*32);
        EditText editShare_percentage = (EditText) findViewById(R.id.editShare_percentage);
        editShare_percentage.setTextSize(newConfig.fontScale*32);
        EditText editSales_share = (EditText) findViewById(R.id. editSales_share);
        editSales_share.setTextSize(newConfig.fontScale*32);
        EditText  editSales_minus_share = (EditText) findViewById(R.id.  editSales_minus_share);
        editSales_minus_share.setTextSize(newConfig.fontScale*32);
        Button button = (Button) findViewById(R.id.Calculate);
        button.setTextSize(newConfig.fontScale*32);
    }

    public void write_information(View view)  {
        file = "record_order.txt";
        Calculate(view);
        try{
        EditText editSales = (EditText) findViewById(R.id.editSales);
        EditText editShare_percentage = (EditText) findViewById(R.id.editShare_percentage);
        EditText editSales_share = (EditText) findViewById(R.id. editSales_share);
        EditText  editSales_minus_share = (EditText) findViewById(R.id.  editSales_minus_share);
        String col1 = editSales.getText().toString();
        String col2 = editShare_percentage.getText().toString();
        String col3 = editSales_share.getText().toString();
        String col4 = editSales_minus_share.getText().toString();
           FileOutputStream outputStream = openFileOutput(file, MODE_APPEND);
           String data = col1+" "+col2+" "+col3+" "+col4+"\n";
           outputStream.write(data.getBytes());
           outputStream.close();
       }catch (FileNotFoundException  e){
           Toast.makeText(this, "Not found", Toast.LENGTH_SHORT).show();
       }catch (IOException e){
           Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
       }

    }
    public void show_History(View view) {
        Intent intent = new Intent(main_activity.this, history.class);
        startActivity(intent);
    }
    private void Calculate(View view){
        EditText editSales = (EditText) findViewById(R.id.editSales);
        String sales_EA = editSales.getText().toString();
        Double EA = Double.parseDouble(sales_EA);
        EditText editShare_percentage = (EditText) findViewById(R.id.editShare_percentage);
        String Share_per = editShare_percentage.getText().toString();
        Double Per = Double.parseDouble(Share_per)/100;
        setFormatter(EA,EA*Per,EA-EA*Per);
    }
    private void setFormatter(Double EA,Double comission,Double remaining){
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        String formatted1 = formatter.format(EA);
        String formatted3 = formatter.format(comission);
        String formatted4 = formatter.format(remaining);
        EditText editSales = (EditText) findViewById(R.id.editSales);
        EditText editSales_share = (EditText) findViewById(R.id. editSales_share);
        EditText  editSales_minus_share = (EditText) findViewById(R.id.  editSales_minus_share);
        editSales.setText(formatted1);
        editSales_share.setText(formatted4);
        editSales_minus_share.setText(formatted3);
    }
    private void initial_state(){

        EditText editSales = (EditText) findViewById(R.id.editSales);
        EditText editShare_percentage = (EditText) findViewById(R.id.editShare_percentage);
        EditText editSales_share = (EditText) findViewById(R.id. editSales_share);
        EditText  editSales_minus_share = (EditText) findViewById(R.id.  editSales_minus_share);
        editSales.setText("0");
        editSales_minus_share.setText("0");
        editSales_share.setText("0");
        editShare_percentage.setText("0");
    }

}