package com.example.timtr.dungeonsandhomework;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class BossMenu extends AppCompatActivity {

    private ListView bossList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boss_menu);




//        bossList = (ListView) findViewById(R.id.bossListView);
//
//        // final ArrayList<Boss> bossList = Boss
//
//        String[] listOfBosses = new String[10];
//
//        for (int i = 0; i < 10; i++) {
//            listOfBosses[i] = String.format("Boss %d", i);
//        }
//
//        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, listOfBosses);
//        bossList.setAdapter(adapter);

//        // Add item to adapter
//        Boss newBoss = new Boss(10, 10, 10, "Boss 1");
//        adapter.add(newBoss);


        initializeViews();
    }

    private void initializeViews() {
        // Construct the data source
        ArrayList<Boss> arrayOfBosses = new ArrayList<Boss>();
        // Create the adapter to convert the array to views
        BossAdapter adapter = new BossAdapter(this, arrayOfBosses);
        // Attach the adapter to a ListView
        ListView bossListView = (ListView) findViewById(R.id.bossListView);
        bossListView.setAdapter(adapter);

        SqliteManager sqliteManager = new SqliteManager(getApplicationContext());
        int numBosses = sqliteManager.getNumBosses();
        Toast.makeText(this, Integer.toString(numBosses), Toast.LENGTH_LONG).show();

//        Boss newBoss = new Boss(10, 10, 10, "Boss 1");
//        adapter.add(newBoss);

        for (int i = 0; i < numBosses; i++){
            adapter.add(sqliteManager.getBoss(i+1));
        }

        bossListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Boss boss = (Boss) adapterView.getAdapter().getItem(i);
                OpenBattleMenu(boss);
            }
        });
    }
//
//    ListView homeFeedListView = (ListView) findViewById(R.id.home_list_view);
//    homeFeedAdapter = new HomeFeedAdapter(this, R.id.home_list_view);
//
//        homeFeedListView.setAdapter(homeFeedAdapter);
//        homeFeedListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//        @Override
//        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//            Repository repository = (Repository) adapterView.getAdapter().getItem(i);
//            launchRepositoryDetails(repository);
//        }
//    });
//}


    /** Called when the user touches the button */
    private void OpenBattleMenu(Boss boss) {
        Intent intent = new Intent(this, BattleMenu.class);
        intent.putExtra(BattleMenu.BOSS_DETAILS, boss);
        startActivity(intent);
    }
}
