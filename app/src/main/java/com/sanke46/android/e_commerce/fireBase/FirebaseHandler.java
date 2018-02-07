package com.sanke46.android.e_commerce.fireBase;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sanke46.android.e_commerce.model.Item;

import java.util.ArrayList;

public class FirebaseHandler {

    private static final String TAG = FirebaseHandler.class.getSimpleName();

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("product");

    public void ref(final ArrayList<Item> arrayOfItemProduct){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                   myRef.child(snapshot.getKey()).addValueEventListener(new ValueEventListener() {
                       @Override
                       public void onDataChange(DataSnapshot dataSnapshot2) {
                           arrayOfItemProduct.add(dataSnapshot2.getValue(Item.class));
                           System.out.println(arrayOfItemProduct.get(0).getComeFrom() + "!!!!!!!");
                       }

                       @Override
                       public void onCancelled(DatabaseError databaseError) {

                       }
                   });
                }

                Log.d(TAG, "Value is: " );
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());


            }
        });
    }
}
