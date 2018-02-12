package com.sanke46.android.e_commerce.fireBase;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.sanke46.android.e_commerce.adapter.SalesRecyclerViewAdapter;
import com.sanke46.android.e_commerce.model.Item;

import java.util.ArrayList;

public class FirebaseHandler {

    private static final String TAG = FirebaseHandler.class.getSimpleName();
    private Item item;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("product");
    private StorageReference mStorageRef = FirebaseStorage.getInstance().getReference();

    public ArrayList<Item> getAllSalesItem(final ArrayList<Item> arrayOfItemProduct, final SalesRecyclerViewAdapter adapter){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    System.out.println("!!!!!!!!" + snapshot);
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
//                    item = snapshot.getValue(Item.class);

//                    System.out.println(item.getKalories() + " !!!!");
//                    System.out.println(item.getDiscontPrice() + " !!!!");
//                  ArrayList<Item> items = new ArrayList<>();


                    mStorageRef.child("game.jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            // Got the download URL for 'users/me/profile.png'
                            System.out.println(uri + " ");
//                            System.out.println(item.getImageId() + "!!!!!!");
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle any errors
                        }
                    });
//                    arrayOfItemProduct.add(item);
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
