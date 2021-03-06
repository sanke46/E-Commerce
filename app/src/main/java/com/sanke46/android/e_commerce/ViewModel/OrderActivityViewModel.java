package com.sanke46.android.e_commerce.ViewModel;

import android.content.Intent;
import android.util.Log;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sanke46.android.e_commerce.model.Item;
import com.sanke46.android.e_commerce.model.Order;
import com.sanke46.android.e_commerce.model.UserProfile;
import com.sanke46.android.e_commerce.ui.navigation.BasketActivity;
import com.sanke46.android.e_commerce.ui.navigation.OrderActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderActivityViewModel {

    private static final String TAG = OrderActivity.class.getSimpleName();
    public static final String NAME_ACTIVITY = "Order";
    public static final String DONE = "Order DONE";

    private OrderActivity orderActivity;
    private DatabaseReference dbRef;
    DatabaseReference orderIdRef;
    private String orderId;
    private FirebaseAuth userAuth;
    private String userId;

    public BasketActivityViewModel basketViewModel = new BasketActivityViewModel();
    public List<Item> itemList = basketViewModel.getBasketItem();
    public Order order = new Order();

    public OrderActivityViewModel(OrderActivity orderActivity) {
        this.orderActivity = orderActivity;
        dbRef = FirebaseDatabase.getInstance().getReference().child("users");
        orderIdRef = FirebaseDatabase.getInstance().getReference().child("currentOrder");
        userAuth = FirebaseAuth.getInstance();
        FirebaseUser user = userAuth.getCurrentUser();
        userId = user.getUid();
    }

    // add order to firebase
    public void addOrderUserFireBase(EditText phone, EditText city, EditText street, EditText house, EditText flat) {
        long yourmilliseconds = System.currentTimeMillis();
        SimpleDateFormat currentTime = new SimpleDateFormat("MMM dd,yyyy HH:mm");
        Date resultdate = new Date(yourmilliseconds);

        order.setPayment("Cach");
        order.setTime(resultdate.toString());
        order.setPhone(phone.getText().toString());
        order.setListOfBuyProducts(itemList);
        order.setTotalPrice(getTotalPrice(itemList));
        order.setAdress(city.getText().toString() + " " + street.getText().toString() + " " + house.getText().toString() + " " + flat.getText().toString());

        dbRef.child(userId).child("history").child(String.valueOf(currentTime.format(resultdate))).child("payment").setValue(order.getPayment());
        dbRef.child(userId).child("history").child(String.valueOf(currentTime.format(resultdate))).child("totalPrice").setValue(order.getTotalPrice());
        dbRef.child(userId).child("history").child(String.valueOf(currentTime.format(resultdate))).child("adress").setValue(order.getAdress());
        dbRef.child(userId).child("history").child(String.valueOf(currentTime.format(resultdate))).child("phone").setValue(order.getPhone());
        dbRef.child(userId).child("history").child(String.valueOf(currentTime.format(resultdate))).child("time").setValue(order.getTime());
        dbRef.child(userId).child("history").child(String.valueOf(currentTime.format(resultdate))).child("items").setValue(order.getListOfBuyProducts());

        basketViewModel.cleanBasket();
//        orderActivity.getApplicationContext().startActivity(new Intent(orderActivity.getApplicationContext(), Payment.class));
    }

    public String getTotalPrice(List<Item> itemList) {
        int resultPrice = 0;

        for (Item item : itemList) {
            resultPrice += item.getPrice();
        }

        return String.valueOf(resultPrice);
    }


    // method to find information about user
    public void InformationAboutUser() {
        dbRef.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                UserProfile userProfile = new UserProfile();
                String[] arrayTitlesAboutUser = {"name", "phone", "city", "street", "house", "flat"};
                ArrayList<String> dbAboutUser = new ArrayList<>();

                for (String title : arrayTitlesAboutUser) {
                    if (title.equals("name") || title.equals("phone")) {
                        try {
                            dbAboutUser.add(dataSnapshot.child(title).getValue().toString());
                        } catch (NullPointerException e) {
                            dbAboutUser.add("");
                            Log.i(TAG, title);
                        }
                    } else {
                        try {
                            dbAboutUser.add(dataSnapshot.child("adress").child(title).getValue().toString());
                        } catch (NullPointerException e) {
                            dbAboutUser.add("");
                            Log.i(TAG, title);
                        }
                    }
                }

                userProfile.setName(dbAboutUser.get(0));
                userProfile.setPhone(dbAboutUser.get(1));
                userProfile.setCity(dbAboutUser.get(2));
                userProfile.setStreet(dbAboutUser.get(3));
                userProfile.setHouse(dbAboutUser.get(4));
                userProfile.setFlat(dbAboutUser.get(5));

                orderActivity.autoChangeEditTextAboutUser(userProfile);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
