package com.example.meizar.learningstyleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class SurveyActivity extends AppCompatActivity {

    FirebaseDatabase mDatabase;
    DatabaseReference mReference;
    TextView numberText, questionText;
    CheckBox checkBox1, checkBox2, checkBox3, checkBox4;
    Button nextBtn, prevBtn;
    ArrayList<QuestionAndAnswers> qnaList;
    QuestionAndAnswers activeQna;
    int iteration = 0;
    int vTotal = 0, aTotal = 0, rTotal = 0, kTotal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        //ui component
        numberText = (TextView) findViewById(R.id.numberText);
        questionText = (TextView) findViewById(R.id.questionText);
        checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        checkBox3 = (CheckBox) findViewById(R.id.checkBox3);
        checkBox4 = (CheckBox) findViewById(R.id.checkBox4);
        nextBtn = (Button) findViewById(R.id.nextBtn);
        prevBtn = (Button) findViewById(R.id.prevBtn);

        //disable btn
        nextBtn.setEnabled(false);
        prevBtn.setEnabled(false);

        //qnaList
        qnaList = new ArrayList<QuestionAndAnswers>();

        //fetching data from firebase
        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference("QnA");
        mReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                    QuestionAndAnswers mQna = singleSnapshot.getValue(QuestionAndAnswers.class);
                    mQna.initAnswers();
                    qnaList.add(mQna);
                }
                Collections.shuffle(qnaList);
                startSurvey();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        //next button logic
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iteration < qnaList.size()){
                    calculateQna();
                    //get next QnA
                    iteration++;
                    activeQna = qnaList.get(iteration);
                    updateQna(activeQna, iteration);
                }
            }
        });

        //previous button logic
        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iteration > 0){
                    calculateQna();
                    iteration--;
                    activeQna = qnaList.get(iteration);
                    updateQna(activeQna, iteration);
                }
            }
        });
    }

    private void startSurvey() {
        nextBtn.setEnabled(true);
        activeQna = qnaList.get(0);
        updateQna(activeQna, iteration);
    }

    private void calculateQna(){
        if(checkBox1.isChecked()){
            if(checkBox1.getText() == activeQna.vAnswer) activeQna.vPoint = 1;
            else if(checkBox1.getText() == activeQna.aAnswer) activeQna.aPoint = 1;
            else if(checkBox1.getText() == activeQna.rAnswer) activeQna.rPoint = 1;
            else if(checkBox1.getText() == activeQna.kAnswer) activeQna.kPoint = 1;
        }
        else{
            if(checkBox1.getText() == activeQna.vAnswer) activeQna.vPoint = 0;
            else if(checkBox1.getText() == activeQna.aAnswer) activeQna.aPoint = 0;
            else if(checkBox1.getText() == activeQna.rAnswer) activeQna.rPoint = 0;
            else if(checkBox1.getText() == activeQna.kAnswer) activeQna.kPoint = 0;
        }
        if(checkBox2.isChecked()){
            if(checkBox2.getText() == activeQna.vAnswer) activeQna.vPoint = 1;
            else if(checkBox2.getText() == activeQna.aAnswer) activeQna.aPoint = 1;
            else if(checkBox2.getText() == activeQna.rAnswer) activeQna.rPoint = 1;
            else if(checkBox2.getText() == activeQna.kAnswer) activeQna.kPoint = 1;
        }
        else {
            if (checkBox2.getText() == activeQna.vAnswer) activeQna.vPoint = 0;
            else if (checkBox2.getText() == activeQna.aAnswer) activeQna.aPoint = 0;
            else if (checkBox2.getText() == activeQna.rAnswer) activeQna.rPoint = 0;
            else if (checkBox2.getText() == activeQna.kAnswer) activeQna.kPoint = 0;
        }
        if(checkBox3.isChecked()){
            if(checkBox3.getText() == activeQna.vAnswer) activeQna.vPoint = 1;
            else if(checkBox3.getText() == activeQna.aAnswer) activeQna.aPoint = 1;
            else if(checkBox3.getText() == activeQna.rAnswer) activeQna.rPoint = 1;
            else if(checkBox3.getText() == activeQna.kAnswer) activeQna.kPoint = 1;
        }
        else {
            if (checkBox3.getText() == activeQna.vAnswer) activeQna.vPoint = 0;
            else if (checkBox3.getText() == activeQna.aAnswer) activeQna.aPoint = 0;
            else if (checkBox3.getText() == activeQna.rAnswer) activeQna.rPoint = 0;
            else if (checkBox3.getText() == activeQna.kAnswer) activeQna.kPoint = 0;
        }
        if(checkBox4.isChecked()){
            if(checkBox4.getText() == activeQna.vAnswer) activeQna.vPoint = 1;
            else if(checkBox4.getText() == activeQna.aAnswer) activeQna.aPoint = 1;
            else if(checkBox4.getText() == activeQna.rAnswer) activeQna.rPoint = 1;
            else if(checkBox4.getText() == activeQna.kAnswer) activeQna.kPoint = 1;
        }
        else {
            if (checkBox4.getText() == activeQna.vAnswer) activeQna.vPoint = 0;
            else if (checkBox4.getText() == activeQna.aAnswer) activeQna.aPoint = 0;
            else if (checkBox4.getText() == activeQna.rAnswer) activeQna.rPoint = 0;
            else if (checkBox4.getText() == activeQna.kAnswer) activeQna.kPoint = 0;
        }
    }

    private void updateQna(QuestionAndAnswers mQna, int i){
        numberText.setText(String.valueOf(i+1));
        questionText.setText(mQna.question);
        ArrayList<String> mAnswers = mQna.getAnswers();
        checkBox1.setText(mAnswers.get(0));
        checkBox2.setText(mAnswers.get(1));
        checkBox3.setText(mAnswers.get(2));
        checkBox4.setText(mAnswers.get(3));

        //check button next and prev
        if(i == 0) prevBtn.setEnabled(false);
        else prevBtn.setEnabled(true);
        if(i == (qnaList.size()-1))
        {
            nextBtn.setText("Selesai");
            nextBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    calculateQna();
                    calculateResult();
                    Intent intent = new Intent(SurveyActivity.this, ResultActivity.class);
                    intent.putExtra("vTotal", vTotal);
                    intent.putExtra("aTotal", aTotal);
                    intent.putExtra("rTotal", rTotal);
                    intent.putExtra("kTotal", kTotal);
                    startActivity(intent);
                }
            });
        }

        //update checkbox
        checkBox1.setChecked(false);
        checkBox2.setChecked(false);
        checkBox3.setChecked(false);
        checkBox4.setChecked(false);
        if(mQna.vPoint == 1){
            int vCheck = mAnswers.indexOf(mQna.vAnswer);
            switch (vCheck){
                case 0 : checkBox1.setChecked(true); break;
                case 1 : checkBox2.setChecked(true); break;
                case 2 : checkBox3.setChecked(true); break;
                case 3 : checkBox4.setChecked(true); break;
                default : break;
            }
        }
        if(mQna.aPoint == 1){
            int aCheck = mAnswers.indexOf(mQna.aAnswer);
            switch (aCheck){
                case 0 : checkBox1.setChecked(true); break;
                case 1 : checkBox2.setChecked(true); break;
                case 2 : checkBox3.setChecked(true); break;
                case 3 : checkBox4.setChecked(true); break;
                default : break;
            }
        }
        if(mQna.rPoint == 1){
            int rCheck = mAnswers.indexOf(mQna.rAnswer);
            switch (rCheck){
                case 0 : checkBox1.setChecked(true); break;
                case 1 : checkBox2.setChecked(true); break;
                case 2 : checkBox3.setChecked(true); break;
                case 3 : checkBox4.setChecked(true); break;
                default : break;
            }
        }
        if(mQna.kPoint == 1){
            int kCheck = mAnswers.indexOf(mQna.kAnswer);
            switch (kCheck){
                case 0 : checkBox1.setChecked(true); break;
                case 1 : checkBox2.setChecked(true); break;
                case 2 : checkBox3.setChecked(true); break;
                case 3 : checkBox4.setChecked(true); break;
                default : break;
            }
        }
    }
    private void calculateResult(){
        for(QuestionAndAnswers singleQna : qnaList){
            vTotal += singleQna.vPoint;
            aTotal += singleQna.aPoint;
            rTotal += singleQna.rPoint;
            kTotal += singleQna.kPoint;
        }
    }
}
