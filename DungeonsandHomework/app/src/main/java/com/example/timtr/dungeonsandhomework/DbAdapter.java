package com.example.timtr.dungeonsandhomework;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Alex Chen on 2017-11-18.
 */

public class DbAdapter {
    /**
     * Class for interacting with the database. Allows updating of item counts.
     */
    dbHelper myHelper;
    public DbAdapter(Context context)
    {
        myHelper = new dbHelper(context);
    }

    public int getItemQty(int itemID) {
        // returns the quantity of the item owned
        int qty = 0;

        return qty;
    }

    public int useItem(int itemID){
        // uses the item specified by itemID, returns the quantity remaining

        return getItemQty(itemID);
    }


    static class dbHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "myDatabase";    // Database Name
        private static final String ITEM_TABLE = "items";   // Table Name
        private static final int DATABASE_Version = 1;    // Database Version
        private static final String ItemID = "IID";     // Column I (Primary Key)
        private static final String ITEM_NAME = "Name";    //Column II
        private static final String ITEM_QTY = "Qty";    //Column III
        private static final String CREATE_TABLE = "CREATE TABLE " + ITEM_TABLE +
                " (" + ItemID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ITEM_NAME + " VARCHAR(255) ," + ITEM_QTY + " INTEGER UNIQUE);";
        private static final String DROP_ITEM_TABLE = "DROP TABLE IF EXISTS " + ITEM_TABLE;
        private Context context;

        public dbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context = context;
        }

        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                ;
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
//            Message.message(context,"OnUpgrade");
                db.execSQL(DROP_ITEM_TABLE);
                onCreate(db);
            } catch (Exception e) {
//            Message.message(context,""+e);
            }
        }
    }
}
