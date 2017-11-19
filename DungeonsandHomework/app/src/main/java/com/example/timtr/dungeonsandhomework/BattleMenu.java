package com.example.timtr.dungeonsandhomework;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.SystemClock;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import static java.lang.Math.min;

public class BattleMenu extends AppCompatActivity {
    public static final String BOSS_DETAILS = "Boss_Info_Details";
    private Context context;

    private Timer timer;
    private Handler timerHandler;
    private TextView timerText;

    private Handler healthRegenHandler;
    private long healthRegenInterval;
    private int healthRegen;
    private long potionDuration;
    private int bossReward;

    private long timerDuration;

    long timeRemaining, elapsedMillisecondTime, StartTime, TimeBuff, UpdateTime = 0;
    long minutesRemaining, secondsRemaining, millisecondsRemaining;
    int seconds, minutes, milliSeconds ;

    private int bossHealth;
    private int maxHealth;
    private String bossHealthText;
    private TextView bossHealthTextView;

    private final int SECONDS_WAIT = 10; //time before damage takes effect

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_menu);

        timerHandler = new Handler();
        timerText = findViewById(R.id.timerTextView);

        healthRegenHandler = new Handler();

//        bossHealth = 50;
//        healthRegen = 10;
        healthRegenInterval = 20 * 1000;
        Boss boss = (Boss) getIntent().getSerializableExtra(BOSS_DETAILS);
        bossHealth = boss.getHealth();
        maxHealth = bossHealth;
        healthRegen = boss.getHealthRegen();
        bossReward = boss.getGold();

        potionDuration = 5 * 1000;
        bossHealthText = "Health: " + bossHealth;
        bossHealthTextView = findViewById(R.id.bossHealthText);
        bossHealthTextView.setText(bossHealthText);

        Button fight_button = (Button) findViewById(R.id.fightButton);
        fight_button.setVisibility(View.VISIBLE);

        Button exit_button = (Button) findViewById(R.id.return_to_main_button);
        exit_button.setVisibility(View.GONE);
    }

    public void startTimer(View view) {
        this.timer = new Timer(5);
        Toast.makeText(this, Double.toString(this.timer.getTime()), Toast.LENGTH_LONG).show();
        String fightButtonText = "Fighting";

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.fightButton);
        textView.setText(fightButtonText);
        // disable button in fighting state
        Button fightButton = (Button) findViewById(R.id.fightButton);
        fightButton.setEnabled(false);

        this.timerDuration = SECONDS_WAIT * 1000;
        StartTime = SystemClock.uptimeMillis();
        timerHandler.postDelayed(timerRunnable, 0);

        // don't regen during fight status
        healthRegenHandler.removeCallbacks(healthRegenRunnable);
        healthRegenHandler.postDelayed(healthRegenRunnable, healthRegenInterval + SECONDS_WAIT*1000);
    }

    public void stopTimer(View view) {
        timerHandler.removeCallbacks(timerRunnable);
        TextView textView = findViewById(R.id.fightButton);
        textView.setText("Fight(25)");

        // re-enable fight button
        Button fightButton = (Button) findViewById(R.id.fightButton);
        fightButton.setEnabled(true);
    }

    private void dealDamage(int damageDealt) {
        Toast.makeText(this, "Dealt 25 damage", Toast.LENGTH_LONG).show();
        bossHealth -= damageDealt;
        bossHealthText = "Health: " + bossHealth;
        checkWin();
        bossHealthTextView.setText(bossHealthText);
        healthRegenHandler.postDelayed(healthRegenRunnable, 10 * 1000);
        TextView textView = findViewById(R.id.fightButton);
        textView.setText("Fight(25)");
        // re-enable fight button
        Button fightButton = (Button) findViewById(R.id.fightButton);
        fightButton.setEnabled(true);

        // vibrate and TODO play noise
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        v.vibrate(500);
    }

    private void checkWin() {
        if (bossHealth <= 0) {
            Toast.makeText(this, "You win!", Toast.LENGTH_LONG).show();

            finishBattle();
        }
    }

    public void usePotion(View view) {
        healthRegenHandler.removeCallbacks(healthRegenRunnable);
        healthRegenHandler.postDelayed(healthRegenRunnable, healthRegenInterval + potionDuration);
    }

    public Runnable timerRunnable = new Runnable() {
        @Override
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

    public Runnable healthRegenRunnable = new Runnable() {
            @Override
            public void run() {
//                bossHealth += healthRegen;
                bossHealth = min(bossHealth+healthRegen, maxHealth); // prevent over healing
                bossHealthText = "Health: " + bossHealth;
                bossHealthTextView.setText(bossHealthText);
                healthRegenHandler.postDelayed(this, healthRegenInterval);
            }
    };

    public void finishBattle() {
        // hide unnecessary components
        Button fight_button = (Button) findViewById(R.id.fightButton);
        fight_button.setVisibility(View.GONE);
        Button stop_button = (Button) findViewById(R.id.button5);
        stop_button.setVisibility(View.GONE);
        Button potion_button = (Button) findViewById(R.id.usePotionButton);
        potion_button.setVisibility(View.GONE);
        TextView hpTextView = (TextView) findViewById(R.id.bossHealthText);
        hpTextView.setVisibility(View.GONE);

        // reuse timer view for win message
        TextView timer = (TextView) findViewById(R.id.timerTextView);
        timer.setText(String.format("You won! You earned %d coins!", bossReward));
        // set visibility of exit button
        Button exit_button = (Button) findViewById(R.id.return_to_main_button);
        exit_button.setVisibility(View.VISIBLE);


        SharedPreferences pref = getApplicationContext().getSharedPreferences("HeldItems", 0); // 0 - for private mode
        HeldItem.addItem(pref, "coin", HeldItem.getCoins(pref) + bossReward);
    }

    public void goToMainMenu(View view) {
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }

}
