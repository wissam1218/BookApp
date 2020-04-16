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
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Button PDFbutton;
    private Button postTest;
    private Button preTest;
    private EditText lessonNum;
    private EditText classNum;

    // Variables for the class and lesson number
    public static int classNumber;
    public static int lessonNumber;

    // Variables to save class and lesson
    // these are needed so the user does not have
    // re-enter numbers betweeen activity cycles
    public static String savedClass = "" ;
    public static String savedLesson = "" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check permission when application is first loaded
        if (!hasPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE})) {
            // Ask permission
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 111);
        }

        lessonNum = findViewById(R.id.lessonNum);
        classNum = findViewById(R.id.classNum);
        PDFbutton = findViewById(R.id.PDFButton);
        postTest = findViewById(R.id.PostButton);
        preTest = findViewById(R.id.preButton);

        // Restore class number
        if(!(savedClass.isEmpty())){
            classNum.setText(savedClass);
        }
        classNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                classNumber = Integer.parseInt(classNum.getText().toString());
                savedClass = classNum.getText().toString();
            }
        });

        // Restore lesson number
        if(!(savedLesson.isEmpty())){
            lessonNum.setText(savedLesson);
        }

        lessonNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lessonNumber = Integer.parseInt(lessonNum.getText().toString());
                savedLesson = lessonNum.getText().toString();
            }
        });

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

    // Function that checks for storage permissions
    // If permissions are not granted a dialog will appear
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
