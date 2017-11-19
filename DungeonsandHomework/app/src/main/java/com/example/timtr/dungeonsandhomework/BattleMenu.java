package com.example.timtr.dungeonsandhomework;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class BattleMenu extends AppCompatActivity {

    private Timer timer;
    private Handler timerHandler;
    private TextView timerText;

    private long timerDuration;

    long timeRemaining, elapsedMillisecondTime, StartTime, TimeBuff, UpdateTime = 0;
    long minutesRemaining, secondsRemaining, millisecondsRemaining;
    int seconds, minutes, milliSeconds ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_menu);

        timerHandler = new Handler();
        timerText = findViewById(R.id.timerTextView);
    }

    public void attack(View view) {
        this.timer = new Timer(5);
        Toast.makeText(this, Double.toString(this.timer.getTime()), Toast.LENGTH_LONG).show();
        String fightButtonText = "Fighting";

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.fightButton);
        textView.setText(fightButtonText);

        this.timerDuration = 25 * 60 * 1000;
        StartTime = SystemClock.uptimeMillis();
        timerHandler.postDelayed(timerRunnable, 0);
    }

    public void stopTimer(View view) {
        timerHandler.removeCallbacks(timerRunnable);
    }

    public Runnable timerRunnable = new Runnable() {

        public void run() {

            elapsedMillisecondTime = SystemClock.uptimeMillis() - StartTime;

            millisecondsRemaining = timerDuration - elapsedMillisecondTime;
            secondsRemaining = millisecondsRemaining / 1000;
            minutesRemaining = secondsRemaining / 60;

            milliSeconds = (int) millisecondsRemaining % 1000;
            seconds = (int) secondsRemaining % 60;
            minutes = (int) minutesRemaining;

            timerText.setText("" + minutes + ":"
                    + String.format("%02d", seconds) + ":"
                    + String.format("%03d", milliSeconds));

            timerHandler.postDelayed(this, 0);
        }
    };



}
