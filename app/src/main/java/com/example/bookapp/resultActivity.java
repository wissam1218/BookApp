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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import static com.example.bookapp.postViewer.mScore;
import static com.example.bookapp.postViewer.questionNum;
import static com.example.bookapp.postViewer.testNum;

public class resultActivity extends AppCompatActivity {
    TextView mGrade, mFinalScore;
    Button mRetryButton, nextBtn, saveBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mGrade = (TextView)findViewById(R.id.grade);
        mFinalScore = (TextView)findViewById(R.id.outOf);
        mRetryButton = (Button)findViewById(R.id.retry);
        nextBtn = (Button) findViewById(R.id.nextBtn);
        saveBtn = (Button) findViewById(R.id.saveBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save(resultActivity.this, "postReportCard.txt", mScore);

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
    public void save(Context context, String fileName, int score) {
        try {
            File root = new File(Environment.getExternalStorageDirectory(), "reports");
            File file = new File(root, fileName);
            FileWriter writer = new FileWriter(file);
            writer.write("postTest number "+testNum+" result is " +score);
            writer.flush();
            writer.close();
            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}