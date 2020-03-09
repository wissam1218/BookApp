package com.example.bookapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button PDFbutton;

    private Button postTest;
    private Button preTest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!hasPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE})) {
            // ask permission
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 111);
        }
        PDFbutton = (Button) findViewById(R.id.PDFButton);
        postTest = (Button) findViewById(R.id.PostButton);
        preTest = (Button) findViewById(R.id.preButton);

        preTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPre();
            }
        });

        postTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPost();
            }
        });


        PDFbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPDF();
            }
        });
    }
    private void openPDF(){
        Intent i = new Intent(MainActivity.this,PDFViewer.class);
        startActivity(i);
    }
    private void openPost(){
       Intent i = new Intent(MainActivity.this,postViewer.class);
      startActivity(i);
    }
    private void openPre(){
        Intent i = new Intent(MainActivity.this,preViewer.class);
        startActivity(i);
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
}
