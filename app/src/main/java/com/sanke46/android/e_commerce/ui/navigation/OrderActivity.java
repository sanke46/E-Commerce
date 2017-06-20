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
import com.sanke46.android.e_commerce.database.DataOrderBaseHandler;
import com.sanke46.android.e_commerce.model.Order;

import java.util.List;

/**
 * Created by ilafedoseev on 13.06.17.
 */

public class OrderActivity extends AppCompatActivity {

    private Order order = new Order();

    private EditText editName;
    private EditText editCity;
    private EditText editStreet;
    private EditText editHouseNumber;
    private EditText editFlat;
    private EditText editPhoneNumber;
    private Button buttonNext;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Order");

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_black_36px));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), BasketActivity.class);
                startActivity(intent);
                finish();
            }
        });

        final DataOrderBaseHandler db = new DataOrderBaseHandler(this);

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

                if(editName.length() != 0 || editCity.length() != 0 || editStreet.length() != 0 || editHouseNumber.length() != 0 || editFlat.length() != 0 || editPhoneNumber.length() != 0){
                    order.setEtnS(editName.getText().toString());
                    order.setEtcS(editCity.getText().toString());
                    order.setEtsS(editStreet.getText().toString());
                    order.setEthnS(editHouseNumber.getText().toString());
                    order.setEtfS(editFlat.getText().toString());
                    order.setEtpnS(editPhoneNumber.getText().toString());
                    db.addOrder(order);
                    Intent intent = new Intent(getApplicationContext(), Payment.class);
                    startActivity(intent);
                }


            }
        });

    }
}
