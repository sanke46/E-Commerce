package com.sanke46.android.e_commerce.ui.navigation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.sanke46.android.e_commerce.R;
import com.sanke46.android.e_commerce.ViewModel.ForgotPasswordViewModel;

public class ForgotPasswordActivity extends AppCompatActivity {

    private ForgotPasswordViewModel forgotViewModel = new ForgotPasswordViewModel();
    private ProgressBar loddingProgressBar;
    private EditText forgotEmailEditText;
    private Button buttonSend;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        linearLayout = findViewById(R.id.liner);
        loddingProgressBar = findViewById(R.id.loadingProgressBar);
        forgotEmailEditText = findViewById(R.id.forgotEmail);
        buttonSend = findViewById(R.id.sendEmail);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loddingProgressBar.setVisibility(View.VISIBLE);
                forgotViewModel.sendPasswordToEmail(getApplicationContext(),forgotEmailEditText);
            }
        });
    }
}
