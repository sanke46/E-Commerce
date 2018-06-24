package com.sanke46.android.e_commerce.ui.navigation;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sanke46.android.e_commerce.MainActivity;
import com.sanke46.android.e_commerce.R;
import com.sanke46.android.e_commerce.model.User;

public class SingUpActivity extends AppCompatActivity {

    private static final String TAG = "SingUpActivity";

    private EditText newName;
    private EditText newEmail;
    private EditText newPass;
    private Button buttonCreateAc;
    private FirebaseAuth mAuth;

    // Firebase instance
    public FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        //init objects
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        newName = (EditText) findViewById(R.id.newName);
        newEmail = (EditText) findViewById(R.id.newEmail);
        newPass = (EditText) findViewById(R.id.newPassword);
        buttonCreateAc = (Button) findViewById(R.id.sibmit);

        buttonCreateAc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(newName.getText().length() == 0){
                    newName.setHintTextColor(Color.RED);
                }
                if(newEmail.getText().length() == 0){
                    newEmail.setHintTextColor(Color.RED);
                }
                if(newPass.getText().length() == 0){
                    newPass.setHintTextColor(Color.RED);
                }

                if(newName.length() != 0 && newEmail.length() != 0 && newPass.length() != 0 ){
                    createNewAccaount(newEmail.getText().toString(), newPass.getText().toString());
//                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//                    startActivity(intent);
                }
            }
        });
    }

    public void createNewAccaount(String email, String password) {

        Log.d(TAG, "onClick Registtration pressed");

        email = newEmail.getText().toString().trim();
        password = newPass.getText().toString().trim();

        final String finalEmail = email;
        final String finalPassword = password;

        // check email and password if is it empty
        if (TextUtils.isEmpty(email)) {
            newEmail.setHintTextColor(Color.RED);
            Toast.makeText(this,"Email is not correct or empty, try again.", Toast.LENGTH_SHORT);
        } else if (TextUtils.isEmpty(password)) {
            newPass.setHintTextColor(Color.RED);
            Toast.makeText(this, "Password is not correct or empty, try again.", Toast.LENGTH_SHORT);
        }

        // check and sing in
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success");

                        FirebaseUser user = mAuth.getCurrentUser();
                        String userId = user.getUid();
                        myRef.child("users").child(userId).setValue(new User(0,null, finalEmail, finalPassword));
                        finish();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        Toast.makeText(SingUpActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }

}
