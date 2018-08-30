package com.sanke46.android.e_commerce.ui.navigation;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.sanke46.android.e_commerce.MainActivity;
import com.sanke46.android.e_commerce.R;
import com.sanke46.android.e_commerce.ViewModel.ChatViewModel;
import com.sanke46.android.e_commerce.adapter.ChatListAdapter;


import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    private ChatViewModel chatViewModel;
    private ListView mChatListView;
    private ChatListAdapter mChatListViewAdapter;
    private static final int NOTIFY_ID = 101;

    public RelativeLayout mainLayout;
    public RelativeLayout progressBar;
    public EditText messageEditText;
    private ImageButton sendMessage;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        chatViewModel = new ChatViewModel(this);

        mainLayout = findViewById(R.id.mainChatLayout);
        progressBar = findViewById(R.id.progressChat);
        messageEditText = findViewById(R.id.sendMessage);
        sendMessage = findViewById(R.id.buttonSendMessage);

        Toolbar toolbar = findViewById(R.id.toolbarChat);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_go_back_left_arrow));
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        // Discount [RecycleView + Adapter + LayoutManager + FB]

        mChatListView = findViewById(R.id.chatListView);
        mChatListViewAdapter = new ChatListAdapter(this, chatViewModel.listChat);
        mChatListView.setAdapter(mChatListViewAdapter);
        chatViewModel.getAllPreviosChat(chatViewModel.listChat, mChatListViewAdapter);

        sendMessage.setOnClickListener(view -> chatViewModel.addTextToChat(mChatListViewAdapter));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.call_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.call_action:
                chatViewModel.callAction();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void refreshUi() {
        mChatListViewAdapter.notifyDataSetChanged();
    }

}
