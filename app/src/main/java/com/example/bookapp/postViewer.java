package com.example.bookapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;

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
        loadQuestions();
        updateQuestion(q);
        score.setText("Score: " + mScore);
        choice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q++;
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
                if(choice2.getText() == mAnswer){
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
                if(choice3.getText() == mAnswer){
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
                if(choice4.getText() == mAnswer){
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

    public void loadQuestions(){
        AssetManager assets = getAssets();
        int qCount=0;
        int ansCount = 0;
        int choices = 0;
        int nextChoice = 0;

        try{
            InputStream in = assets.open("postTest.txt");
            LineNumberReader lin = new LineNumberReader(new InputStreamReader(in));
            String line;

            while((line = lin.readLine())!= null) {
                // load question
                if (line.startsWith("$")) {
                    questions.mQuestions[qCount] = line;
                    qCount++;
                }
                else if (line.startsWith("&")){
                    questions.mChoices[0][0]=line;
                }
                else if (line.startsWith("!")){
                    questions.mChoices[0][1]=line;
                }
                else if (line.startsWith("*")){
                    questions.mChoices[0][2]=line;
                }
                else if (line.startsWith("@")){
                    questions.mChoices[0][3]=line;
                }


            }
        }
        catch(IOException e){
        }
    }
}
