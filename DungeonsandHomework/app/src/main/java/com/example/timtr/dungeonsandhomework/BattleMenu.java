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
    int Seconds, Minutes, MilliSeconds ;



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

            UpdateTime = TimeBuff + elapsedMillisecondTime;

            Seconds = (int) (UpdateTime / 1000);

            Minutes = Seconds / 60;

            Seconds = Seconds % 60;

            MilliSeconds = (int) (UpdateTime % 1000);

            timeRemaining = timerDuration - elapsedMillisecondTime;

            timerText.setText(Long.toString(timeRemaining));

            timerHandler.postDelayed(this, 0);
        }
    };



}
