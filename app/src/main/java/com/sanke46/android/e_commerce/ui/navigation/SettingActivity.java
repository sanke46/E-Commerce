package com.sanke46.android.e_commerce.ui.navigation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.sanke46.android.e_commerce.R;
import com.sanke46.android.e_commerce.ViewModel.SettingViewModel;

public class SettingActivity extends AppCompatActivity {

    private SettingViewModel settingViewModel;
    private Switch smsPush;
    private Switch notificationPush;
    private Switch emailPush;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_go_back_left_arrow));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        settingViewModel = new SettingViewModel(this);
        smsPush = findViewById(R.id.smsPush);
        notificationPush = findViewById(R.id.notificationPush);
        emailPush = findViewById(R.id.emailPush);

        smsPush.setOnClickListener(view -> settingViewModel.saveSwitches(smsPush,notificationPush,emailPush));
        notificationPush.setOnClickListener(view -> settingViewModel.saveSwitches(smsPush,notificationPush,emailPush));
        emailPush.setOnClickListener(view -> settingViewModel.saveSwitches(smsPush,notificationPush,emailPush));

        refreshUI();
    }

    public void refreshUI() {
        settingViewModel.updateSwitches(smsPush,notificationPush,emailPush);
    }
}
