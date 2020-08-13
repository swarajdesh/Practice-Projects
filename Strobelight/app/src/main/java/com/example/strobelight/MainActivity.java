package com.example.strobelight;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    SeekBar seek;
    Switch s;
    int blinkInterval;
    private boolean isOn = false;
    boolean btnenabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        s = findViewById(R.id.btn);
        seek = findViewById(R.id.seek);
        seek.setMax(10); //max 10 seconds the of seekbar

        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(ContextCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CAMERA},10);
                }
                else if(s.isChecked()){
                    startblink();
                }
            }
        });

    }
    private void startblink(){
        Timer t = new Timer();
        if(!s.isChecked()){
            t.cancel();
            flashlightOff();
            isOn = false;
            return;
        }
        blinkInterval=1000/(seek.getProgress()==0?1:seek.getProgress());

        t.schedule(new TimerTask() {
            @Override
            public void run() {
                if (isOn) {
                    flashlightOff();
                    isOn = false;
                } else {
                    flashLightOn();
                    isOn = true;
                }
                startblink();
            }
        },blinkInterval);
    }
    private void flashlightOff(){
        CameraManager cm = (CameraManager)getSystemService(Context.CAMERA_SERVICE);
        try{
            String cameraId=cm.getCameraIdList()[0];
            cm.setTorchMode(cameraId,false);
        }catch(Exception e){}
    }
    private void flashLightOn(){
        CameraManager cm = (CameraManager)getSystemService(Context.CAMERA_SERVICE);
        try{
            String cameraId=cm.getCameraIdList()[0];
            cm.setTorchMode(cameraId,true);
        }catch (Exception e){}
    }

}
