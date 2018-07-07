package com.sanke46.android.e_commerce.ui.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sanke46.android.e_commerce.MainActivity;
import com.sanke46.android.e_commerce.R;

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = ProfileActivity.class.getSimpleName();
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseUser user;

    private boolean unvisiblePassword = false;
    private String password;

    private EditText editTextName;
    private EditText editTextNumber;
    private EditText editTextMail;
    private EditText editTextCity;
    private EditText editTextStreet;
    private EditText editTextHouse;
    private EditText editTextFlat;
    private EditText editTextPassword;
    private ImageView seePassButton;
    private Button buttonToSave;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Profile");

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_go_back_left_arrow));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        editTextName = (EditText) findViewById(R.id.name);
        editTextNumber = (EditText) findViewById(R.id.editNumber);
        editTextMail = (EditText) findViewById(R.id.editMail);
        editTextCity = (EditText) findViewById(R.id.city_profile);
        editTextStreet = (EditText) findViewById(R.id.street_profile);
        editTextHouse = (EditText) findViewById(R.id.house_profile);
        editTextFlat = (EditText) findViewById(R.id.flat_profile);
        editTextPassword = (EditText) findViewById(R.id.editPassword);
        buttonToSave = (Button) findViewById(R.id.saveButton);
        seePassButton = (ImageView) findViewById(R.id.seePassButton);


        // FireBase Inctance
        mDatabase = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference unicDataBase = mDatabase.child("users");
        user = mAuth.getCurrentUser();
        final String userId = user.getUid();

        // Change information about user
        unicDataBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    String name = dataSnapshot.child(userId).child("name").getValue().toString();
                    editTextName.setText(name);
                    Log.d(TAG, name);
                } catch (NullPointerException e) {
                    Log.e(TAG, "Name is not exist in user profile.");
                }

                try {
                    String email = dataSnapshot.child(userId).child("email").getValue().toString();
                    editTextMail.setText(email);
                    Log.d(TAG, email);
                } catch (NullPointerException e) {
                    Log.e(TAG, "Email is not exist in user profile.");
                }

                try {
                    String number = dataSnapshot.child(userId).child("phone").getValue().toString();
                    editTextNumber.setText(number);
                    Log.d(TAG, number);
                } catch (NullPointerException e) {
                    Log.e(TAG, "Number phone is not exist in user profile.");
                }

                try {
                    String city = dataSnapshot.child(userId).child("adress").child("city").getValue().toString();
                    String street = dataSnapshot.child(userId).child("adress").child("street").getValue().toString();
                    String house = dataSnapshot.child(userId).child("adress").child("house").getValue().toString();
                    String flat = dataSnapshot.child(userId).child("adress").child("flat").getValue().toString();

                    editTextCity.setText(city);
                    editTextStreet.setText(street);
                    editTextHouse.setText(house);
                    editTextFlat.setText(flat);

                    Log.d(TAG, "Correct adress: " + city + " " + street + " " + house + " " + flat + ".");
                } catch (NullPointerException e) {
                    Log.e(TAG, "Adress is not exist in user profile.");
                }

                    password = (dataSnapshot.child(userId).child("password").getValue()).toString();
                    editTextPassword.setText(password);

//                    unvisiblePassword = false;
//                    visibleAndInvisiblePassword(password);
                    Log.d(TAG, password);
                }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        buttonToSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("ProfileActivity", "CLICKED to save information");
                saveAllNewInformation(getTextEditText(editTextName),
                        getTextEditText(editTextNumber),
                        getTextEditText(editTextMail),
                        getTextEditText(editTextPassword),
                        getTextEditText(editTextCity),
                        getTextEditText(editTextStreet),
                        getTextEditText(editTextHouse),
                        getTextEditText(editTextFlat),
                        userId);
                Toast.makeText(getApplicationContext(), "Thanks,Information saved", Toast.LENGTH_LONG).show();
            }
        });

        seePassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                visibleAndInvisiblePassword();
            }
        });
    }

    /**  Save new Inctance to FireBase **/
    public void saveAllNewInformation(String name,
                                      String phoneNumber,
                                      String email,
                                      String password,
                                      String city,
                                      String street,
                                      String house,
                                      String flat,
                                      String userId) {
        mDatabase.child("users").child(userId).child("name").setValue(name);
        mDatabase.child("users").child(userId).child("phone").setValue(phoneNumber);
        mDatabase.child("users").child(userId).child("email").setValue(email);
        mDatabase.child("users").child(userId).child("password").setValue(password);
        user.updatePassword(password).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "Password updated");
                } else {
                    Log.d(TAG, "Error password not updated");
                }
            }
        });
        mDatabase.child("users").child(userId).child("adress").child("city").setValue(city);
        mDatabase.child("users").child(userId).child("adress").child("street").setValue(street);
        mDatabase.child("users").child(userId).child("adress").child("house").setValue(house);
        mDatabase.child("users").child(userId).child("adress").child("flat").setValue(flat);

    }

    /** Get String inside EditText **/
    public String getTextEditText(EditText input) {
        return input.getText().toString();
    }

    /** invisible or visible password **/
    public void visibleAndInvisiblePassword() {
        unvisiblePassword = !unvisiblePassword ? true : false;

        if (unvisiblePassword) {
            editTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            editTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }
}
