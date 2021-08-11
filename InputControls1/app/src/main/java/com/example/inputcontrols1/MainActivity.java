package com.example.inputcontrols1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get a reference to the AutoCompleteTextView in the layout
        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autocompleteView);
        // Get the string array
        String[] countries = getResources().getStringArray(R.array.countries_array);
        // Create the adapter and set it to the AutoCompleteTextView
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countries);
        textView.setAdapter(adapter);
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();
        final TextView txtView=(TextView)findViewById(R.id.textView);

        switch(view.getId()) {
            case R.id.checkbox_meat:
                if (checked){
                    txtView.setText(" Meat ");
                }else{
                    txtView.setText(" No Meat ");
                }
                break;
            case R.id.checkbox_cheese:
                if (checked){
                    txtView.setText(" Cheese ");
                }else{
                    txtView.setText(" No Cheese ");
                }
                break;
        }
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        final TextView txtView=(TextView)findViewById(R.id.textView);

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_pirates:
                if (checked)
                    txtView.setText(" Pirates ! ");
                break;
            case R.id.radio_ninjas:
                if (checked)
                    txtView.setText(" Ninjas ! ");
                break;
        }
    }
}