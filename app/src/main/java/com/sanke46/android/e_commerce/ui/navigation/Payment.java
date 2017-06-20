package com.sanke46.android.e_commerce.ui.navigation;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.sanke46.android.e_commerce.R;

public class Payment extends AppCompatActivity {

    private TextView timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        timer = (TextView) findViewById(R.id.time);

        new CountDownTimer(30000, 1000){

            @Override
            public void onTick(long l) {
                timer.setText("second remaining " + l / 1000);
            }

            @Override
            public void onFinish() {
                timer.setText("done!");
            }
        }.start();
    }


}
