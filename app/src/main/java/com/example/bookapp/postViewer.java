package com.example.bookapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.nio.Buffer;
import java.util.ArrayList;

import static com.example.bookapp.MainActivity.hasPermissions;

import static java.lang.Math.floor;

public class postViewer extends AppCompatActivity  {
    int q = 0;
    Button choice1,choice2,choice3,choice4;
    TextView score,question;
    private questions mQuestions = new questions();
    private String mAnswer;
    private int mScore = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_viewer);
        choice1 = findViewById(R.id.choice1);
        choice2 = findViewById(R.id.choice2);
        choice3 = findViewById(R.id.choice3);
        choice4 = findViewById(R.id.choice4);
        score = findViewById(R.id.score);
        question = findViewById(R.id.question);
//        if (!hasPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE})) {
//            // Permission ask
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 111);
//        }
        loadQuestions();
        updateQuestion(q);
        score.setText("Score: " + mScore);
        choice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q++;
                // .getText().toString().substring(1).equalsIgnoreCase(mAnswer.substring(1))
                if(choice1.getText().toString().substring(1).equalsIgnoreCase(mAnswer.substring(1))){
                   mScore++;
                    score.setText("Score: " + mScore);
                    updateQuestion(q);
                } else{
                    gameOver();
                }
            }
        });
        choice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q++;
                if(choice2.getText().toString().substring(1).equalsIgnoreCase(mAnswer.substring(1))){
                    mScore++;
                    score.setText("Score: " + mScore);
                    updateQuestion(q);
                } else{
                    gameOver();
                }

            }
        });
        choice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                q++;
                if(choice3.getText().toString().substring(1).equalsIgnoreCase(mAnswer.substring(1))){
                    mScore++;
                    score.setText("Score: " + mScore);
                    updateQuestion(q);
                } else{
                    gameOver();
                }
            }
        });
        choice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q++;
                if(choice4.getText().toString().substring(1).equalsIgnoreCase(mAnswer.substring(1))){
                    mScore++;
                    score.setText("Score: " + mScore);
                    updateQuestion(q);
                } else{
                    gameOver();
                }
            }
        });

    }

    private void updateQuestion(int n){
        question.setText(mQuestions.getQuestions(n));
        choice1.setText(mQuestions.getChoice1(n));
        choice2.setText(mQuestions.getChoice2(n));
        choice3.setText(mQuestions.getChoice3(n));
        choice4.setText(mQuestions.getChoice4(n));
        mAnswer = mQuestions.getAnswer(n);
    }



    public void loadQuestions(){

        int qCount=0;
        int cCount = 0;
        int ansCount = 0;

        try{
            File file = new File(Environment.getExternalStorageDirectory().getAbsoluteFile()+"/postTest.txt");
            LineNumberReader lin = new LineNumberReader(new FileReader(file));
            String line;
            while((line = lin.readLine()) != null) {
                // load question
                if (line.startsWith("$")) {
                    if(line.endsWith(">")){
                        cCount++;
                    }
                    questions.mQuestions[qCount] = line;
                    qCount++;
                }
                // load choices
                else if (line.startsWith("&")){
                    questions.mChoices[cCount][0]=line;
                }
                else if (line.startsWith("!")){
                    questions.mChoices[cCount][1]=line;
                }
                else if (line.startsWith("*")){
                    questions.mChoices[cCount][2]=line;
                }
                else if (line.startsWith("@")){
                    questions.mChoices[cCount][3]=line;
                }
                // load answers
                else if (line.startsWith(".")) {
                    questions.mAnswers[ansCount] = line;
                    ansCount++;
                }
                else return;
            }
        }
        catch(IOException e){
        }
    }
    private void gameOver(){
        AlertDialog.Builder adb = new AlertDialog.Builder(postViewer.this);
        adb.setMessage("game over fool").setPositiveButton("new game?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(getApplicationContext(), postViewer.class));
            }
        }).setNegativeButton("exit?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        adb.show();
    }
}