package com.example.timtr.dungeonsandhomework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import models.Item;

public class Item_Buy_Details extends AppCompatActivity {
    public static final String BUY_DETAILS = "ItemToBuy_Details";
    private int qty = 0;
    SqliteManager sqliteManager;
    Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        item = (Item) getIntent().getSerializableExtra(BUY_DETAILS);

        setContentView(R.layout.activity_item__buy__details);
        sqliteManager = new SqliteManager(this);
        int coins = sqliteManager.getItemQty(0); // get number of coins

        int maxBuyable = coins/item.getPrice();

        // seek bar updates
        SeekBar buy_qty_seekbar = (SeekBar) findViewById(R.id.amount_to_buy);
        buy_qty_seekbar.setProgress(0); // set default value to 0
        buy_qty_seekbar.setMax(maxBuyable); // set seekbar maximum value
        TextView buy_qty_textview = (TextView) findViewById(R.id.buy_qty_text);
        buy_qty_textview.setText("" + buy_qty_seekbar.getProgress());
        TextView priceTextView = (TextView) findViewById(R.id.buy_cost_text);
        priceTextView.setText(String.format("Total price: %d", qty*item.getPrice()));

        buy_qty_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                TextView buy_qty_textview = (TextView) findViewById(R.id.buy_qty_text);
                buy_qty_textview.setText("" + seekBar.getProgress());
                qty = seekBar.getProgress();

                TextView priceTextView = (TextView) findViewById(R.id.buy_cost_text);
                priceTextView.setText(String.format("Total price: %d", qty*item.getPrice()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                ; //not sure if method is needed
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                ; //not sure if method is needed needed
            }
        });

        TextView coin_count_text = (TextView) findViewById(R.id.coins_owned);
        coin_count_text.setText(String.format("Coins owned: %d", coins));

    }


}
