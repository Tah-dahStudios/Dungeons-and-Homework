package com.example.timtr.dungeonsandhomework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    /** Called when the user touches the button */
    public void OpenBossMenu(View view) {
        Toast.makeText(this, "open boss menu", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, BossMenu.class);
        startActivity(intent);
    }

    public void testTimer(View view) {
        Timer testTimer = new Timer(5);
        Toast.makeText(this, Double.toString(testTimer.getTime()), Toast.LENGTH_LONG).show();
    }


}
