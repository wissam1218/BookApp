package com.example.bookapp;


public class questions {
    //ten questions sets
    final static int QA = 9;
    final static int OPTIONS = QA * 4;
    public static String mQuestions[] = new String[QA+1];
    public static String mAnswers[] = new String[QA+1];
    public static String mChoices[][] = new String[OPTIONS][4];

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
