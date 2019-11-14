package com.example.primenums;

import android.app.IntentService;
import android.content.Intent;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;


public class MyIntentService extends IntentService {

    public static final String EXTRA_TOTAL_NUMBERS = "com.example.primenums.TOTAL_NUMBERS";
    public static final String EXTRA_NUMBER_LIST = "com.example.primenums.NUMBER_LIST";
    public static final String ACTION_VIEW = "com.example.primenums.ACTION_VIEW";

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {

            int number = intent.getIntExtra(MainActivity.EXTRA_NUMBER, 0);
            int primeNumsCounter = 0;
            StringBuilder resStr = new StringBuilder();

            boolean[] primeNumbers = getPrimes(number);

            for(int i = 0; i <= number; i++) {

                if(!primeNumbers[i]) {
                    if(primeNumsCounter != 0) resStr.append(", ");
                    resStr.append(i);
                    primeNumsCounter++;
                }

            }

            Intent newIntent = new Intent(ACTION_VIEW);
            newIntent.putExtra(EXTRA_NUMBER_LIST, resStr.toString());
            newIntent.putExtra(EXTRA_TOTAL_NUMBERS, primeNumsCounter);
            LocalBroadcastManager.getInstance(this).sendBroadcast(newIntent);

        }
    }

    public boolean[] getPrimes(int number) {

        boolean[] marked = new boolean[number + 1];

        marked[0] = marked[1] = true;

        for(int i = 2; i * i <= number; i++) {

            if(!marked[i]) {
                for(int j = i * i; j <= number; j += i) {
                    if(j % i == 0) marked[j] = true;
                }
            }

        }

        return marked;
    }
}
