package com.example.kekapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AnotherActivity extends AppCompatActivity {

    public static final String EXTRA_ANSWER = "com.example.kekapp.ANSWER";

    private Button okButton;
    private EditText reply;
    private View.OnClickListener buttonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            sendReply(v);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_another);
        this.setContentView(R.layout.fragment_main);

        Intent intent = getIntent();
        String question = intent.getStringExtra(MainActivity.EXTRA_QUESTION);

        reply = findViewById(R.id.edit_message);
        reply.setHint(R.string.reply_message);

        okButton = findViewById(R.id.button_ok);
        okButton.setOnClickListener(buttonOnClickListener);

        TextView textView = findViewById(R.id.message_type);
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
