package com.example.cui.myfirstapp;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by CUI on 2016/10/26.
 */

public class Accelerometer extends AppCompatActivity implements SensorEventListener {
    Sensor accelerometer;
    SensorManager sm;
    TextView accerlation;
    Double tmpMaxX = 0.00;
    Double tmpMaxY = 0.00;
    Double tmpMaxZ = 0.00;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);

        accelerometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        accerlation = (TextView) findViewById(R.id.acceleration);



        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Double ValueX = (double) (Math.round(event.values[0] * 100) / 100.0);
        Double ValueY = (double) (Math.round(event.values[1] * 100) / 100.0);
        Double ValueZ = (double) (Math.round(event.values[1] * 100) / 100.0);
        if (ValueX > tmpMaxX) {
            tmpMaxX = ValueX;
        }
        if (ValueY > tmpMaxY) {
            tmpMaxY = ValueY;
        }
        if (ValueZ > tmpMaxZ) {
            tmpMaxZ = ValueZ;
        }
        accerlation.setText("X:" + ValueX +
                "\nY:" + ValueY +
                "\nZ:" + ValueZ +
                "\nMaxX:" + tmpMaxX +
                "\nMaxY:" + tmpMaxY +
                "\nMaxZ:" + tmpMaxZ);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Accelerometer Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}