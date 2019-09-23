package com.sanke46.android.e_commerce.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class OrderService extends Service {

    private long date = 0l;
    private OrderServiceBinder binder = new OrderServiceBinder();

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return binder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        updateOrderDate();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    ///
    /// Public interfaces

    public Long getTimeOrder(){
        return date;
    }

    public void updateOrderDate() {
        date = System.currentTimeMillis();
    }

    ///
    /// Binder returning current service

    public class OrderServiceBinder extends Binder {
        public OrderService getService() {
            return OrderService.this;
        }
    }

}
