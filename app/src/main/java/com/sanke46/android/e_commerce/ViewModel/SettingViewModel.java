package com.sanke46.android.e_commerce.ViewModel;

import android.support.annotation.NonNull;
import android.widget.Switch;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sanke46.android.e_commerce.ui.navigation.SettingActivity;

public class SettingViewModel {

    private SettingActivity settingActivity;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private DatabaseReference unicDataBase;
    private FirebaseUser user;
    private String userId;

    public SettingViewModel(SettingActivity settingActivity) {
        this.settingActivity = settingActivity;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        unicDataBase = mDatabase.child("users");
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        userId = user.getUid();
    }

    public void updateSwitches(Switch sms, Switch notification, Switch email) {
        unicDataBase.child(userId).child("setting").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                sms.setChecked((Boolean) dataSnapshot.child("sms").getValue());
                notification.setChecked((Boolean) dataSnapshot.child("notification").getValue());
                email.setChecked((Boolean) dataSnapshot.child("email").getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void saveSwitches(Switch sms, Switch notification, Switch email) {
        unicDataBase.child(userId).child("setting").child("sms").setValue(sms.isChecked());
        unicDataBase.child(userId).child("setting").child("notification").setValue(notification.isChecked());
        unicDataBase.child(userId).child("setting").child("email").setValue(email.isChecked());
    }
}
