package com.sanke46.android.e_commerce.ViewModel;

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
import com.sanke46.android.e_commerce.model.UserOrder;
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

        dbRef.child(userId).child("history").child(String.valueOf(orderId)).child("payment").setValue(order.getPayment());
        dbRef.child(userId).child("history").child(String.valueOf(orderId)).child("totalPrice").setValue(order.getTotalPrice());
        dbRef.child(userId).child("history").child(String.valueOf(orderId)).child("adress").setValue(order.getAdress());
        dbRef.child(userId).child("history").child(String.valueOf(orderId)).child("phone").setValue(order.getPhone());
        dbRef.child(userId).child("history").child(String.valueOf(orderId)).child("time").setValue(order.getTime());
        dbRef.child(userId).child("history").child(String.valueOf(orderId)).child("items").setValue(order.getListOfBuyProducts());

        getOrederId();
        int changeId = Integer.parseInt(orderId);
        orderIdRef.child("currentOrder").setValue(changeId - 1);

    }

    public String getTotalPrice(List<Item> itemList) {
        int resultPrice = 0;

        for (Item item : itemList) {
            resultPrice += item.getPrice();
        }

        return String.valueOf(resultPrice);
    }

    public void getOrederId() {
        orderIdRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                orderId = dataSnapshot.getValue().toString();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    // method to find information about user
    public void InformationAboutUser() {
        dbRef.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                UserOrder userOrder = new UserOrder();
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

                userOrder.setName(dbAboutUser.get(0));
                userOrder.setPhone(dbAboutUser.get(1));
                userOrder.setCity(dbAboutUser.get(2));
                userOrder.setStreet(dbAboutUser.get(3));
                userOrder.setHouse(dbAboutUser.get(4));
                userOrder.setFlat(dbAboutUser.get(5));

                orderActivity.autoChangeEditTextAboutUser(userOrder);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
