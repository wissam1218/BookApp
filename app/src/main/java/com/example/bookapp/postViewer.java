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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static com.example.bookapp.MainActivity.hasPermissions;

import static java.lang.Math.floor;

public class postViewer extends AppCompatActivity  {

    int q = 0;
    Button choice1,choice2,choice3,choice4;
    TextView score,question;
    private questions mQuestions = new questions();
    private String mAnswer;
    public static int mScore = 0;
    Button loadBtn;
    public static int questionNum=0;

    public static int testNum = 1;
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
        loadBtn = findViewById(R.id.loadBtn);
        loadQuestions();
        updateQuestion(q);
        score.setText("Score: " + mScore);
        choice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q++;
                if(choice1.getText().toString().equalsIgnoreCase(mAnswer.substring(1))){
                    Toast.makeText(postViewer.this,"correct",Toast.LENGTH_SHORT).show();
                   mScore++;
                    score.setText("Score: " + mScore);
                    updateQuestion(q);
                }else gameOver();
            }
        });
        choice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q++;
                if(choice2.getText().toString().equalsIgnoreCase(mAnswer.substring(1))){
                    Toast.makeText(postViewer.this,"correct",Toast.LENGTH_SHORT).show();
                    mScore++;
                    score.setText("Score: " + mScore);
                    updateQuestion(q);
                } else gameOver();
            }
        });
        choice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                q++;
                if(choice3.getText().toString().equalsIgnoreCase(mAnswer.substring(1))){
                    Toast.makeText(postViewer.this,"correct",Toast.LENGTH_SHORT).show();
                    mScore++;
                    score.setText("Score: " + mScore);
                    updateQuestion(q);
                } else gameOver();
            }
        });
        choice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q++;
                if(choice4.getText().toString().equalsIgnoreCase(mAnswer.substring(1))){
                    Toast.makeText(postViewer.this,"correct",Toast.LENGTH_SHORT).show();
                    mScore++;
                    score.setText("Score: " + mScore);
                    updateQuestion(q);
                }  else gameOver();
            }
        });
    }

    private void updateQuestion(int n){
        // check if we are not outside array bounds for questions
        if(questionNum<mQuestions.mQuestions.length){
           question.setText(mQuestions.getQuestions(n));
           choice1.setText(mQuestions.getChoice1(n));
           choice2.setText(mQuestions.getChoice2(n));
           choice3.setText(mQuestions.getChoice3(n));
           choice4.setText(mQuestions.getChoice4(n));
           mAnswer = mQuestions.getAnswer(n);
           questionNum++;
        }
        else {
            Intent i = new Intent(postViewer.this, resultActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("Final Score: ", mScore);
            i.putExtras(bundle);
            postViewer.this.finish();
            startActivity(i);

//            Intent intent = new Intent(postViewer.this, resultActivity.class);
//            intent.putExtra("score", mScore); // pass the score
//            startActivity(intent);
        }
    }



    public void loadQuestions(){
        // these counters are required so that each question set is loaded into the correct position
        int qCount=0;
        int cCount = -1;
        int ansCount = 0;
        Integer[] cArr = {0,1,2,3};
        try{
            File file = new File(Environment.getExternalStorageDirectory().getAbsoluteFile()+"/postTest"+testNum+".txt");
            LineNumberReader lin = new LineNumberReader(new FileReader(file));
            String line;

            while((line = lin.readLine()) != null) {
                // load question
                if (line.startsWith("$")) {
                    if(line.endsWith(">")){
                        cCount++;
                    }
                    questions.mQuestions[qCount] = line.substring(1);
                    qCount++;
                }
                // load choices
                else if (line.startsWith("&")){
                    questions.mChoices[cCount][cArr[0]]=line.substring(1);
                }
                else if (line.startsWith("!")){
                    questions.mChoices[cCount][cArr[1]]=line.substring(1);
                }
                else if (line.startsWith("*")){
                    questions.mChoices[cCount][cArr[2]] =line.substring(1);
                }
                else if (line.startsWith("@")){
                    questions.mChoices[cCount][cArr[3]]=line.substring(1);
                }
                // load answers
                else if (line.startsWith(".")) {
                    questions.mAnswers[ansCount] = line;
                    ansCount++;
                    // shuffle choice array
                    List<Integer> intList = Arrays.asList(cArr);
                    Collections.shuffle(intList);
                    intList.toArray(cArr);
                }
                else return;
            }
            lin.close();
        }
        catch(IOException e) {
        }
    }
    private void gameOver(){
        AlertDialog.Builder adb = new AlertDialog.Builder(postViewer.this);
        adb.setMessage("Game Over.").setPositiveButton("new game?", new DialogInterface.OnClickListener() {
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