package com.sanke46.android.e_commerce.ui.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

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

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();;

    private EditText editTextNumber;
    private EditText editTextMail;
    private EditText editTextPassword;
    private Button buttonToSave;
    private ImageView seePassButton;

    private boolean unvisiblePassword = false;
    private String password;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Profile");

        editTextNumber = findViewById(R.id.editNumber);
        editTextMail = findViewById(R.id.editMail);
        editTextPassword = findViewById(R.id.editPassword);
        buttonToSave = findViewById(R.id.saveButton);
        seePassButton = findViewById(R.id.seePassButton);

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_black_36px));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // FireBase Inctance
        mDatabase = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference unicDataBase = mDatabase.child("users");
        FirebaseUser user = mAuth.getCurrentUser();
        final String userId = user.getUid();


        // Change information about user
        unicDataBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String email = dataSnapshot.child(userId).child("email").getValue().toString();
                editTextMail.setText(email);
                try {
                    String number = dataSnapshot.child(userId).child("phone").getValue().toString();
                    editTextNumber.setText(number);
                } catch (NullPointerException e ) {
                    System.out.println(e);
                }

                password = dataSnapshot.child(userId).child("password").getValue().toString();
                visibleAndInvisiblePassword(password);

                }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        buttonToSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("ProfileActivity", "CLICKED to save information");
                saveAllNewInformation(getTextEditText(editTextNumber),getTextEditText(editTextMail),getTextEditText(editTextPassword), userId);
                Toast.makeText(getApplicationContext(), "Thanks,Information saved", Toast.LENGTH_LONG).show();
            }
        });

        seePassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                visibleAndInvisiblePassword(password);
            }
        });
    }

    /**  Save new Inctance to FireBase **/
    public void saveAllNewInformation(String phoneNumber, String email, String password, String userId) {
        mDatabase.child("users").child(userId).child("phone").setValue(phoneNumber);
        mDatabase.child("users").child(userId).child("email").setValue(email);
        mDatabase.child("users").child(userId).child("password").setValue(password);
    }

    /** Get String inside EditText **/
    public String getTextEditText(EditText input) {
        return input.getText().toString();
    }

    /** invisible or visible password **/
    public void visibleAndInvisiblePassword(String password) {

        if(!unvisiblePassword) {
            unvisiblePassword = true;
        } else {
            unvisiblePassword = false;
        }

        if (unvisiblePassword) {
            String resultUnvisible = "";
            for (int i = 0; i < password.length(); i++) {
                resultUnvisible += "*";
            }
            editTextPassword.setText(resultUnvisible);
        } else {
            editTextPassword.setText(password);
        }
    }
}
