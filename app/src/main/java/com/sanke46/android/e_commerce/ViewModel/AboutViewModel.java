package com.sanke46.android.e_commerce.ViewModel;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;

import com.sanke46.android.e_commerce.R;
import com.sanke46.android.e_commerce.ui.navigation.ChatActivity;

import java.util.Locale;

public class AboutViewModel {

    private Context context;
    private static final String url = "http://ilyafedoseev.ru/";
    private static final String mail = "abc@gmail.com";

    public AboutViewModel(Context context) {
        this.context = context;
    }

    public void openWebSite() {
        try {
            Intent i = new Intent("android.intent.action.MAIN");
            i.setComponent(ComponentName.unflattenFromString("com.android.chrome/com.android.chrome.Main"));
            i.addCategory("android.intent.category.LAUNCHER");
            i.setData(Uri.parse(url));
            context.startActivity(i);
        }
        catch(ActivityNotFoundException e) {
            // Chrome is not installed
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            context.startActivity(i);
        }
    }

    public void callPhone() {
        if (ActivityCompat.checkSelfPermission(context.getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        context.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + context.getResources().getString(R.string.phone))));
    }

    public void openChat() {
        context.startActivity(new Intent(context, ChatActivity.class));
    }

    public void sendToEmail() {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto",mail, null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject to E-commerce");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Hello, ");
        context.startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }


    public void openGoogleMap() {
        String uri = String.format(Locale.ENGLISH, "geo:%f,%f", 51.126368, 1.134553);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        context.startActivity(intent);
    }
}
