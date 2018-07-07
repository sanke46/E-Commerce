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

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    public FirebaseAuth.AuthStateListener mAuthListener;
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };

    // UI references.
    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private View mLoginFormView;
    private TextView needAcc;
    private TextView fogotPass;
    private Button singInButton;
    private ImageView logo;
    private ProgressBar login_progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        // Set up the login form.
        logo = findViewById(R.id.logoLogIn);
        mEmailEditText = findViewById(R.id.email);
        mPasswordEditText = findViewById(R.id.password);
        singInButton = findViewById(R.id.sign_in_button);
        mLoginFormView = findViewById(R.id.login_form);
        login_progress = findViewById(R.id.login_progress);

        //Click links
        needAcc = findViewById(R.id.needAccaunt);
        needAcc.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewAccaunt();
            }
        });

        fogotPass = findViewById(R.id.fogot_pass);
        fogotPass.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                fogotPassword();
            }
        });


        Animation animation = AnimationUtils.loadAnimation(this, R.anim.animate);
        logo.startAnimation(animation);


        // Test of login user or not
        if (mAuth.getCurrentUser() != null) {
            finish();
            Log.v("LoginActivity","** USER arleady login **");
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("LoginActivity", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d("LoginActivity", "onAuthStateChanged:signed_out");
                }
            }
        };

        singInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singIn(mEmailEditText.getText().toString().trim(), mPasswordEditText.getText().toString().trim());
            }
        });
    }

    public void singIn(String email, String password) {
        logo.setVisibility(View.GONE);
        login_progress.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("LogingActivity", "signInWithEmail:success");
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                } else {
                    // If sign in fails, display a message to the user.
                    logo.setVisibility(View.VISIBLE);
                    login_progress.setVisibility(View.GONE);
                    Log.w("LogingActivity", "signInWithEmail:failure", task.getException());
                    Toast.makeText(getApplicationContext(), "Authentication failed.",  Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void createNewAccaunt(){
        startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
    }

    public void fogotPassword(){
        startActivity( new Intent(getApplicationContext(), ForgotPasswordActivity.class));
    }

}

