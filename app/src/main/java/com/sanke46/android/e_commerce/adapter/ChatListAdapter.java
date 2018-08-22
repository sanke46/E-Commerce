package com.sanke46.android.e_commerce.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sanke46.android.e_commerce.R;
import com.sanke46.android.e_commerce.model.Chat;

import java.util.ArrayList;
import java.util.List;

public class ChatListAdapter extends ArrayAdapter<Chat> {

    public ChatListAdapter(@NonNull Context context, ArrayList<Chat> listChat) {
        super(context, 0, listChat);
    }

    @SuppressLint("ResourceAsColor")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Chat chat = getItem(position);
        if (convertView == null) {
           convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_chat_user, parent, false);
        }

        LinearLayout mainlayout = convertView.findViewById(R.id.layoutChat);
        LinearLayout chatCloud = convertView.findViewById(R.id.chatCloud);
        ImageView blueArrow = convertView.findViewById(R.id.blueArrow);
        ImageView greyArrow = convertView.findViewById(R.id.greyArrow);
        TextView text = convertView.findViewById(R.id.textChat);
//        TextView timeText = convertView.findViewById(R.id.dateTextChat);

        text.setText(chat.getText());
//        timeText.setText(chat.getTime());
        System.out.println(chat.getUser());
        try {
            if(!chat.getUser().equalsIgnoreCase("admin")){
                mainlayout.setGravity(Gravity.RIGHT);
                greyArrow.setVisibility(View.VISIBLE);
                blueArrow.setVisibility(View.GONE);
                chatCloud.setBackgroundResource(R.drawable.border_chat);
                text.setTextColor(ContextCompat.getColor(getContext(),R.color.BlackGrey));
//                timeText.setTextColor(ContextCompat.getColor(getContext(),R.color.BlackGrey));
            } else  {
                mainlayout.setGravity(Gravity.LEFT);
                blueArrow.setVisibility(View.VISIBLE);
                greyArrow.setVisibility(View.GONE);
                chatCloud.setBackgroundResource(R.drawable.border_chat_blue);
                text.setTextColor(ContextCompat.getColor(getContext(),R.color.bgWhite));
//                timeText.setTextColor(ContextCompat.getColor(getContext(),R.color.bgWhite));
            }
        } catch (NullPointerException e) {
            System.out.println("ERROR CHAT");
        }


        return convertView;
    }
}
