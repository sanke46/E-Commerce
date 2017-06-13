package com.sanke46.android.e_commerce;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilafedoseev on 13.06.17.
 */

public class DataOrderBaseHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Order";
    private static final String TABLE_CONTACTS = "Order";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_CITY = "city";
    private static final String KEY_STREET = "street";
    private static final String KEY_HOUSE_NUMBER = "house";
    private static final String KEY_FLAT = "flat";
    private static final String KEY_PHONE_NUMBER = "phone";

    public DataOrderBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CreateTableSql(DATABASE_NAME));
    }

    private String CreateTableSql(String order) {
        String table = "CREATE TABLE" + DATABASE_NAME + "("
                + KEY_ID + "INTEGER PRIMARY KEY,"
                + KEY_NAME + "TEXT"
                + KEY_CITY + "TEXT"
                + KEY_STREET + "TEXT"
                + KEY_HOUSE_NUMBER + "TEXT"
                + KEY_FLAT + "TEXT"
                + KEY_PHONE_NUMBER + "TEXT";
        return table;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }

    public void addOrder(Order order) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, order.getEtnS());
        values.put(KEY_CITY, order.getEtcS());
        values.put(KEY_STREET, order.getEtsS());
        values.put(KEY_HOUSE_NUMBER, order.getEthnS());
        values.put(KEY_FLAT, order.getEtfS());
        values.put(KEY_PHONE_NUMBER, order.getEtpnS());

        db.insert(DATABASE_NAME, null, values);
        db.close();
    }

    public Order getOrder(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
                        KEY_NAME, KEY_CITY, KEY_STREET, KEY_HOUSE_NUMBER, KEY_FLAT, KEY_PHONE_NUMBER }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        assert cursor != null;
        Order order = new Order(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2),
                cursor.getString(3), cursor.getString(4), cursor.getString(5),cursor.getString(6));

        return order;
    }

    public List<Order> getAllOrder() {
        List<Order> orderList = new ArrayList<Order>();
        String selectQuery = "SELECT  * FROM " + DATABASE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Order order = new Order();
                order.setId(Integer.parseInt(cursor.getString(0)));
                order.setEtnS(cursor.getString(1));
                order.setEtcS(cursor.getString(2));
                order.setEtsS(cursor.getString(3));
                order.setEthnS(cursor.getString(4));
                order.setEtfS(cursor.getString(5));
                order.setEtpnS(cursor.getString(6));

                orderList.add(order);
            } while (cursor.moveToNext());
        }

        return orderList;
    }

    public int getOrderCounter() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    public int updateOrder(Order order) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, order.getEtnS());
        values.put(KEY_CITY, order.getEtcS());
        values.put(KEY_STREET, order.getEtsS());
        values.put(KEY_HOUSE_NUMBER, order.getEthnS());
        values.put(KEY_FLAT, order.getEtfS());
        values.put(KEY_PHONE_NUMBER, order.getEtpnS());

        return db.update(DATABASE_NAME, values, KEY_ID + " = ?",
                new String[] { String.valueOf(order.getId()) });
    }

    public void deleteOrder(Order order) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DATABASE_NAME, KEY_ID + " = ?", new String[] { String.valueOf(order.getId()) });
        db.close();
    }

    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DATABASE_NAME, null, null);
        db.close();
    }
}
