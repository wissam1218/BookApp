package com.example.bookapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.example.bookapp.MainActivity.classNumber;
import static com.example.bookapp.MainActivity.lessonNumber;
import static com.example.bookapp.MainActivity.savedClass;
import static com.example.bookapp.MainActivity.savedLesson;

// postviewer, previewer and their result activities are the same

public class postViewer extends AppCompatActivity  {

    // When postViewer is initially loaded up,
    // we create references to the files that we stores the scores in
    public static File root = new File(Environment.getExternalStorageDirectory() + "/reports");
    public static File file = new File(root, "/postReportCard.txt");

    // q is used to update the questions
    int q = 0;

    Button choice1,choice2,choice3,choice4;
    TextView score,question;

    // We refer to the questions class and create a string variable to hold the correct answer
    private questions mQuestions = new questions();
    private String mAnswer;

    // We must keep track of post score
    public static int PostScore = 0;

    // We must keep track of question number so that
    // the questions are updated in the correct order
    public static int questionNum = 0;

    // We must keep track of the test number to help when writing to the report card file
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

        // if reports folder is not found on root of storage the we create that folder
        if(!root.exists()){
            root.mkdir();
        }

        // if the file to store the scores does not exist then we create that file
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // we need to load the questions from the files found on storage
        loadQuestions();

        // we need to update questions so they appear on the screen
        updateQuestion(q);

        // set score to screen
        score.setText("Score: " + PostScore);

        choice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q++;
                if(choice1.getText().toString().equalsIgnoreCase(mAnswer.substring(1))){
                    Toast.makeText(postViewer.this,"correct",Toast.LENGTH_SHORT).show();
                    PostScore++;
                    score.setText("Score: " + PostScore);
                    updateQuestion(q);
                }else{
                    score.setText("Score: " + PostScore);
                    updateQuestion(q);
                }
            }
        });
        choice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q++;
                if(choice2.getText().toString().equalsIgnoreCase(mAnswer.substring(1))){
                    Toast.makeText(postViewer.this,"correct",Toast.LENGTH_SHORT).show();
                    PostScore++;
                    score.setText("Score: " + PostScore);
                    updateQuestion(q);
                } else{
                    score.setText("Score: " + PostScore);
                    updateQuestion(q);
                }
            }
        });

        choice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                q++;
                if(choice3.getText().toString().equalsIgnoreCase(mAnswer.substring(1))){
                    Toast.makeText(postViewer.this,"correct",Toast.LENGTH_SHORT).show();
                    PostScore++;
                    score.setText("Score: " + PostScore);
                    updateQuestion(q);
                } else{
                    score.setText("Score: " + PostScore);
                    updateQuestion(q);
                }
            }
        });
        choice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q++;
                if(choice4.getText().toString().equalsIgnoreCase(mAnswer.substring(1))){
                    Toast.makeText(postViewer.this,"correct",Toast.LENGTH_SHORT).show();
                    PostScore++;
                    score.setText("Score: " + PostScore);
                    updateQuestion(q);
                }  else{
                    score.setText("Score: " + PostScore);
                    updateQuestion(q);
                }
            }
        });
    }

    // This function extracts the question set from the appropriate arrays and sets the text
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
            i.putExtra("score", PostScore); // pass the score to resultActivity
            startActivity(i);
        }
    }

    public void loadQuestions(){
        // These counters are required so that each question set is loaded into the correct position
        int qCount=0;
        int cCount = -1;
        int ansCount = 0;
        Integer[] cArr = {0,1,2,3};
        try{
            File file = new File(Environment.getExternalStorageDirectory().getAbsoluteFile()
                    +"/Classrooms/class"+ savedClass + "/Curriculum/Lesson "+ savedLesson +"/postTest"+testNum+".txt");
            LineNumberReader lin = new LineNumberReader(new FileReader(file));
            String line;
            while((line = lin.readLine()) != null) {
                // Load question
                if (line.startsWith("$")) {
                    if(line.endsWith(">")){
                        cCount++;
                    }
                    questions.mQuestions[qCount] = line.substring(1, line.length()-1);
                    qCount++;
                }
                // Load choices
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
                // Load answers
                else if (line.startsWith(".")) {
                    questions.mAnswers[ansCount] = line;
                    ansCount++;
                    // Shuffle choice array to randomize choices
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
}