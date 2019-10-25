package com.example.kekapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "MainActivity";

    public static final String EXTRA_QUESTION = "com.example.kekapp.QUESTION";
    public static final int ANSWER_REQUEST_CODE = 1;

    private Button okButton;
    private EditText question;
    private View.OnClickListener buttonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            sendQuestion(v);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.activity_main);
        this.setContentView(R.layout.fragment_main);

        question = findViewById(R.id.edit_message);
        question.setHint(R.string.question_message);

        okButton = findViewById(R.id.button_ok);
        okButton.setOnClickListener(buttonOnClickListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == ANSWER_REQUEST_CODE) {
            if(resultCode == RESULT_OK) {
                TextView textView = findViewById(R.id.message_type);
                String answer = data.getStringExtra(AnotherActivity.EXTRA_ANSWER);
                textView.setText(answer);
            }
        }
    }

    public void sendQuestion(View view) {
        Intent intent = new Intent(this, AnotherActivity.class);
        EditText editText = findViewById(R.id.edit_message);
        String question = editText.getText().toString();
        intent.putExtra(EXTRA_QUESTION, question);
        this.startActivityForResult(intent, ANSWER_REQUEST_CODE);
    }
}
