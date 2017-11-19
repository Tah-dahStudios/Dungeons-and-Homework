package com.example.timtr.dungeonsandhomework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import models.Item;

public class ShopMenu extends AppCompatActivity {
    private ListView shopItemListView;
    private ShopItemAdapter shopItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_menu);
        SqliteManager sqliteManager = new SqliteManager(getApplicationContext());
        int coins = sqliteManager.getItemQty(0); // get number of coins
        int numItems = sqliteManager.getNumItems();
        Toast.makeText(this, Integer.toString(numItems), Toast.LENGTH_LONG).show();

        Item[] itemList = new Item[numItems];
        // populate list
        for (int i = 1; i <= numItems; i++){
            itemList[i-1] = sqliteManager.getItem(i);
        }

        TextView coinTextView = (TextView) findViewById(R.id.coin_count);
        coinTextView.setText(String.format("Coins: %d", coins));

        shopItemListView = (ListView) findViewById(R.id.item_list);
        shopItemAdapter = new ShopItemAdapter(this, itemList);
        shopItemListView.setAdapter(shopItemAdapter);

        shopItemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Item item = (Item) adapterView.getAdapter().getItem(i);
                OpenBuyMenu(item);
            }
        });
    }

    /** Called when the user touches the button */
    private void OpenBuyMenu(Item item) {
        Intent buyItemIntent = new Intent(this, Item_Buy_Details.class);
        buyItemIntent.putExtra(Item_Buy_Details.BUY_DETAILS, item);
        startActivity(buyItemIntent);
    }


}
