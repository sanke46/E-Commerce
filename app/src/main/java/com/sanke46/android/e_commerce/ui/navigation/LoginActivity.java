package com.sanke46.android.e_commerce.ui.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
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


    private static final int REQUEST_READ_CONTACTS = 0;
    private FirebaseAuth mAuth;
    public FirebaseAuth.AuthStateListener mAuthListener;

    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private TextView needAcc;
    private TextView fogotPass;
    private Button singInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
//        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
//                if (id == R.id.login || id == EditorInfo.IME_NULL) {
//                    return true;
//                }
//                return false;
//            }
//        });
        singInButton = (Button) findViewById(R.id.sign_in_button);
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);

        //
        //Click links

        needAcc = (TextView) findViewById(R.id.needAccaunt);
        needAcc.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewAccaunt();
            }
        });

        fogotPass = (TextView) findViewById(R.id.fogot_pass);
        fogotPass.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                fogotPassword();
            }
        });
        mAuth = FirebaseAuth.getInstance();

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

//        mAuth = FirebaseAuth.getInstance().getReference();

        singInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singIn(mEmailView.getText().toString().trim(),mPasswordView.getText().toString().trim());
            }
        });


    }

    public void singIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("LogingActivity", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("LogingActivity", "signInWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",  Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void createNewAccaunt(){
        Intent createAccaunt = new Intent(getApplicationContext(), SingUpActivity.class);
        startActivity(createAccaunt);
    }

    public void fogotPassword(){
        Intent fogetpass = new Intent(getApplicationContext(), ForgotPasswordActivity.class);
        startActivity(fogetpass);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

}

