package com.example.timtr.dungeonsandhomework;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//import android.database.sqlite.SQLiteCursor;
import android.database.Cursor;

/**
 * Created by Alex Chen on 2017-11-18.
 */

public class ItemManager {
    /**
     * Class for interacting with the database to manage items.
     */
    dbHelper myHelper;


    public ItemManager(Context context)
    {
        myHelper = new dbHelper(context);
    }

    public int getItemQty(int itemID) {
        // returns the quantity of the item owned
        int qty = 0;
        String query = "SELECT " + myHelper.ITEM_QTY +" FROM " + myHelper.ITEM_TABLE + " WHERE " +
                myHelper.ItemID + " = " + Integer.toString(itemID) + ";";
        SQLiteDatabase db = this.myHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToNext()){
            qty = cursor.getInt(0);
        }
        // no else is needed, if this move fails, the item is not found, so the qty will be 0

        db.close();
        return qty;
    }

    public int useItem(int itemID){
        // uses the item specified by itemID, returns the quantity remaining or
        // -1 if item was not used because there is none owned
        int qty = getItemQty(itemID);

        if (qty <= 0){
            return -1;
        }

        else{ //qty > 0, so there is one held to be used
            String query = "";
            SQLiteDatabase db = this.myHelper.getWritableDatabase();
            qty--;
            // TODO: Update the value of qty in the database
            ContentValues values = new ContentValues();
            values.put(myHelper.ITEM_QTY, qty);
            String[] whereArgs= {Integer.toString(itemID)};
            String whereClause = myHelper.ItemID + " = ?";
            db.update(myHelper.ITEM_TABLE, values, whereClause, whereArgs);

            db.close();
        }

        return qty;
    }


    static class dbHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "myDatabase";    // Database Name
        private static final String ITEM_TABLE = "items";   // Table Name
        private static final int DATABASE_Version = 1;    // Database Version
        private static final String ItemID = "iid";     // Column I (Primary Key)
        private static final String ITEM_NAME = "name";    //Column II
        private static final String ITEM_QTY = "qty";    //Column III
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
//                SQLiteConfig config = new SQLiteConfig();
//                config.enforceForeignKeys(true);
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
