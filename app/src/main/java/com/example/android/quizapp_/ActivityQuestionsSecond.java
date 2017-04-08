package com.example.android.quizapp_;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ActivityQuestionsSecond extends AppCompatActivity {

    int answers;
    EditText rightAnswerSixthQuestion;
    String rightAnswerSixthQuestionContent;
    CheckBox firstRightAnswerCheckbox, secondRightAnswerCheckbox;

    // Disable back button
    @Override
    public void onBackPressed() { return; }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_second);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        Button next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityQuestionsSecond.this, ResultsActivity.class);
                String name = getIntent().getStringExtra("name");
                answers = getIntent().getIntExtra("questionsAnswered", 0);

                rightAnswerSixthQuestion = (EditText) findViewById(R.id.right_answer_sixth_question);
                rightAnswerSixthQuestionContent = rightAnswerSixthQuestion.getText().toString().toLowerCase();

                firstRightAnswerCheckbox = (CheckBox)findViewById(R.id.first_right_answer_checkbox);
                secondRightAnswerCheckbox = (CheckBox)findViewById(R.id.second_right_answer_checkbox);

                //Check if 5th answer is empty
                if (checkIfLast3AnswersAreEmpty()) {
                    return;
                }

                // Check correct answers, avoid incorrect sum when going back from different activities
                if (getIntent().getExtras() == null) {
                    checkScoreForLast3Answers();
                } else {
                    answers = getIntent().getIntExtra("questionsAnswered", 0);
                    checkScoreForLast3Answers();
                }

                intent.putExtra("name", name);
                intent.putExtra("answers", answers);
                startActivity(intent);
            }
        });
    }

    private void checkScoreForLast3Answers() {
        RadioButton rightAnswerFifthQuestion = (RadioButton) findViewById(R.id.right_answer_fifth_question);

        if (firstRightAnswerCheckbox.isChecked() && secondRightAnswerCheckbox.isChecked()){
            answers++;
        }
        if (rightAnswerFifthQuestion.isChecked()) {
            answers++;
        }
        if (rightAnswerSixthQuestionContent.equals("syd barrett")) {
            answers++;
        }
    }

    private boolean checkIfLast3AnswersAreEmpty(){
        RadioGroup fifthQuestionGroup = (RadioGroup) findViewById(R.id.fifth_question_group);
        int fifthQuestionGroupSelected = fifthQuestionGroup.getCheckedRadioButtonId();

        if (fifthQuestionGroupSelected == -1 || rightAnswerSixthQuestionContent.equals("") ||
                !firstRightAnswerCheckbox.isChecked() || !secondRightAnswerCheckbox.isChecked()) {
            Toast.makeText(getApplicationContext(), "You have unchecked options!", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
}