package com.example.android.quiz;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

/**
 * Created by lkatta on 11/28/16.
 * Biology quiz. Checks answers for all questions.
 */

public class BiologyQuiz extends AppCompatActivity {

    public static final String MyPREFERENCES = "MYPREFS";
    public static final String isBiologyDone = "ISBIOLOGYDONE";
    public static final String score = "SCORE";
    private static int biologyScore = 0;
    private static int finalScore = 0;
    String question1 = "wrong";
    SharedPreferences sharedPreferences;
    Button submit;
    private boolean isOptionSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biology);

        submit = (Button) findViewById(R.id.submit);

        final CheckBox optionA = (CheckBox) findViewById(R.id.optionA);
        final CheckBox optionB = (CheckBox) findViewById(R.id.optionB);
        final CheckBox optionC = (CheckBox) findViewById(R.id.optionC);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                finalScore = sharedPreferences.getInt(score, 0);

                if (optionA.isChecked() || optionB.isChecked() || optionC.isChecked()) {
                    isOptionSelected = true;

                    if (optionC.isChecked() && !optionA.isChecked() && !optionB.isChecked()) {
                        question1 = "right";
                        biologyScore = 10;
                        finalScore = finalScore + biologyScore;

                        toast(isOptionSelected);
                    } else {
                        toast(isOptionSelected);
                    }

                } else {
                    toast(isOptionSelected);
                }

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(isBiologyDone, true);
                editor.putInt(score, finalScore);
                editor.commit();
                finish();
            }
        });
    }

    /**
     * Toast message is displayed on screen.
     * @param isOptionSelected Check box is selected or not.
     */
    private void toast(boolean isOptionSelected) {
        if (isOptionSelected == true) {
            Toast.makeText(this, "Biology Question: " + question1, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Score:" + finalScore, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please select an option for question ", Toast.LENGTH_SHORT).show();
        }
    }

}
