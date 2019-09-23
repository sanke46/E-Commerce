package com.sanke46.android.e_commerce.ui.navigation;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.sanke46.android.e_commerce.R;
import com.sanke46.android.e_commerce.service.OrderService;

import io.github.krtkush.lineartimer.LinearTimer;
import io.github.krtkush.lineartimer.LinearTimerView;

public class Payment extends AppCompatActivity {

    private TextView timer;
    private LinearTimerView linearTimerView;

    private boolean isServiceBound = false;
    private OrderService service;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder binder) {
            OrderService.OrderServiceBinder orderBinder = (OrderService.OrderServiceBinder)binder;
            service = orderBinder.getService();
            isServiceBound = true;

            setupTimer();
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            isServiceBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Intent intent = new Intent(this, OrderService.class);
        startService(intent);

        // Text timer
        timer = (TextView) findViewById(R.id.time);

        // Cycle timer
        linearTimerView = (LinearTimerView) findViewById(R.id.linearTimer);
    }

    protected void setupTimer() {
        new CountDownTimer(deliveryTime(), 1000) {
            @Override
            public void onTick(long timeLeft) {
                timer.setText("second remaining " + timeLeft / 1000);
            }

            @Override
            public void onFinish() {
                timer.setText("done!");
            }
        }.start();

        LinearTimer linearTimer = new LinearTimer.Builder()
                .linearTimerView(linearTimerView)
                .duration(deliveryDuration(), deliveryTimeElapsed())
                .build();
        linearTimer.startTimer();
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent intentService = new Intent(this, OrderService.class);
        bindService(intentService, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (isServiceBound) {
            isServiceBound = false;
            unbindService(serviceConnection);
        }
    }

    private long deliveryDuration() {
        return 60*1000l;
    }

    private long deliveryTimeElapsed() {
        return Math.max(0, System.currentTimeMillis() - service.getTimeOrder());
    }

    private long deliveryTime() {
        long orderDeliveryTimeMillis = deliveryDuration();
        long timeElapsedSinceOrder = deliveryTimeElapsed();
        long timeWhenStopTimer = Math.max(0, orderDeliveryTimeMillis - timeElapsedSinceOrder);
        return timeWhenStopTimer;
    }

    public boolean isServiceBound() {
        return isServiceBound;
    }


}
