package com.example.timtr.dungeonsandhomework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ShopMenu extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_menu);
        ItemManager itemManager = new ItemManager(getApplicationContext());
        int coins = itemManager.getItemQty(0); // get number of coins

        TextView coinTextView = (TextView) findViewById(R.id.coin_count);
        coinTextView.setText(String.format("Coins: %d", coins));


    }
}
