package com.sanke46.android.e_commerce.fireBase;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sanke46.android.e_commerce.adapter.HistoryListAdapter;
import com.sanke46.android.e_commerce.adapter.RecyclerViewAdapter;
import com.sanke46.android.e_commerce.adapter.SalesRecyclerViewAdapter;
import com.sanke46.android.e_commerce.model.Chat;
import com.sanke46.android.e_commerce.model.Item;
import com.sanke46.android.e_commerce.model.Order;
import com.sanke46.android.e_commerce.ui.navigation.OrderActivity;

import java.util.ArrayList;

public class FirebaseHandler {

    private static final String TAG = FirebaseHandler.class.getSimpleName();
    private Item item;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("product");

    private DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
//    private DatabaseReference orderIdRef = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth userAuth = FirebaseAuth.getInstance();
    private FirebaseUser user = userAuth.getCurrentUser();
    private String userId = user.getUid();


    /** MainActivity : menu **/
    public ArrayList<Item> getAllSalesItem(String titleProduct,
                                           final ArrayList<Item> arrayOfItemProduct,
                                           final SalesRecyclerViewAdapter adapter,
                                           final ProgressBar progressBar,
                                           final LinearLayout mContentLayout){
        arrayOfItemProduct.clear();
        myRef.child(titleProduct).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (progressBar != null) {
                    mContentLayout.setVisibility(LinearLayout.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }

                for (final DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    item = snapshot.getValue(Item.class);
                    if(item.isSales()) {
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

    /** Profile History Activity **/

    public ArrayList<Order> getHistoryOrder(final ArrayList<Order> arrayHistory, final HistoryListAdapter adapter){
        arrayHistory.clear();
        dbRef.child("users").child(userId).child("history").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (final DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Order order = snapshot.getValue(Order.class);
                    arrayHistory.add(order);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        return arrayHistory;
    }
}
