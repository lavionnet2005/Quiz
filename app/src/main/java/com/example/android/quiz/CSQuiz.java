package com.example.android.quiz;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

/**
 * Created by lkatta on 11/28/16.
 * Computer Science Quiz, checks all the answers.
 */

public class CSQuiz extends AppCompatActivity {
    public static final String MyPREFERENCES = "MYPREFS";
    public static final String isCSDone = "ISCSDONE";
    public static final String score = "SCORE";
    private static int csScore = 0;
    boolean isQuestionOneRight = false;
    boolean isQuestionTwoRight = false;
    String question1 = "wrong";
    String question2 = "wrong";
    boolean isRadioGroupOneSelected = false;
    boolean isRadioGrouptwoSelected = false;
    boolean alreadyAnswered = false;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cs);
    }

    /**
     * Checks for all the right answers, updates the final score.
     * @param view view of the activty.
     */
    public void submit(View view) {

        if (alreadyAnswered) {
            Toast.makeText(this, "You have already completed this category, please select another category.", Toast.LENGTH_SHORT).show();
        } else {
            if (!isRadioGroupOneSelected) {
                Toast.makeText(this, "Please select an option for question 1 ", Toast.LENGTH_SHORT).show();
            } else if (!isRadioGrouptwoSelected) {
                Toast.makeText(this, "Please select an option for question 2 ", Toast.LENGTH_SHORT).show();
            } else {
                if (isQuestionOneRight) {
                    question1 = "correct";
                    csScore = csScore + 10;
                }
                if (isQuestionTwoRight) {
                    question2 = "correct";
                    csScore = csScore + 10;
                }

                sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                int finalScore = sharedPreferences.getInt(score, 0);
                finalScore = finalScore + csScore;

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(isCSDone, true);
                editor.putInt(score, finalScore);
                editor.commit();

                Toast.makeText(this, "CS Question1: " + question1 + "\n CS Question2: " + question2, Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Score: " + finalScore, Toast.LENGTH_SHORT).show();
                alreadyAnswered = true;

                finish();

            }
        }
    }

    /**
     * Checks first question answer.
     * @param view View of the activity.
     */
    public void onQuestionOneGroupSelected(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        if (checked) {
            isRadioGroupOneSelected = true;
        }

        switch (view.getId()) {
            case R.id.radioA1:
                isQuestionOneRight = false;
                break;
            case R.id.radioB1:
                if (checked) {
                    isQuestionOneRight = true;
                }
                break;
            case R.id.radioC1:
                isQuestionOneRight = false;
                break;
            case R.id.radioD1:
                isQuestionOneRight = false;
                break;
        }
    }

    /**
     * Checks second question answer.
     * @param view View of the activity.
     */
    public void onQuestionTwoGroupSelected(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        if (checked) {
            isRadioGrouptwoSelected = true;
        }

        switch (view.getId()) {
            case R.id.radioA2:
                isQuestionTwoRight = false;
                break;
            case R.id.radioB2:
                isQuestionTwoRight = false;
                break;
            case R.id.radioC2:
                if (checked) {
                    isQuestionTwoRight = true;
                }
                break;
            case R.id.radioD2:
                isQuestionTwoRight = false;
                break;
        }
    }
}
