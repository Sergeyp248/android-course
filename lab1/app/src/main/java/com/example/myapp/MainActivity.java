package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_QUESTION = "com.example.myapp.QUESTION";
    public static final int ANSWER_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendQuestion(View view) {
        Intent intent = new Intent(this, AnotherActivity.class);
        EditText editText = findViewById(R.id.editText);
        String question = editText.getText().toString();
        intent.putExtra(EXTRA_QUESTION, question);
        startActivityForResult(intent, ANSWER_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == ANSWER_REQUEST_CODE) {
            if(resultCode == RESULT_OK) {
                TextView textView = findViewById(R.id.textView);
                if(data == null) return;
                String answer = data.getStringExtra(AnotherActivity.EXTRA_ANSWER);
                textView.setText(answer);
            }
        }
    }
}
