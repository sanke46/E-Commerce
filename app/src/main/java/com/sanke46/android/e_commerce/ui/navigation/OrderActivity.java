package com.sanke46.android.e_commerce.ui.navigation;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sanke46.android.e_commerce.R;
import com.sanke46.android.e_commerce.model.Item;
import com.sanke46.android.e_commerce.model.Order;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OrderActivity extends AppCompatActivity {

    private static final String TAG = OrderActivity.class.getSimpleName();
    BasketActivity basketActivity = new BasketActivity();
    private List<Item> itemList = basketActivity.getBasketItem();
    private Order order = new Order();

    private EditText editName;
    private EditText editCity;
    private EditText editStreet;
    private EditText editHouseNumber;
    private EditText editFlat;
    private EditText editPhoneNumber;
    private Button buttonNext;
    private LinearLayout linearLayout;

    private DatabaseReference dbRef;
    DatabaseReference orderIdRef;
    private String orderId;
    private FirebaseAuth userAuth;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        // Firebase init var
        dbRef = FirebaseDatabase.getInstance().getReference().child("users");
        orderIdRef = FirebaseDatabase.getInstance().getReference();
        userAuth = FirebaseAuth.getInstance();
        FirebaseUser user = userAuth.getCurrentUser();
        userId = user.getUid();

        // Toolbar init
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Order");
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_go_back_left_arrow));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), BasketActivity.class);
                startActivity(intent);
                finish();
            }
        });

        editName = (EditText) findViewById(R.id.editDN);
        editCity = (EditText) findViewById(R.id.editDC);
        editStreet = (EditText) findViewById(R.id.editDS);
        editHouseNumber = (EditText) findViewById(R.id.editDH);
        editFlat = (EditText) findViewById(R.id.editDF);
        editPhoneNumber = (EditText) findViewById(R.id.editDP);
        buttonNext = (Button) findViewById(R.id.buttonNext);
        linearLayout = (LinearLayout) findViewById(R.id.orderInfo);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editName.getText().length() == 0){
                    editName.setHintTextColor(Color.RED);
                }
                if(editCity.getText().length() == 0){
                    editCity.setHintTextColor(Color.RED);
                }
                if(editStreet.getText().length() == 0){
                    editStreet.setHintTextColor(Color.RED);
                }
                if(editHouseNumber.getText().length() == 0) {
                    editHouseNumber.setHintTextColor(Color.RED);
                }
                if(editFlat.getText().length() == 0) {
                    editFlat.setHintTextColor(Color.RED);
                }
                if(editPhoneNumber.getText().length() == 0) {
                    editPhoneNumber.setHintTextColor(Color.RED);
                }

                if(editName.length() != 0 && editCity.length() != 0 && editStreet.length() != 0 && editHouseNumber.length() != 0 && editFlat.length() != 0 && editPhoneNumber.length() != 0){
                    addOrderUserFireBase();
                }
            }
        });

        changeAutomaticInformationAboutUser();

    }


    // method to find information about user
    public void changeAutomaticInformationAboutUser() {
        dbRef.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    String name = dataSnapshot.child("name").getValue().toString();
                    editName.setText(name);
                    Log.i(TAG, "Name of user: " + name);
                } catch (NullPointerException e) {
                    Log.i(TAG, "Name of user doesn't exist in Firebase.");
                }

                try {
                    String phone = dataSnapshot.child("phone").getValue().toString();
                    Log.i(TAG, "Phone of user: " + phone);
                    editPhoneNumber.setText(phone);
                } catch (NullPointerException e) {
                    Log.i(TAG, "Phone of user doesn't exist in Firebase.");
                }

                try {
                    String city = dataSnapshot.child("adress").child("city").getValue().toString();
                    String street = dataSnapshot.child("adress").child("street").getValue().toString();
                    String houseNumber = dataSnapshot.child("adress").child("house").getValue().toString();
                    String flatNumber = dataSnapshot.child("adress").child("flat").getValue().toString();

                    Log.i(TAG, "Adress of user: " + city + " " + street + " " + houseNumber + " " + flatNumber);

                    editCity.setText(city);
                    editStreet.setText(street);
                    editHouseNumber.setText(houseNumber);
                    editFlat.setText(flatNumber);
                } catch (NullPointerException e) {
                    Log.i(TAG, "Adress of user doesn't exist in Firebase.");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        orderIdRef.child("currentOrder").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                orderId = dataSnapshot.getValue().toString();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    // add order to firebase
    public void addOrderUserFireBase() {
        long yourmilliseconds = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");
        Date resultdate = new Date(yourmilliseconds);

        order.setPayment("Cach");
        order.setTime(resultdate.toString());
        order.setPhone(editPhoneNumber.getText().toString());
        order.setListOfBuyProducts(itemList);
        order.setTotalPrice(getTotalPrice(itemList));
        order.setAdress(editCity.getText().toString() + " " + editStreet.getText().toString() + " " + editHouseNumber.getText().toString() + " " + editFlat.getText().toString());

        dbRef.child(userId).child("history").child(String.valueOf(orderId)).child("payment").setValue(order.getPayment());
        dbRef.child(userId).child("history").child(String.valueOf(orderId)).child("totalPrice").setValue(order.getTotalPrice());
        dbRef.child(userId).child("history").child(String.valueOf(orderId)).child("adress").setValue(order.getAdress());
        dbRef.child(userId).child("history").child(String.valueOf(orderId)).child("phone").setValue(order.getPhone());
        dbRef.child(userId).child("history").child(String.valueOf(orderId)).child("time").setValue(order.getTime());
        dbRef.child(userId).child("history").child(String.valueOf(orderId)).child("items").setValue(order.getListOfBuyProducts());

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
}
