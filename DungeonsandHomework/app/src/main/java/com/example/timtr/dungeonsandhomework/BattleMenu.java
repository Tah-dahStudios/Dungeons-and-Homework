package com.example.timtr.dungeonsandhomework;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class BattleMenu extends AppCompatActivity {

    private Context context;

    private Timer timer;
    private Handler timerHandler;
    private TextView timerText;

    private long timerDuration;

    long timeRemaining, elapsedMillisecondTime, StartTime, TimeBuff, UpdateTime = 0;
    long minutesRemaining, secondsRemaining, millisecondsRemaining;
    int seconds, minutes, milliSeconds ;

    private int bossHealth;
    private String bossHealthText;
    private TextView bossHealthTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_menu);

        timerHandler = new Handler();
        timerText = findViewById(R.id.timerTextView);

        bossHealth = 50;
        bossHealthText = "Health: " + bossHealth;
        bossHealthTextView = findViewById(R.id.bossHealthText);
        bossHealthTextView.setText(bossHealthText);
    }

    public void startTimer(View view) {
        this.timer = new Timer(5);
        Toast.makeText(this, Double.toString(this.timer.getTime()), Toast.LENGTH_LONG).show();
        String fightButtonText = "Fighting";

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.fightButton);
        textView.setText(fightButtonText);

        this.timerDuration = 10 * 1000;
        StartTime = SystemClock.uptimeMillis();
        timerHandler.postDelayed(timerRunnable, 0);
    }

    public void stopTimer(View view) {
        timerHandler.removeCallbacks(timerRunnable);
    }

    private void dealDamage(int damageDealt) {
        Toast.makeText(this, "Dealt 25 damage", Toast.LENGTH_LONG).show();
        bossHealth -= damageDealt;
        bossHealthText = "Health: " + bossHealth;
        bossHealthTextView.setText(bossHealthText);
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

            String timerString = "" + minutes + ":"
                    + seconds + ":"
                    + milliSeconds;

            timerText.setText(timerString);

            timerHandler.postDelayed(this, 0);

            if (millisecondsRemaining <= 0) {
                timerString = "00:00:00";
                timerText.setText(timerString);
                timerHandler.removeCallbacks(timerRunnable);
                dealDamage(25);
            }
        }
    };
}
