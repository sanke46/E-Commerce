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
import com.sanke46.android.e_commerce.database.DataUserBaseHandler;
import com.sanke46.android.e_commerce.model.User;

public class SingUpActivity extends AppCompatActivity {

    private EditText newName;
    private EditText newEmail;
    private EditText newPass;
    private EditText newConfirmPass;
    private Button buttonCreateAc;
    private DataUserBaseHandler dbUser = new DataUserBaseHandler(this);
    private FirebaseAuth mAuth;

    private static final String TAG = "SingUpActivity";

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

        newName = findViewById(R.id.newName);
        newEmail = findViewById(R.id.newEmail);
        newPass = findViewById(R.id.newPassword);
        newConfirmPass = findViewById(R.id.confirm_pass);
        buttonCreateAc = findViewById(R.id.sibmit);


        buttonCreateAc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(newName.getText().length() == 0){
//                    newName.setHintTextColor(Color.RED);
//                }
//                if(newEmail.getText().length() == 0){
//                    newEmail.setHintTextColor(Color.RED);
//                }
//                if(newPass.getText().length() == 0){
//                    newPass.setHintTextColor(Color.RED);
//                }
//                if(newConfirmPass.getText().length() == 0) {
//                    newConfirmPass.setHintTextColor(Color.RED);
//                    if(newPass.getText().toString().equals(newConfirmPass.getText().toString())){
//                        newPass.setHintTextColor(Color.RED);
//                    }
//                }
//
//                if(newName.length() != 0 || newEmail.length() != 0 || newPass.length() != 0 && newPass.getText().toString().equals(newConfirmPass.getText().toString()) ){
//                    dbUser.addUser(new User(newName.getText().toString(),newEmail.getText().toString(),newPass.getText().toString()));
//                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//                    startActivity(intent);
//
//
//                    for (User us : dbUser.getAllUser()){
//                        System.out.println(us.getId() + " " + us.getName() + " " + us.getEmail() + " " + us.getPassword());
//                    }
//                }
                createNewAccaount(newEmail.getText().toString(), newPass.getText().toString(), newConfirmPass.getText().toString());
            }
        });
    }

    public void createNewAccaount(String email, String password, String passwordConfirm) {

        Log.d(TAG, "onClick Registtration pressed");
        email = newEmail.getText().toString().trim();
        password = newPass.getText().toString().trim();
        passwordConfirm =  newConfirmPass.getText().toString().trim();

        // check email and password if is it empty
        if(TextUtils.isEmpty(email)) {
            newEmail.setHintTextColor(Color.RED);
            Toast.makeText(this,"Email is not correct or empty, try again.", Toast.LENGTH_SHORT);
            return;
        } else if(TextUtils.isEmpty(password) && TextUtils.isEmpty(passwordConfirm)) {
            newPass.setHintTextColor(Color.RED);
            newConfirmPass.setHintTextColor(Color.RED);
            Toast.makeText(this, "Password is not correct or empty, try again.", Toast.LENGTH_SHORT);
            return;
        }

        final String finalEmail = email;
        final String finalPassword = password;

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            String userId = user.getUid();
                            myRef.child(userId).setValue(new User(0,null, finalEmail, finalPassword));
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
