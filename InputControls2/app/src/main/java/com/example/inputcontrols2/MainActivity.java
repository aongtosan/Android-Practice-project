package com.example.inputcontrols2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    DateFormat fmtDateAndTime = DateFormat.getDateTimeInstance();
    Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ToggleButton toggle = (ToggleButton) findViewById(R.id.toggleButton1);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                TextView text1 =(TextView)findViewById(R.id.textView);
                if (isChecked) {
                    text1.setText("Toggle ON");
                } else {
                    text1.setText("Toggle OFF");
                }
            }
        });

        SwitchCompat sw = (SwitchCompat) findViewById(R.id.switchButton);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                TextView text1 =(TextView)findViewById(R.id.textView);
                if (isChecked) {
                    text1.setText("Switch ON");
                } else {
                    text1.setText("Switch OFF");
                }
            }
        });

        Spinner spinner = (Spinner) findViewById(R.id.planets_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, R.layout.spinner_text);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button btnDate = (Button) findViewById(R.id.btnChangeDate);
        btnDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new DatePickerDialog(MainActivity.this, d,
                        myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        Button btnTime = (Button) findViewById(R.id.btnChangeTime);
        btnTime.setOnClickListener(new View.OnClickListener() {
            public  void onClick(View v) {
                new TimePickerDialog(MainActivity.this, t,
                        myCalendar.get(Calendar.HOUR_OF_DAY),
                        myCalendar.get(Calendar.MINUTE), true).show();
            }
        });

        updateLabel();
    }

    public void onToggleClicked(View view) {
        boolean on = ((ToggleButton) view).isChecked();
        TextView text1 =(TextView)findViewById(R.id.textView);

        if (on) {
            text1.setText("Toggle ON");
        } else {
            text1.setText("Toggle OFF");
        }
    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };

    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            myCalendar.set(Calendar.MINUTE, minute);
            updateLabel();
        }
    };

    private void updateLabel() {
        TextView text1 =(TextView)findViewById(R.id.textView);
        text1.setText(fmtDateAndTime.format(myCalendar.getTime()));
    }

}