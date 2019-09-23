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
    FirebaseStorage storage = FirebaseStorage.getInstance("gs://e-commerce-ddd1c.appspot.com");
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("product");
    private StorageReference mStorageRef = FirebaseStorage.getInstance().getReference();

    public ArrayList<Item> getAllSalesItem(final ArrayList<Item> arrayOfItemProduct, final SalesRecyclerViewAdapter adapter){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (final DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    System.out.println("!!!!!!!!" + snapshot);
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    item = snapshot.getValue(Item.class);
                    item.setImageUrl("http://takioki.ru/wp-content/uploads/2016/06/1-skolko-zharit-polufabrikaty.jpg");
                    System.out.println("Url : " + item.getImageUrl());

//                    System.out.println(item.getKalories() + " !!!!");
//                    System.out.println(item.getDiscontPrice() + " !!!!");
//                  ArrayList<Item> items = new ArrayList<>();
//                    mStorageRef.child("image/pizza.jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                        @Override
//                        public void onSuccess(Uri uri) {
//                            item.setImageUrl(uri.toString());
//                        }
//                    });

//                    Helper.showDialog(this);
                    mStorageRef.child("image").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
//                            Helper.dismissDialog();
                            Log.d("FireBaseHandler", uri.toString());
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                        }
                    });

                    arrayOfItemProduct.add(item);
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
