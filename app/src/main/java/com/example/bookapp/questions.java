package com.example.bookapp;

public class questions {

    public static String mQuestions[] = new String[100];
    public static String mAnswers[] = new String[100];
    public static String mChoices[][] = new String[100][400];

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
