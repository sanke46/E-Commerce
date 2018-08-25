package com.sanke46.android.e_commerce.ui.navigation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.sanke46.android.e_commerce.MainActivity;
import com.sanke46.android.e_commerce.R;
import com.sanke46.android.e_commerce.ViewModel.ProfileViewModel;
import com.sanke46.android.e_commerce.model.UserProfile;

public class ProfileActivity extends AppCompatActivity {

    private ProfileViewModel profileViewModel;
    public RelativeLayout progressBar;
    public RelativeLayout contentLayout;
    public EditText editTextName;
    public EditText editTextPhone;
    public EditText editTextMail;
    public EditText editTextCity;
    public EditText editTextStreet;
    public EditText editTextHouse;
    public EditText editTextFlat;
    public EditText editTextPassword;
    public ImageView seePassButton;
    public Button buttonToSave;

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(profileViewModel.NAME);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_go_back_left_arrow));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), MainActivity.class));
                finish();
            }
        });

        profileViewModel = new ProfileViewModel(this);
        contentLayout = findViewById(R.id.allProfileActivity);
        progressBar = findViewById(R.id.progressBarProfile);
        editTextName = findViewById(R.id.name);
        editTextPhone = findViewById(R.id.editNumber);
        editTextMail = findViewById(R.id.editMail);
        editTextCity = findViewById(R.id.city_profile);
        editTextStreet = findViewById(R.id.street_profile);
        editTextHouse = findViewById(R.id.house_profile);
        editTextFlat = findViewById(R.id.flat_profile);
        editTextPassword = findViewById(R.id.editPassword);
        buttonToSave = findViewById(R.id.saveButton);
        seePassButton = findViewById(R.id.seePassButton);
        buttonToSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v(profileViewModel.TAG, "CLICKED to save information");
                profileViewModel.saveAllNewInformation(profileViewModel.getTextEditText(editTextName),
                        profileViewModel.getTextEditText(editTextPhone),
                        profileViewModel.getTextEditText(editTextMail),
                        profileViewModel.getTextEditText(editTextPassword),
                        profileViewModel.getTextEditText(editTextCity),
                        profileViewModel.getTextEditText(editTextStreet),
                        profileViewModel.getTextEditText(editTextHouse),
                        profileViewModel.getTextEditText(editTextFlat));
                Toast.makeText(getApplicationContext(), "Thanks,Information saved", Toast.LENGTH_LONG).show();
            }
        });

        seePassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                visibleAndInvisiblePassword();
            }
        });
        profileViewModel.changeInformationAboutProfile();

    }

    /** invisible or visible password **/
    public void visibleAndInvisiblePassword() {
        profileViewModel.unvisiblePassword = !profileViewModel.unvisiblePassword ? true : false;
        if (profileViewModel.unvisiblePassword) {
            editTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            editTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }

    /** Automatic change all EditText **/
    public void autoChangeEditTextAboutUser(UserProfile userProfile) {
        editTextName.setText(userProfile.getName());
        editTextMail.setText(userProfile.getEmail());
        editTextPhone.setText(userProfile.getPhone());
        editTextPassword.setText(userProfile.getPassword());
        editTextCity.setText(userProfile.getCity());
        editTextStreet.setText(userProfile.getStreet());
        editTextHouse.setText(userProfile.getHouse());
        editTextFlat.setText(userProfile.getFlat());
    }
}
