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

public class DataBaseHandler extends SQLiteOpenHelper implements DBHandlerInterface   {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ItemFood";
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
        String CREATE_ITEM_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_IMAGE + " TEXT,"
                + KEY_NAME + " TEXT,"
                + KEY_COMM + " TEXT,"
                + KEY_PRICE + " TEXT,"
                + KEY_BUT_1 + " TEXT,"
                + KEY_BUT_2 + " TEXT" + ")";
        db.execSQL(CREATE_ITEM_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }

    @Override
    public void addItem(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_IMAGE, item.getImageId());
        values.put(KEY_NAME, item.getName());
        values.put(KEY_COMM, item.getComment());
        values.put(KEY_PRICE, item.getPrice());
        values.put(KEY_BUT_1, item.getButtonOne());
        values.put(KEY_BUT_2, item.getButtonTwo());

        db.insert(TABLE_CONTACTS, null, values);
        db.close();
    }

    @Override
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

    @Override
    public List<Item> getAllItem() {
        List<Item> itemList = new ArrayList<Item>();
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

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

    @Override
    public int getItemCounter() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    @Override
    public int updateItem(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_IMAGE, item.getImageId());
        values.put(KEY_NAME, item.getName());
        values.put(KEY_COMM, item.getComment());
        values.put(KEY_PRICE, item.getPrice());
        values.put(KEY_BUT_1, item.getButtonOne());
        values.put(KEY_BUT_2, item.getButtonTwo());

        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(item.getId()) });
    }

    @Override
    public void deleteItem(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?", new String[] { String.valueOf(item.getId()) });
        db.close();
    }

    @Override
    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, null, null);
        db.close();
    }
}
