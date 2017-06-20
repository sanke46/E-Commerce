package com.sanke46.android.e_commerce.ui.navigation;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sanke46.android.e_commerce.R;
import com.sanke46.android.e_commerce.database.DataOrderBaseHandler;
import com.sanke46.android.e_commerce.model.Order;

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

    private int etn,etc,ets,ethn,etf,etpn;

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

        DataOrderBaseHandler db = new DataOrderBaseHandler(this);
//        List<Order> list = db.getAllOrder();

        editName = (EditText) findViewById(R.id.editDN);
        editCity = (EditText) findViewById(R.id.editDC);
        editStreet = (EditText) findViewById(R.id.editDS);
        editHouseNumber = (EditText) findViewById(R.id.editDH);
        editFlat = (EditText) findViewById(R.id.editDF);
        editPhoneNumber = (EditText) findViewById(R.id.editDP);
        buttonNext = (Button) findViewById(R.id.buttonNext);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editName.getText().length() == 0){
                    etn = 0;
                    editName.setHintTextColor(Color.RED);
                }
                if(editCity.getText().length() == 0){
                    etc = 0;
                    editCity.setHintTextColor(Color.RED);
                }
                if(editStreet.getText().length() == 0){
                    ets = 0;
                    editStreet.setHintTextColor(Color.RED);
                }
                if(editHouseNumber.getText().length() == 0) {
                    ethn = 0;
                    editHouseNumber.setHintTextColor(Color.RED);
                }
                if(editFlat.getText().length() == 0) {
                    etf = 0;
                    editFlat.setHintTextColor(Color.RED);
                }
                if(editPhoneNumber.getText().length() == 0) {
                    etpn = 0;
                    editPhoneNumber.setHintTextColor(Color.RED);
                }

                if(etn != 0 || etc != 0 || ets != 0 || ethn != 0 || etf != 0 || etpn != 0){
                    order.setEtnS(editName.getText().toString());
                    order.setEtcS(editCity.getText().toString());
                    order.setEtsS(editStreet.getText().toString());
                    order.setEthnS(editHouseNumber.getText().toString());
                    order.setEtfS(editFlat.getText().toString());
                    order.setEtpnS(editPhoneNumber.getText().toString());
                    System.out.println(order.getId() + " " + order.getEtnS() + " " + order.getEtpnS() );
                    Toast.makeText(OrderActivity.this, "Thanks for your order", Toast.LENGTH_LONG).show();
                }


            }
        });

    }
}