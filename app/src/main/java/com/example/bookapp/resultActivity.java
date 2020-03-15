package com.example.bookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import static com.example.bookapp.postViewer.mScore;

public class resultActivity extends AppCompatActivity {
    TextView mGrade, mFinalScore;
    Button mRetryButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mGrade = (TextView)findViewById(R.id.grade);
        mFinalScore = (TextView)findViewById(R.id.outOf);
        mRetryButton = (Button)findViewById(R.id.retry);


        Bundle bundle = getIntent().getExtras();
        int score = bundle.getInt("finalScore");

        mFinalScore.setText("You scored " + mScore);


    }
}
