package com.sanke46.android.e_commerce.fireBase;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sanke46.android.e_commerce.adapter.SalesRecyclerViewAdapter;
import com.sanke46.android.e_commerce.model.Item;

import java.util.ArrayList;

public class FirebaseHandler {

    private static final String TAG = FirebaseHandler.class.getSimpleName();

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("product");

    public ArrayList<Item> getAllSalesItem(final ArrayList<Item> arrayOfItemProduct, final SalesRecyclerViewAdapter adapter){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    System.out.println("!!!!!!!!" + snapshot);
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    Item item = snapshot.getValue(Item.class);
//                ArrayList<Item> items = new ArrayList<>();
                    arrayOfItemProduct.add(item);
                    Log.d(TAG, "Value is: " + arrayOfItemProduct.size());
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
