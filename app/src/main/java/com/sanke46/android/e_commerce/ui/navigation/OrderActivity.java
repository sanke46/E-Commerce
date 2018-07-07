package com.sanke46.android.e_commerce.ui.navigation;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.sanke46.android.e_commerce.R;
import com.sanke46.android.e_commerce.ViewModel.OrderActivityViewModel;
import com.sanke46.android.e_commerce.model.UserOrder;

public class OrderActivity extends AppCompatActivity {

    public EditText editName;
    public EditText editCity;
    public EditText editStreet;
    public EditText editHouseNumber;
    public EditText editFlat;
    public EditText editPhoneNumber;
    public Button buttonNext;
    public LinearLayout linearLayout;

    private OrderActivityViewModel orderViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        orderViewModel = new OrderActivityViewModel(this);

        // Toolbar init
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Order");
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_go_back_left_arrow));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), BasketActivity.class));
                finish();
            }
        });

        editName = findViewById(R.id.editDN);
        editCity = findViewById(R.id.editDC);
        editStreet = findViewById(R.id.editDS);
        editHouseNumber = findViewById(R.id.editDH);
        editFlat = findViewById(R.id.editDF);
        editPhoneNumber = findViewById(R.id.editDP);
        buttonNext = findViewById(R.id.buttonNext);
        linearLayout = findViewById(R.id.orderInfo);
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
                    orderViewModel.addOrderUserFireBase(editPhoneNumber,editCity,editStreet,editHouseNumber,editFlat);
                    Toast.makeText(getApplicationContext(), "order DONE", Toast.LENGTH_SHORT);
                }
            }
        });

        orderViewModel.InformationAboutUser();
    }

    public void autoChangeEditTextAboutUser(UserOrder userOrder) {
        editName.setText(userOrder.getName());
        editPhoneNumber.setText(userOrder.getPhone());
        editCity.setText(userOrder.getCity());
        editStreet.setText(userOrder.getStreet());
        editHouseNumber.setText(userOrder.getHouse());
        editFlat.setText(userOrder.getFlat());
    }

}
