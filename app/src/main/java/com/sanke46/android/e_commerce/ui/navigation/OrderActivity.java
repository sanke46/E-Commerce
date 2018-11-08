package com.sanke46.android.e_commerce.ui.navigation;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.sanke46.android.e_commerce.MainActivity;
import com.sanke46.android.e_commerce.R;
import com.sanke46.android.e_commerce.Utility.Helper;
import com.sanke46.android.e_commerce.ViewModel.OrderActivityViewModel;
import com.sanke46.android.e_commerce.model.UserProfile;

public class OrderActivity extends AppCompatActivity {

    private ImageView userIcon;
    private ImageView placeIcon;
    private ImageView phoneIcon;

    private ProgressBar userProgressBar;
    private ProgressBar placeProgressBar;
    private ProgressBar phoneProgressBar;

    public EditText editName;
    public EditText editCity;
    public EditText editStreet;
    public EditText editHouseNumber;
    public EditText editFlat;
    public EditText editPhoneNumber;
    public Button buttonNext;

    private OrderActivityViewModel orderViewModel;
    private Helper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        orderViewModel = new OrderActivityViewModel(this);
        helper = new Helper(this);

        // Toolbar init
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(orderViewModel.NAME_ACTIVITY);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_go_back_left_arrow));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), BasketActivity.class));
                finish();
            }
        });

        userIcon = findViewById(R.id.iconUser);
        placeIcon = findViewById(R.id.iconPlace);
        phoneIcon = findViewById(R.id.iconPhone);

        userProgressBar = findViewById(R.id.userProgressBarOrder);
        placeProgressBar = findViewById(R.id.placeProgressBar);
        phoneProgressBar = findViewById(R.id.phoneProgressBar);

        editName = findViewById(R.id.editDN);
        editCity = findViewById(R.id.editDC);
        editStreet = findViewById(R.id.editDS);
        editHouseNumber = findViewById(R.id.editDH);
        editFlat = findViewById(R.id.editDF);
        editPhoneNumber = findViewById(R.id.editDP);
        buttonNext = findViewById(R.id.buttonNext);
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(OrderActivity.this,R.style.AlertDialogCustom_Destructive);
                    builder.setTitle("Заказ оформлен")
                            .setMessage("В скором времени с вами свяжутся для подтверждения заказа")
                            .setIcon(R.drawable.ic_shopping_bag_two)
                            .setCancelable(false)
                            .setNegativeButton("Хорошо",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                            startActivity(new Intent(getBaseContext(), MainActivity.class));

                                        }
                                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                    Toast.makeText(getApplicationContext(), orderViewModel.DONE, Toast.LENGTH_SHORT);
                }
            }
        });

        orderViewModel.InformationAboutUser();
        changeEditTextcolorBack(editCity);
        changeEditTextcolorBack(editFlat);
        changeEditTextcolorBack(editHouseNumber);
        changeEditTextcolorBack(editStreet);
    }

    public void autoChangeEditTextAboutUser(UserProfile userProfile) {
        editName.setText(userProfile.getName());
        editPhoneNumber.setText(userProfile.getPhone());
        editCity.setText(userProfile.getCity());
        editStreet.setText(userProfile.getStreet());
        editHouseNumber.setText(userProfile.getHouse());
        editFlat.setText(userProfile.getFlat());
        helper.doneLoadingActivity(userIcon, userProgressBar);
        helper.doneLoadingActivity(placeIcon, placeProgressBar);
        helper.doneLoadingActivity(phoneIcon, phoneProgressBar);
    }

    public void changeEditTextcolorBack(EditText editText) {
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                    editText.setHintTextColor(Color.GRAY);
            }
        });
    }

}
