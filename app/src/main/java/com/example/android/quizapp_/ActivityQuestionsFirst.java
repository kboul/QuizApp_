package com.example.android.quizapp_;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ActivityQuestionsFirst extends AppCompatActivity {

    int[] radioButtonIDs = new int[]{
            R.id.first_question_group,
            R.id.second_question_group,
            R.id.third_question_group};
    int questionsAnswered = 0;

    @Override
    public void onBackPressed() { return; }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_first);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        /*
        *  Go Forward
        */
        Button next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityQuestionsFirst.this, ActivityQuestionsSecond.class);

                String name = getIntent().getStringExtra("name");

                // Check whether the radio buttons have been selected
                if (checkIfFirst3AnswersAreEmpty()){
                    return;
                }

                // Check correct answers, avoid incorrect sum when going back from different activities
                if (getIntent().getExtras() == null) {
                    checkScoreForFirst3Answers();
                } else {
                    questionsAnswered = 0;
                    checkScoreForFirst3Answers();
                }

                intent.putExtra("questionsAnswered", questionsAnswered);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });
    }

    private boolean checkIfFirst3AnswersAreEmpty() {
        for (int i = 0; i < radioButtonIDs.length; i++) {
            RadioGroup QuestionGroup = (RadioGroup) findViewById(radioButtonIDs[i]);
            int QuestionGroupSelected = QuestionGroup.getCheckedRadioButtonId();
            if (QuestionGroupSelected == -1) {
                Toast.makeText(getApplicationContext(), "You have unchecked options!", Toast.LENGTH_SHORT).show();
                return true;
            }
        }
        return false;
    }

    private void checkScoreForFirst3Answers() {
        RadioButton rightAnswerFirstQuestion = (RadioButton) findViewById(R.id.right_answer_first_question);
        RadioButton rightAnswerSecondQuestion = (RadioButton) findViewById(R.id.right_answer_second_question);
        RadioButton rightAnswerThirdQuestion = (RadioButton) findViewById(R.id.right_answer_third_question);
        if (rightAnswerFirstQuestion.isChecked()) {
            questionsAnswered++;
        }
        if (rightAnswerSecondQuestion.isChecked()) {
            questionsAnswered++;
        }
        if (rightAnswerThirdQuestion.isChecked()) {
            questionsAnswered++;
        }
        //http://stackoverflow.com/questions/9178253/how-to-handle-an-intent-which-does-not-have-data-at-first-call-of-an-activity
    }
}
