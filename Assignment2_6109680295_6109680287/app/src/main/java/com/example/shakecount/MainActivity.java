package com.example.shakecount;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;
    private static final int POLL_INTERVAL = 500;
    private static final int INTERVAL_MS = 20;
    private Handler hdr = new Handler();
    private PowerManager.WakeLock wl;
    SensorInfo sensor_info = new SensorInfo();
    SensorInfo previous_sensor_info = new SensorInfo();
    private static final int shake_threshold = 10;
    private int shake_count = 0;
    private long previousTimestamp;
    Boolean shown_dialog = false;

    private Runnable pollTask = new Runnable() {
        public void run() {
            showDialog();
            hdr.postDelayed(pollTask, POLL_INTERVAL);
        }
    };

    @SuppressLint("InvalidWakeLockTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wl = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "Sensors Info");
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy){
        // TO DO
    }

    public void onSensorChanged(SensorEvent event){
        int type = event.sensor.getType();
        if (type == Sensor.TYPE_ACCELEROMETER) {
            sensor_info.accX=event.values[0];
            sensor_info.accY=event.values[1];
            sensor_info.accZ=event.values[2];
        }
    }

    public void showDialog() {
        TextView text1 = (TextView) findViewById(R.id.text);
        LinearLayout layout = (LinearLayout) findViewById(R.id.linear_layout);
        String message1 = "";

        int num = 0;
        final AlertDialog.Builder Dialog = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater factory = LayoutInflater.from(MainActivity.this);
        String message = String.format(" %1$d", num);
        //String[] result = getResources().getStringArray(R.array.show);
        Random rand = new Random();
        String result = "";
        if( (Math.abs(sensor_info.accX-previous_sensor_info.accX)>shake_threshold) || (Math.abs(sensor_info.accY-previous_sensor_info.accY)>shake_threshold) || (Math.abs(sensor_info.accZ-previous_sensor_info.accZ)>shake_threshold) ) {
            final long now = System.currentTimeMillis();
            if (now - previousTimestamp > INTERVAL_MS) {
               num = rand.nextInt(7);
                if(num == 0){
                    //message = result[0];
                    final View view = factory.inflate(R.layout.aha, null);
                    Dialog.setView(view);
                }
                else if(num == 1){
                    //message = result[1];
                    final View view = factory.inflate(R.layout.o_zone, null);
                    Dialog.setView(view);
                }
                else if(num == 2){
                    //message = result[2];
                    final View view = factory.inflate(R.layout.rick_astley, null);
                    Dialog.setView(view);
                }
                else if(num == 3){
                    //message = result[3];
                    final View view = factory.inflate(R.layout.village_people, null);
                    Dialog.setView(view);
                }
               else if(num == 4){
                    //message = result[4];
                    final View view = factory.inflate(R.layout.michael_jackson, null);
                    Dialog.setView(view);
                }
                else if(num == 5){
                    //message = result[5];
                    final View view = factory.inflate(R.layout.thecranberries, null);
                    Dialog.setView(view);
                }
                else if(num == 6){
                    //message = result[6];
                    final View view = factory.inflate(R.layout.scatman, null);
                    Dialog.setView(view);
                }

                if(!shown_dialog) {
                    shown_dialog = true;
                    //Dialog.setMessage(message);

                    Dialog.setPositiveButton("Close",  new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            shown_dialog = false;
                        }
                    });
                    Dialog.show();
                }
                previousTimestamp = now;
            }
            previous_sensor_info.accX = sensor_info.accX;
            previous_sensor_info.accY = sensor_info.accY;
            previous_sensor_info.accZ = sensor_info.accZ;
        }//end if
    }

    @SuppressLint("WakelockTimeout")
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);

        if (!wl.isHeld()) {
            wl.acquire();
        }
        hdr.postDelayed(pollTask, POLL_INTERVAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);

        if (wl.isHeld()) {
            wl.release();
        }
        hdr.removeCallbacks(pollTask);
    }

    static class SensorInfo{
        float accX, accY, accZ;
    }

}//end MainActivity