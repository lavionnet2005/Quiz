package com.example.android.quiz;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by lkatta on 11/28/16.
 * Feedback quiz, user gets brownie points for giving feedback.
 */

public class FeedbackQuiz extends AppCompatActivity {

    public static final String MyPREFERENCES = "MYPREFS";
    public static final String isFeedbackDone = "ISFEEDBACKDONE";
    public static final String score = "SCORE";
    private static int feedbackscore = 10;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        Button submit = (Button) findViewById(R.id.feedbackButton);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText text = (EditText) findViewById(R.id.feedbackTextView);
                Editable input = text.getEditableText();
                String feedback = input.toString();

                sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                int finalScore = sharedPreferences.getInt(score, 0);
                finalScore = finalScore + feedbackscore;

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(isFeedbackDone, true);
                editor.putInt(score, finalScore);
                editor.commit();
                toast();

                finish();
            }
        });

    }

    /**
     * Displays toast message.
     */
    private void toast() {
        Toast.makeText(this, "Thanks for your feedback, you earn 10 brownie points.", Toast.LENGTH_SHORT).show();
    }
}
