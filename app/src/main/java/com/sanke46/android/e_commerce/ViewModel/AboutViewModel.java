package com.sanke46.android.e_commerce.ViewModel;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class AboutViewModel {

    private Context context;

    public AboutViewModel(Context context) {
        this.context = context;
    }

    public void openWebSite() {
        String url = "http://mysuperwebsite";
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
}
