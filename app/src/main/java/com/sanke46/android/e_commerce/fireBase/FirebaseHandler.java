package com.sanke46.android.e_commerce.fireBase;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sanke46.android.e_commerce.adapter.RecyclerViewAdapter;
import com.sanke46.android.e_commerce.adapter.SalesRecyclerViewAdapter;
import com.sanke46.android.e_commerce.model.Item;

import java.util.ArrayList;

public class FirebaseHandler {

    private static final String TAG = FirebaseHandler.class.getSimpleName();
    private Item item;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("product");

    public ArrayList<Item> getAllSalesItem(String titleProduct, final ArrayList<Item> arrayOfItemProduct, final SalesRecyclerViewAdapter adapter){
        arrayOfItemProduct.clear();
        myRef.child(titleProduct).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (final DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    item = snapshot.getValue(Item.class);
                    if(item.isSales()) {
                        item.setImageUrl("http://takioki.ru/wp-content/uploads/2016/06/1-skolko-zharit-polufabrikaty.jpg");
                        arrayOfItemProduct.add(item);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        return arrayOfItemProduct;
    }

    public ArrayList<Item> getAllItem(String titleProduct, final ArrayList<Item> arrayOfItemProduct, final RecyclerViewAdapter adapter){
        arrayOfItemProduct.clear();
        myRef.child(titleProduct).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (final DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    item = snapshot.getValue(Item.class);
                    if(!item.isSales()) {
                        arrayOfItemProduct.add(item);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        return arrayOfItemProduct;

    }
}
