package com.example.timtr.dungeonsandhomework;

import android.content.SharedPreferences;

public class HeldItem {
    public static int getItemQty(SharedPreferences s, String itemName){
        return s.getInt(itemName, 0);
    }

    public static int getCoins(SharedPreferences s) {
        return s.getInt("coin", 0);
    }

    public static int useItem(SharedPreferences s, String itemName, int amount){
        SharedPreferences.Editor edit = s.edit();
        edit.putInt(itemName, getItemQty(s, itemName) - amount);

        edit.commit();
        return s.getInt(itemName, 0);
    }

    public static int addItem(SharedPreferences s, String itemName, int amount){
        SharedPreferences.Editor edit = s.edit();
        edit.putInt(itemName, getItemQty(s, itemName) + amount);

        edit.commit();
        return s.getInt(itemName, 0);
    }
}