package com.sanke46.android.e_commerce.ViewModel;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.sanke46.android.e_commerce.ui.navigation.LoginActivity;

public class ForgotPasswordViewModel {
    private static final String TAG = "ForgotPasswordActivity";
    private static final String LOG_PASS_SEND = "password send to Email";
    private static final String TOAST_PASS_SEND = "New password send to your email";

    public void sendPasswordToEmail(Context context, EditText emailEditText) {
        FirebaseAuth.getInstance().sendPasswordResetEmail(String.valueOf(emailEditText.getText()))
        .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, LOG_PASS_SEND);
                    context.startActivity(new Intent(context, LoginActivity.class));
                    Toast.makeText(context, TOAST_PASS_SEND, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
