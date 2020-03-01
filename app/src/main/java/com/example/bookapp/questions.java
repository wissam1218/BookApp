package com.example.bookapp;

import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class questions {

    public String mQuestions[]    ={"what is question 1","what is question 2",
            "what is question 3","what is question 4",
            "what is question 5","what is question 6",
            "what is question 7","what is question 8","what is question 9"};


    public void loadQuestions(){
        BufferedReader br = null;



    }

    private String mChoices[][] = {
            {"choice1","choice2","choice3","choice4"},
            {"choice1","choice2","choice3","choice4"},
            {"choice1","choice2","choice3","choice4"},
            {"choice1","choice2","choice3","choice4"},
            {"choice1","choice2","choice3","choice4"},
            {"choice1","choice2","choice3","choice4"},
            {"choice1","choice2","choice3","choice4"},
            {"choice1","choice2","choice2","choice3"},
            {"choice1","choice2","choice3","choice4"}
            };
    private String mAnswers[] = {"choice1","choice1","choice1","choice1","choice1","choice1","choice1","choice1","choice1"};

    public String getQuestions(int n){
        String question = mQuestions[n];
        return question;
    }
    public String getChoice1(int n){
        String choice = mChoices[n][0];
        return choice;

    }
    public String getChoice2(int n){
        String choice = mChoices[n][1];
        return choice;

    }
    public String getChoice3(int n){
        String choice = mChoices[n][2];
        return choice;

    }
    public String getChoice4(int n){
        String choice = mChoices[n][3];
        return choice;

    }
    public String getAnswer(int n){
        String ans = mAnswers[n];
        return ans;
    }


}
