package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AnotherActivity extends AppCompatActivity {

    public static final String EXTRA_ANSWER = "com.example.myapp.ANSWER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);

        Intent intent = getIntent();
        String question = intent.getStringExtra(MainActivity.EXTRA_QUESTION);

        TextView textView = findViewById(R.id.textView);
        textView.setText(question);
    }

    public void sendAnswer(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        EditText editText = findViewById(R.id.editText2);
        String answer = editText.getText().toString();
        intent.putExtra(EXTRA_ANSWER, answer);
        setResult(RESULT_OK, intent);
        finish();
    }

}
