package com.sanke46.android.e_commerce.ViewModel;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sanke46.android.e_commerce.model.User;
import com.sanke46.android.e_commerce.ui.navigation.SignUpActivity;

public class SignUpViewModel {

    private static final String TAG = SignUpActivity.class.getSimpleName();

    private SignUpActivity signUpActivity;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;

    public SignUpViewModel(SignUpActivity signUpActivity, String user) {
        this.signUpActivity = signUpActivity;
        this.mAuth = FirebaseAuth.getInstance();
        this.mFirebaseDatabase = FirebaseDatabase.getInstance();
        this.myRef = mFirebaseDatabase.getReference();
    }

    public void createNewAccaount(EditText name, EditText email, EditText password) {
        Log.d(TAG, "onClick Registtration pressed");

        final String finalName = name.getText().toString().trim();
        final String finalEmail = email.getText().toString().trim();
        final String finalPassword = password.getText().toString().trim();

        signUpActivity.isEmptyEditText(email, password);

        // check and sing in
        mAuth.createUserWithEmailAndPassword(finalEmail, finalPassword)
        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    String userId = user.getUid();

                    Log.d(TAG, "createUserWithEmail:success");
                    myRef.child("users").child(userId).setValue(new User(0,finalName, finalEmail, finalPassword));
                    myRef.child("users").child(userId).child("setting").child("sms").setValue(true);
                    myRef.child("users").child(userId).child("setting").child("notification").setValue(true);
                    myRef.child("users").child(userId).child("setting").child("email").setValue(true);
                    signUpActivity.getToast("Authentication cool.", Toast.LENGTH_SHORT);
                    signUpActivity.startActivity();
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    signUpActivity.getToast("Authentication failed.", Toast.LENGTH_SHORT);

                }
            }
        });
    }
}
