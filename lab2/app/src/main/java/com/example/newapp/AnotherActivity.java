package com.example.newapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AnotherActivity extends AppCompatActivity {

    public static final String EXTRA_ANSWER = "com.example.newapp.ANSWER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_another);

        Intent intent = getIntent();
        String question = intent.getStringExtra(MainActivity.EXTRA_QUESTION);

        TextView textView = findViewById(R.id.received_message);
        textView.setText(question);
    }

    public void sendReply(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        EditText editText = findViewById(R.id.edit_message);
        String answer = editText.getText().toString();
        intent.putExtra(EXTRA_ANSWER, answer);
        this.setResult(RESULT_OK, intent);
        this.finish();
    }

}
