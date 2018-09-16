package com.sanke46.android.e_commerce.ViewModel;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.sanke46.android.e_commerce.MainActivity;
import com.sanke46.android.e_commerce.Utility.Helper;
import com.sanke46.android.e_commerce.ui.navigation.ForgotPasswordActivity;
import com.sanke46.android.e_commerce.ui.navigation.LoginActivity;
import com.sanke46.android.e_commerce.ui.navigation.SignUpActivity;

public class LoginActivityViewModel {

    private LoginActivity loginActivity;
    private Helper helper;
    private FirebaseAuth mAuth;

    public LoginActivityViewModel(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
        helper = new Helper(loginActivity.getApplicationContext());
        this.mAuth = FirebaseAuth.getInstance();
    }

    public void singIn(String email, String password, View login_progress, View logo) {
        helper.doneLoadingActivity(login_progress,logo);
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(loginActivity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("LogingActivity", "signInWithEmail:success");
                            loginActivity.startActivity(new Intent(loginActivity.getApplicationContext(), MainActivity.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            logo.setVisibility(View.VISIBLE);
                            login_progress.setVisibility(View.GONE);
                            Log.w("LogingActivity", "signInWithEmail:failure", task.getException());
                            Toast.makeText(loginActivity.getApplicationContext(), "Authentication failed.",  Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void createNewAccaunt(){
        loginActivity.startActivity(new Intent(loginActivity.getApplicationContext(), SignUpActivity.class));
    }

    public void fogotPassword(){
        loginActivity.startActivity( new Intent(loginActivity.getApplicationContext(), ForgotPasswordActivity.class));
    }

    public void isArleadyLoginIn() {
        // Test of login user or not
        if (mAuth.getCurrentUser() != null) {
            loginActivity.finish();
            Log.v("LoginActivity", "** USER arleady login **");
            loginActivity.startActivity(new Intent(loginActivity.getApplicationContext(), MainActivity.class));
        }
    }
}
