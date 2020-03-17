package com.example.bookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static com.example.bookapp.preViewer.PreScore;
import static com.example.bookapp.preViewer.questionNum;
import static com.example.bookapp.preViewer.testNum;
import static com.example.bookapp.questions.QA;

public class resultActivityPre extends AppCompatActivity {

    public static File root = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "reports");
    public static File prefile = new File(root, "/preReportCard.txt");
    public static BufferedWriter preout;
    static {
        try {
            preout = new BufferedWriter((new FileWriter(prefile, true)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    TextView mGrade, mFinalScore;
    Button mRetryButton, nextBtn, saveBtn, buildBtn, menuBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mGrade = findViewById(R.id.grade);
        mFinalScore = findViewById(R.id.score);
        mRetryButton = findViewById(R.id.retry);
        nextBtn = findViewById(R.id.nextBtn);
        saveBtn =  findViewById(R.id.saveBtn);
        buildBtn = findViewById(R.id.buildBtn);
        menuBtn = findViewById(R.id.menuBtn);

        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(resultActivityPre.this, MainActivity.class);
                startActivity(i);
            }
        });

        buildBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    preout.close();
                    PreScore = 0;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    save();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testNum++;
                questionNum = 0;
                PreScore = 0;
                Intent intent = new Intent(resultActivityPre.this, preViewer.class);
                startActivity(intent);
            }
        });

        mRetryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreScore = 0;
                questionNum = 0;
                Intent intent = new Intent(resultActivityPre.this, preViewer.class);
                startActivity(intent);
            }
        });
        mFinalScore.setText("You scored " + PreScore);
    }
    public static void save() throws IOException{
        preout.write("post test number "+testNum+ " score is " + PreScore + "/"+(QA+1));
        preout.newLine();
    }
}