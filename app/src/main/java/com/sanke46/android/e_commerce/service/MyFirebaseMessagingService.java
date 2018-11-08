package com.sanke46.android.e_commerce.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.sanke46.android.e_commerce.MainActivity;
import com.sanke46.android.e_commerce.R;
import com.sanke46.android.e_commerce.model.NotificationData;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyGcmListenerService";

    @Override
    public void onMessageReceived(RemoteMessage message) {
        String image = message.getNotification().getIcon();
        String title = message.getNotification().getTitle();
        String text = message.getNotification().getBody();
        String sound = message.getNotification().getSound();

        int id = 0;
        Object obj = message.getData().get("id");
        if (obj != null) {
            id = Integer.valueOf(obj.toString());
        }

        this.sendNotification(new NotificationData(image, id, title, text, sound));
    }

    private void sendNotification(NotificationData notification) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(NotificationData.TEXT, notification.getTextMessage());

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notificationBuilder = null;
        try {

            notificationBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(URLDecoder.decode(notification.getTitle(), "UTF-8"))
                    .setContentText(URLDecoder.decode(notification.getTextMessage(), "UTF-8"))
                    .setAutoCancel(true)
                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                    .setContentIntent(pendingIntent);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if (notificationBuilder != null) {
            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(notification.getId(), notificationBuilder.build());
        } else {
            Log.d(TAG, "Não foi possível criar objeto notificationBuilder");
        }
    }
}
