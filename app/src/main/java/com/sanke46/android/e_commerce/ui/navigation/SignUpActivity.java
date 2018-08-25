package com.sanke46.android.e_commerce.ui.navigation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.sanke46.android.e_commerce.MainActivity;
import com.sanke46.android.e_commerce.R;
import com.sanke46.android.e_commerce.ViewModel.SignUpViewModel;

public class SignUpActivity extends AppCompatActivity {

    private SignUpViewModel signUpViewModel;
    private ImageView logo;
    private ProgressBar progressBar;
    private EditText newName;
    private EditText newEmail;
    private EditText newPass;
    private Button buttonCreateAc;

    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        signUpViewModel = new SignUpViewModel(this);
        logo = findViewById(R.id.logoSignUp);
        progressBar = findViewById(R.id.sign_up_progress);
        newName = findViewById(R.id.newName);
        newEmail = findViewById(R.id.newEmail);
        newPass = findViewById(R.id.newPassword);
        buttonCreateAc = findViewById(R.id.sibmit);
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
                    logo.setVisibility(View.GONE);
                    progressBar.setVisibility(View.VISIBLE);
                    signUpViewModel.createNewAccaount(newName, newEmail, newPass);
//                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }
            }
        });
    }

    public void startActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void getToast(String text, int howLong) {
        Toast.makeText(this,text, howLong);
    }

    // check email and password if is it empty
    @SuppressLint("ShowToast")
    public void isEmptyEditText(EditText email, EditText password) {
        if (TextUtils.isEmpty(email.getText())) {
            email.setHintTextColor(Color.RED);
            Toast.makeText(this, "Email is not correct or empty, try again.", Toast.LENGTH_SHORT);
        } else if (TextUtils.isEmpty(password.getText())) {
            password.setHintTextColor(Color.RED);
            Toast.makeText(this, "Password is not correct or empty, try again.", Toast.LENGTH_SHORT);
        }
    }
}
