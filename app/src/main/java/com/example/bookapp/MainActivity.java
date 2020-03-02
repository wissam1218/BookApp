package com.example.bookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

}
