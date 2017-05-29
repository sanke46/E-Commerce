package com.sanke46.android.e_commerce;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilafedoseev on 11.02.17.
 */

public class DataBaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "Food";
    private static final String TABLE_CONTACTS = "Food";

    private static final String KEY_ID = "id";
    private static final String KEY_IMAGE = "imageId";
    private static final String KEY_NAME = "name";
    private static final String KEY_COMM = "comments";
    private static final String KEY_PRICE = "price";
    private static final String KEY_BUT_1 = "button1";
    private static final String KEY_BUT_2 = "button2";

    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CreateTableSql("Pi"));
        db.execSQL(CreateTableSql("Su"));
        db.execSQL(CreateTableSql("Dr"));
    }

    public String CreateTableSql(String name){
        String table = "CREATE TABLE " + name + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_IMAGE + " TEXT,"
                + KEY_NAME + " TEXT,"
                + KEY_COMM + " TEXT,"
                + KEY_PRICE + " TEXT,"
                + KEY_BUT_1 + " TEXT,"
                + KEY_BUT_2 + " TEXT" + ")";
        return table;
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }

    public void addItem(String name,Item item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_IMAGE, item.getImageId());
        values.put(KEY_NAME, item.getName());
        values.put(KEY_COMM, item.getComment());
        values.put(KEY_PRICE, item.getPrice());
        values.put(KEY_BUT_1, item.getButtonOne());
        values.put(KEY_BUT_2, item.getButtonTwo());

        db.insert(name, null, values);
        db.close();
    }

    public Item getItem(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
                        KEY_IMAGE, KEY_NAME, KEY_COMM, KEY_PRICE, KEY_BUT_1, KEY_BUT_2 }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        assert cursor != null;
        Item item = new Item(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)), cursor.getString(2),
                cursor.getString(3), Integer.parseInt(cursor.getString(4)), cursor.getString(5),cursor.getString(6));

        return item;
    }

    public List<Item> getAllItem(String name) {
        List<Item> itemList = new ArrayList<Item>();
        String selectQuery = "SELECT  * FROM " + name;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Item item = new Item();
                item.setId(Integer.parseInt(cursor.getString(0)));
                item.setImageId(Integer.parseInt(cursor.getString(1)));
                item.setName(cursor.getString(2));
                item.setComment(cursor.getString(3));
                item.setPrice(Integer.parseInt(cursor.getString(4)));
                item.setButtonOne(cursor.getString(5));
                item.setButtonTwo(cursor.getString(6));

                itemList.add(item);
            } while (cursor.moveToNext());
        }

        return itemList;
    }

    public int getItemCounter() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    public int updateItem(String name, Item item) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_IMAGE, item.getImageId());
        values.put(KEY_NAME, item.getName());
        values.put(KEY_COMM, item.getComment());
        values.put(KEY_PRICE, item.getPrice());
        values.put(KEY_BUT_1, item.getButtonOne());
        values.put(KEY_BUT_2, item.getButtonTwo());

        return db.update(name, values, KEY_ID + " = ?",
                new String[] { String.valueOf(item.getId()) });
    }

    public void deleteItem(String name, Item item) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(name, KEY_ID + " = ?", new String[] { String.valueOf(item.getId()) });
        db.close();
    }

    public void deleteAll(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(name, null, null);
        db.close();
    }
}
