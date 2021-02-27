package com.my.example.countdowntimer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView timeTextView;

    private MyCountDownTimer myCountDownTimer;
    private long timeElapsed = 0;
    private boolean isStarted = false;

    private final long startMilis = Long.MAX_VALUE;
    private final long interval = 1 * 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeTextView = findViewById(R.id.timeTextView);

        myCountDownTimer = new MyCountDownTimer(startMilis, interval);
    }

    public void goClick(View view) {
        if(!isStarted) {
            myCountDownTimer.start();
            isStarted = true;
        }
    }

    public void resetClick(View view) {
        if(isStarted) {
            myCountDownTimer.cancel();
            isStarted = false;
            timeElapsed = 0;

            timeTextView.setText("00:00:00");
        }
    }

    private class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long startMillis, long interval) {
            super(startMillis, interval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            timeElapsed += interval / 1000;
            int seconds = (int)(timeElapsed % 60);
            int minutes = (int)(timeElapsed / 60);
            int hours = minutes % 60;
            timeTextView.setText(String.format(Locale.getDefault(),"%02d:%02d:%02d", hours, minutes, seconds));
        }

        @Override
        public void onFinish() {

        }
    }
}