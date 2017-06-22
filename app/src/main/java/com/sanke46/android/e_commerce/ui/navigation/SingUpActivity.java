package com.sanke46.android.e_commerce.ui.navigation;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        //init objects
        newName = (EditText) findViewById(R.id.newName);
        newEmail = (EditText) findViewById(R.id.newEmail);
        newPass = (EditText) findViewById(R.id.newPassword);
        newConfirmPass = (EditText) findViewById(R.id.confirm_pass);
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
                if(newConfirmPass.getText().length() == 0) {
                    newConfirmPass.setHintTextColor(Color.RED);
                    if(newPass.getText().toString().equals(newConfirmPass.getText().toString())){
                        newPass.setHintTextColor(Color.RED);
                    }
                }

                if(newName.length() != 0 || newEmail.length() != 0 || newPass.length() != 0 && newPass.getText().toString().equals(newConfirmPass.getText().toString()) ){
                    dbUser.addUser(new User(newName.getText().toString(),newEmail.getText().toString(),newPass.getText().toString()));
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);


                    for (User us : dbUser.getAllUser()){
                        System.out.println(us.getId() + " " + us.getName() + " " + us.getEmail() + " " + us.getPassword());
                    }
                }
            }
        });
    }
}
