package com.sanke46.android.e_commerce.ViewModel;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sanke46.android.e_commerce.R;
import com.sanke46.android.e_commerce.Utility.Helper;
import com.sanke46.android.e_commerce.adapter.ChatListAdapter;
import com.sanke46.android.e_commerce.fireBase.FirebaseHandler;
import com.sanke46.android.e_commerce.model.Chat;
import com.sanke46.android.e_commerce.ui.navigation.ChatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChatViewModel {

    public ArrayList<Chat> listChat = new ArrayList<>();

    private ChatActivity chatActivity;
    private FirebaseHandler firebaseHandler;

    public ChatViewModel(ChatActivity chatActivity) {
        this.chatActivity = chatActivity;
        firebaseHandler = new FirebaseHandler();
    }

    public void getAllPreviosChat(final ChatListAdapter chatAdapter) {
        firebaseHandler.getAllChat(chatActivity, this, listChat, chatAdapter);
    }

    public void addTextToChat() {
        firebaseHandler.addInformationToChat(this, chatActivity.messageEditText.getText().toString(), getCurrentTime());
        chatActivity.messageEditText.setText("");
        chatActivity.refreshUi();
    }

    public void addFirstMessage() {
        firebaseHandler.addFirstAdminInformation(this,"hello, here you can write all what you want to know, or same problem what you have at this moment", getCurrentTime());
        chatActivity.refreshUi();
    }

    public void callAction() {
        if (ActivityCompat.checkSelfPermission(chatActivity.getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        chatActivity.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + chatActivity.getResources().getString(R.string.phone))));
    }

    public void sendSound() {
        MediaPlayer mp = MediaPlayer.create(chatActivity.getApplicationContext(), R.raw.chat_sound);
        mp.start();
    }

    public String getCurrentTime() {
        long yourmilliseconds = System.currentTimeMillis();
        SimpleDateFormat currentTime = new SimpleDateFormat("MM/dd/yy HH:mm");
        String convertTime = currentTime.format(yourmilliseconds);

        return convertTime;
    }

    public String getDateForFireBase() {
        long yourmilliseconds = System.currentTimeMillis();
        Date resultdate = new Date(yourmilliseconds);
        return resultdate.toString();
    }
}
