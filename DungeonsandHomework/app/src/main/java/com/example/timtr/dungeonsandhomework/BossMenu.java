package com.example.timtr.dungeonsandhomework;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class BossMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boss_menu);
    }

    /** Called when the user touches the button */
    public void OpenBossMenu(View view) {
        Intent intent = new Intent(this, BattleMenu.class);
        startActivity(intent);
    }

}
