package com.example.bookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.icu.util.Output;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;

import java.io.FileWriter;
import java.io.IOException;

import static com.example.bookapp.postViewer.mScore;
import static com.example.bookapp.postViewer.questionNum;
import static com.example.bookapp.postViewer.testNum;

import static com.example.bookapp.questions.QA;

public class resultActivity extends AppCompatActivity {

    public static File root = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "reports");

    public static File file = new File(root, "/postReportCard.txt");
    public static BufferedWriter out;

    static {
        try {
            out = new BufferedWriter((new FileWriter(file, true)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    TextView mGrade, mFinalScore;
    Button mRetryButton, nextBtn, saveBtn, buildBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mGrade = findViewById(R.id.grade);
        mFinalScore = findViewById(R.id.outOf);
        mRetryButton = findViewById(R.id.retry);
        nextBtn = findViewById(R.id.nextBtn);
        saveBtn =  findViewById(R.id.saveBtn);
        buildBtn = findViewById(R.id.buildBtn);

        buildBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    out.close();
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
                mScore = 0;
                Intent intent = new Intent(resultActivity.this, postViewer.class);
                startActivity(intent);
            }
        });

        mRetryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScore = 0;
                questionNum = 0;
                Intent intent = new Intent(resultActivity.this, postViewer.class);
                startActivity(intent);
            }
        });
        mFinalScore.setText("You scored " + mScore);
    }
    public static void save() throws IOException{

        out.write("post test number "+testNum+ " score is " + mScore + "/"+(QA+1));
        out.newLine();


    }
}