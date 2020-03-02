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
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class preViewer extends AppCompatActivity  {
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
    private void gameOver(){
        AlertDialog.Builder adb = new AlertDialog.Builder(preViewer.this);
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

        try{
            InputStream in = assets.open("postTest.txt");
            LineNumberReader lin = new LineNumberReader(new InputStreamReader(in));
            String line;
            int qCount = 0;
            int cCount = 0;
            int aCount = 0;

            while((line = lin.readLine())!= null) {
                // increment choice counter (excluding the first question)
                if(line.endsWith(">")){
                    cCount=cCount+1;
                }
                // load question
                if (line.startsWith("$")) {
                    if(line.endsWith(">")){
                        line = line.substring(0,line.length()-1);
                    }
                    questions.mQuestions[qCount] = line.substring(1);
                    qCount++;
                }
                // load choices
                if (line.startsWith("&")){
                    questions.mChoices[cCount][0]=line.substring(1);
                }
                if (line.startsWith("!")){
                    questions.mChoices[cCount][1]=line.substring(1);
                }
                if (line.startsWith("*")){
                    questions.mChoices[cCount][2]=line.substring(1);
                }
                if (line.startsWith("@")){
                    questions.mChoices[cCount][3]=line.substring(1);
                }
                // load answers
                if (line.startsWith(".")) {
                    questions.mAnswers[aCount] = line.substring(1);
                    aCount++;
                }

            }

        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    public void done(){
        Toast.makeText(this, "done", Toast.LENGTH_SHORT).show();
    }
}
