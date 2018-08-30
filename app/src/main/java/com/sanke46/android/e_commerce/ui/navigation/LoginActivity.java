package com.sanke46.android.e_commerce.ui.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sanke46.android.e_commerce.MainActivity;
import com.sanke46.android.e_commerce.R;
import com.sanke46.android.e_commerce.ViewModel.LoginActivityViewModel;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    private LoginActivityViewModel loginViewModel;
    public FirebaseAuth.AuthStateListener mAuthListener;

    // UI references.
    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private TextView needAcc;
    private TextView fogotPass;
    private Button singInButton;
    private ImageView logo;
    private ProgressBar login_progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginViewModel = new LoginActivityViewModel(this);

        // Set up the login form.
        logo = findViewById(R.id.logoLogIn);
        mEmailEditText = findViewById(R.id.email);
        mPasswordEditText = findViewById(R.id.password);
        login_progress = findViewById(R.id.login_progress);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.animate);
        logo.startAnimation(animation);

        loginViewModel.isArleadyLoginIn();

        //Click links
        needAcc = findViewById(R.id.needAccaunt);
        needAcc.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                loginViewModel.createNewAccaunt();
            }
        });

        fogotPass = findViewById(R.id.fogot_pass);
        fogotPass.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                loginViewModel.fogotPassword();
            }
        });

        singInButton = findViewById(R.id.sign_in_button);
        singInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginViewModel.singIn(mEmailEditText.getText().toString().trim(), mPasswordEditText.getText().toString().trim(),login_progress,logo);
            }
        });
    }
}

