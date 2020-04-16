package com.example.bookapp;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import static com.example.bookapp.MainActivity.classNumber;
import static com.example.bookapp.MainActivity.lessonNumber;
import static com.example.bookapp.preViewer.PreScore;
import static com.example.bookapp.preViewer.file;
import static com.example.bookapp.preViewer.questionNum;
import static com.example.bookapp.preViewer.testNum;
import static com.example.bookapp.questions.QA;

public class resultActivityPre extends AppCompatActivity {

    public static BufferedWriter out;
    {
        try {
            out = new BufferedWriter((new FileWriter(file, true)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    TextView mGrade, mFinalScore;
    Button mRetryButton, nextBtn, saveBtn, menuBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        mGrade = findViewById(R.id.grade);
        mFinalScore = findViewById(R.id.score);
        mRetryButton = findViewById(R.id.retry);
        nextBtn = findViewById(R.id.nextBtn);
        saveBtn =  findViewById(R.id.saveBtn);
        menuBtn = findViewById(R.id.menuBtn);
        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(resultActivityPre.this, MainActivity.class);
                startActivity(i);
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    savethis();
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
    public static void savethis() throws IOException{
        out.write("Class: " + classNumber + ", " + "Lesson: " + lessonNumber + ", " +
                "Pre test number "+ testNum + " score is: " + PreScore + "/"+(QA+1));
        out.newLine();
        PreScore = 0;
        out.close();
    }
}