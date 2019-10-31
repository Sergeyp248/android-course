package com.example.kekapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AnotherActivity extends AppCompatActivity {

    public static final String EXTRA_ANSWER = "com.example.kekapp.ANSWER";
    public static final String EXTRA_EXIT = "com.example.kekapp.EXIT";

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                return true;
            case R.id.help:
                return true;
            case R.id.exit:
                ExitDialog exitDialog = new ExitDialog();
                exitDialog.show(getSupportFragmentManager(), "dialog");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void sendReply(View view) {
        Intent intent = new Intent();
        EditText editText = findViewById(R.id.edit_message);
        String answer = editText.getText().toString();
        intent.putExtra(EXTRA_ANSWER, answer);
        this.setResult(RESULT_OK, intent);
        this.finish();
    }

    @Override
    public void finishAndRemoveTask() {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_EXIT, "exit");
        this.setResult(RESULT_OK, intent);
        super.finishAndRemoveTask();
    }

}
