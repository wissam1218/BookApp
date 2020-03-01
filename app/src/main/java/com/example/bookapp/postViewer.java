package com.example.bookapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
        updateQuestion(q);
        score.setText("Score: " + mScore);

        choice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q++;
                if(choice1.getText() == mAnswer){
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
}
