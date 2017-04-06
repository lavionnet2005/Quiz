package com.example.android.quiz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Quiz categories are displayed, and based on the user selection questions are displayed.
 * Once a user answers a category, its disabled and user is asked to select another category.
 * Once all the categories are completed, quiz is completed.
 */
public class MainActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "MYPREFS";
    public static final String isChemistryDone = "ISCHEMISTRYDONE";
    public static final String isBiologyDone = "ISBIOLOGYDONE";
    public static final String isCSDone = "ISCSDONE";
    public static final String isHistoryDone = "ISHISTORYDONE";
    public static final String isFeedbackDone = "ISFEEDBACKDONE";
    public static final String score = "SCORE";
    private static int categoryCount = 0;
    Button chemistry;
    Button biology;
    Button cs;
    Button history;
    Button feedback;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toast.makeText(this, "Please select a category to start", Toast.LENGTH_SHORT).show();
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(isChemistryDone, false);
        editor.putBoolean(isBiologyDone, false);
        editor.putBoolean(isCSDone, false);
        editor.putBoolean(isHistoryDone, false);
        editor.putBoolean(isFeedbackDone, false);
        editor.putInt(score, 0);

        editor.commit();

        chemistry = (Button) findViewById(R.id.chemistry);

        chemistry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isChemistryTurn = sharedPreferences.getBoolean(isChemistryDone, false);
                setDisabledButton(isChemistryTurn, chemistry, ChemistryQuiz.class);
            }

        });

        biology = (Button) findViewById(R.id.biology);
        biology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isBiologyTurn = sharedPreferences.getBoolean(isBiologyDone, false);
                setDisabledButton(isBiologyTurn, biology, BiologyQuiz.class);
            }
        });


        cs = (Button) findViewById(R.id.cs);
        cs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isCsTurn = sharedPreferences.getBoolean(isCSDone, false);
                setDisabledButton(isCsTurn, cs, CSQuiz.class);
            }
        });


        history = (Button) findViewById(R.id.history);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isHistoryTurn = sharedPreferences.getBoolean(isHistoryDone, false);
                setDisabledButton(isHistoryTurn, history, HistoryQuiz.class);
            }
        });


        feedback = (Button) findViewById(R.id.feedback);
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isFeedbackTurn = sharedPreferences.getBoolean(isFeedbackDone, false);
                setDisabledButton(isFeedbackTurn, feedback, FeedbackQuiz.class);
            }
        });
    }

    private void setDisabledButton(boolean whoseturn, Button b, Class context) {
        if (!whoseturn) {
            Intent chemistryIntent = new Intent(MainActivity.this, context);
            startActivity(chemistryIntent);
        } else {
            b.setBackgroundColor(Color.GRAY);
            b.setEnabled(false);
            categoryCount = categoryCount + 1;
            if (categoryCount == 5) {
                Toast.makeText(this, "Quiz over please start a new game.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Please select another category.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * When destroying the app, clears all the shared preferences values.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    /**
     * User updated score is displayed.
     * @param view View of the activity.
     */
    public void updateScore(View view) {
        Toast.makeText(this, "Score: " + sharedPreferences.getInt(score, 0), Toast.LENGTH_SHORT).show();
    }

    /**
     * Resets all values and starts a new game.
     * @param view View of the activity.
     */
    public void reset(View view) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linear_layout);

        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            Button b = (Button) linearLayout.getChildAt(i);
            b.setEnabled(true);
            b.setBackgroundColor(getResources().getColor(R.color.buttonColor));
            b.setTextColor(Color.BLACK);
        }
    }
}
