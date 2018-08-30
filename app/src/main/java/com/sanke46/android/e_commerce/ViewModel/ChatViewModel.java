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
import com.sanke46.android.e_commerce.model.Chat;
import com.sanke46.android.e_commerce.ui.navigation.ChatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChatViewModel {

    public ArrayList<Chat> listChat = new ArrayList<>();
    private static final int NOTIFY_ID = 101;
    private Chat chatItem;

    private ChatActivity chatActivity;
    private Helper helper;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private DatabaseReference unicDataBase;
    private FirebaseUser user;
    private String userId;

    public ChatViewModel(ChatActivity chatActivity) {
        this.chatActivity = chatActivity;
        helper = new Helper(chatActivity.getApplicationContext());
        mDatabase = FirebaseDatabase.getInstance().getReference();
        unicDataBase = mDatabase.child("users");
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        userId = user.getUid();
    }

    public ArrayList<Chat> getAllPreviosChat(final ArrayList<Chat> fireBaseChatList, final ChatListAdapter chatAdapter) {

        unicDataBase.child(userId).child("chat").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                fireBaseChatList.clear();
                for (final DataSnapshot snapshot : dataSnapshot.getChildren()){
                    chatItem = snapshot.getValue(Chat.class);
                    fireBaseChatList.add(chatItem);

                }
                chatAdapter.notifyDataSetChanged();
                if(listChat.size() == 0) addFirstMessage();
                helper.doneLoadingActivity(chatActivity.mainLayout, chatActivity.progressBar);
                sendSound();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return fireBaseChatList;
    }

    public void addTextToChat(final ChatListAdapter chatAdapter){
        long yourmilliseconds = System.currentTimeMillis();
        SimpleDateFormat currentTime = new SimpleDateFormat("MM/dd/yy HH:mm");
        Date resultdate = new Date(yourmilliseconds);
        String convertTime = currentTime.format(yourmilliseconds);

        unicDataBase.child(userId).child("chat").child(resultdate.toString()).child("text").setValue(chatActivity.messageEditText.getText().toString());
        unicDataBase.child(userId).child("chat").child(resultdate.toString()).child("time").setValue(convertTime);
        unicDataBase.child(userId).child("chat").child(resultdate.toString()).child("user").setValue(userId);
        chatActivity.messageEditText.setText("");

        chatActivity.refreshUi();
    }

    public void addFirstMessage() {
        long yourmilliseconds = System.currentTimeMillis();
        SimpleDateFormat currentTime = new SimpleDateFormat("MM/dd/yy HH:mm");
        Date resultdate = new Date(yourmilliseconds);
        String convertTime = currentTime.format(yourmilliseconds);

        unicDataBase.child(userId).child("chat").child(resultdate.toString()).child("user").setValue("admin");
        unicDataBase.child(userId).child("chat").child(resultdate.toString()).child("text").setValue("hello, here you can write all what you want to know, or same problem what you have at this moment");
        unicDataBase.child(userId).child("chat").child(resultdate.toString()).child("time").setValue(convertTime);
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
}
