package com.example.primenums;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import javax.xml.transform.Templates;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_NUMBER = "com.example.primenums.NUMBER";

    private EditText numberFieldView;
    private TextView totalNumbersView;
    private TextView numberList;
    private Button computeButton;
    private View.OnClickListener buttonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            prepareAndStartService(v);
        }
    };
    private BroadcastReceiver numberListReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String totalNums = context.getResources().getString(R.string.showTotalPrimes)
                    + " "
                    + intent.getIntExtra(MyIntentService.EXTRA_TOTAL_NUMBERS, 0);
            String numStr = intent.getStringExtra(MyIntentService.EXTRA_NUMBER_LIST);
            totalNumbersView.setText(totalNums);
            numberList.setText(numStr);
            computeButton.setEnabled(true);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        this.numberFieldView = findViewById(R.id.num_field);
        this.totalNumbersView = findViewById(R.id.total_nums);
        this.numberList = findViewById(R.id.num_list);
        this.computeButton = findViewById(R.id.compute_btn);

        this.computeButton.setOnClickListener(this.buttonOnClickListener);

        LocalBroadcastManager.getInstance(this)
                .registerReceiver(numberListReceiver, new IntentFilter(MyIntentService.ACTION_VIEW));
    }

    public void prepareAndStartService(View view){
        Intent intentService = new Intent(this, MyIntentService.class);
        intentService.putExtra(EXTRA_NUMBER, Integer.valueOf(this.numberFieldView.getText().toString()));
        this.computeButton.setEnabled(false);
        this.startService(intentService);
    }
}
