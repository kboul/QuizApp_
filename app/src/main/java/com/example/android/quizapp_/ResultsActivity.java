package com.example.android.quizapp_;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() { return; }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        String name = getIntent().getStringExtra("name");
        int answers = getIntent().getIntExtra("answers",0);

        TextView score = (TextView) findViewById(R.id.results);
        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        switch(answers){
            case 0:
                score.setText("That was... " + name + ".\n " +
                        "You have " + Integer.toString(answers) + " correct answers!" +
                        "\nIt must be the first time you hear the phrase Pink Floyd!");
                ratingBar.setRating(0);
                break;
            case 1:
                score.setText("That was not so successful " + name + ".\n " +
                        "You have " + Integer.toString(answers) + " correct answer!" +
                        "\nYou are really not a fun of Pink Floyd!");
                ratingBar.setRating(1);
                break;
            case 2:
                score.setText("You got a few " + name + ".\n " +
                        "You have " + Integer.toString(answers) + " correct answers!" +
                        "\nYou seem to ...have heard somewhere something from Pink Floyd!");
                ratingBar.setRating(2);
                break;
            case 3:
                score.setText("Good Try " + name + ".\n " +
                        "You have " + Integer.toString(answers) + " correct answers!" +
                        "\nYou seem to know the basic for Pink Floyd!");
                ratingBar.setRating(3);
                break;
            case 4:
                score.setText("Congratulations " + name + ".\n " +
                        "You have " + Integer.toString(answers) + " correct answers!" +
                        "\nYou seem to know a lot for Pink Floyd!");
                ratingBar.setRating(4);
                break;
            case 5:
                score.setText("Congratulations " + name + ".\n " +
                        "You have " + Integer.toString(answers) + " correct answers!" +
                        "\nYou seem to know a lot for Pink Floyd!");
                ratingBar.setRating(5);
                break;
            case 6:
                score.setText("Congratulations " + name + ".\n " +
                        "You have " + Integer.toString(answers) + " correct answers!" +
                        "\nYou have excellent knowledge for Pink Floyd!");
                ratingBar.setRating(6);
        }
        ratingBar.setVisibility(View.VISIBLE);
    }

    public void tryAgain(View view){
        Intent intent = new Intent(ResultsActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
