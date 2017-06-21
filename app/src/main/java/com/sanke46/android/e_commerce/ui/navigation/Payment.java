package com.sanke46.android.e_commerce.ui.navigation;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.sanke46.android.e_commerce.MyService;
import com.sanke46.android.e_commerce.R;

import java.util.Date;

import io.github.krtkush.lineartimer.LinearTimer;
import io.github.krtkush.lineartimer.LinearTimerView;

public class Payment extends AppCompatActivity {

    private TextView timer;
    private LinearTimerView linearTimerView;
    private Date dateOrderNow;
    private MyService ms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        startService(new Intent(this, MyService.class));

        dateOrderNow = new Date();
        Intent intentService = new Intent(this, MyService.class);
        startService(intentService);

            // Text timer
            timer = (TextView) findViewById(R.id.time);
            new CountDownTimer(3600000 , 1000) {
                @Override
                public void onTick(long l) {
                    timer.setText("second remaining " + l / 1000);
                }

                @Override
                public void onFinish() {
                    timer.setText("done!");
                }
            }.start();

            // Cycle timer
            linearTimerView = (LinearTimerView) findViewById(R.id.linearTimer);
            LinearTimer linearTimer = new LinearTimer.Builder()
                    .linearTimerView(linearTimerView)
                    .duration(10 * 1000)
                    .build();
            linearTimer.startTimer();
    }
}
