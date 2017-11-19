package com.example.timtr.dungeonsandhomework;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class BossMenu extends AppCompatActivity {

    private ListView bossList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boss_menu);

        bossList = (ListView) findViewById(R.id.bossListView);

        // final ArrayList<Boss> bossList = Boss

        String[] listOfBosses = new String[10];

        for (int i = 0; i < 10; i++) {
            listOfBosses[i] = String.format("Boss %d", i);
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, listOfBosses);
        bossList.setAdapter(adapter);

    }

    /** Called when the user touches the button */
    public void OpenBattleMenu(View view) {
        Intent intent = new Intent(this, BattleMenu.class);
        startActivity(intent);
    }

}
