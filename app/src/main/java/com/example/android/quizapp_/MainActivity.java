package com.example.android.quizapp_;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.quizapp_.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        *  Prevent keyboard from poping up for Edit Text
        */
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        /*
        *  Go Forward to 1st Activity
        */
        Button start = (Button) findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityQuestionsFirst.class);
                EditText editText = (EditText) findViewById(R.id.type_name);
                String editTextContent = editText.getText().toString();

                if (!editTextContent.equals("")) {
                    intent.putExtra("name", editTextContent);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "You haven't typed any name!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
