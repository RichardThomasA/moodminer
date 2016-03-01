package com.richardthomas.moodminer.moodminer;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class dashboard extends AppCompatActivity implements SensorEventListener{

    SensorManager sensorManager;
    Sensor accelerometer;
    private float last_x,last_y,last_z;
    private long lastupdate;
    private static final int SHAKE_THRESHOLD=600;
    TextView txt_acc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        Intent intent=getIntent();
        String message =intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        sensorManager= (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this,accelerometer,sensorManager.SENSOR_DELAY_NORMAL);

        txt_acc= (TextView) findViewById(R.id.textView9);//for displaying accelerometer readings



    }



    @Override
    public void onSensorChanged(SensorEvent event) {

        Sensor mysensor=event.sensor;

        if (mysensor.getType() == Sensor.TYPE_ACCELEROMETER){
            float x =event.values[0];
            float y =event.values[1];
            float z =event.values[2];

            long curtime=System.currentTimeMillis();
            if((curtime-lastupdate)>100){
                long difftime=(curtime-lastupdate);
                lastupdate =curtime;

                float speed=Math.abs(x+y+z -last_x -last_y -last_z)/difftime*10000;

                if (speed>SHAKE_THRESHOLD) {


                    last_x = x;
                    last_y = y;
                    last_z = z;

                    //String s_x =Float.toString(last_x);
                    //String s_y =Float.toString(last_y);
                    //String s_z =Float.toString(last_z);

                    txt_acc.setText("x:" + last_x + ",y:" + last_y + ",z:" + last_z);
                }

            }
        }
    }

    @Override
    public void onAccuracyChanged(android.hardware.Sensor sensor, int accuracy) {

    }
}
