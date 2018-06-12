package com.example.meizar.learningstyleapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

/**
 * Created by meizar on 30/05/18.
 */

public class QuestionAndAnswers {
    int questionNo;
    String question;
    String vAnswer, aAnswer, rAnswer, kAnswer;
    int vPoint, aPoint, rPoint, kPoint;
    ArrayList<String> answers = new ArrayList<String>();

    QuestionAndAnswers(){}

    public ArrayList<String> getAnswers() {
        if(answers.isEmpty()) initAnswers();
        return (answers);
    }
    public void initAnswers(){
        answers.add(vAnswer);
        answers.add(aAnswer);
        answers.add(rAnswer);
        answers.add(kAnswer);
        Collections.shuffle(answers);
    }
}
