package com.example.timtr.dungeonsandhomework;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by Alex Chen on 2017-11-19.
 */

@Entity(indices = {@Index(value = {"item_name"}, unique = true)})
public class HeldItem {
    @PrimaryKey
    private int android_iid;

    @ColumnInfo(name = "item_name")
    private String itemName;

    @ColumnInfo(name = "qty")
    private int numberHeld;

    public int getAndroid_iid() {
        return android_iid;
    }

    public void setAndroid_iid(int android_iid) {
        this.android_iid = android_iid;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getNumberHeld() {
        return numberHeld;
    }

    public void setNumberHeld(int numberHeld) {
        this.numberHeld = numberHeld;
    }
}