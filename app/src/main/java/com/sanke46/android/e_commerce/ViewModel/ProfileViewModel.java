package com.sanke46.android.e_commerce.ViewModel;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sanke46.android.e_commerce.model.UserProfile;
import com.sanke46.android.e_commerce.ui.navigation.ProfileActivity;

import java.util.ArrayList;

public class ProfileViewModel {

    public static final String TAG = ProfileActivity.class.getSimpleName();
    public  static final String NAME = "Profile";
    public boolean unvisiblePassword = false;

    private ProfileActivity profileActivity;
    private UserProfile userProfile;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private DatabaseReference unicDataBase;
    private FirebaseUser user;
    private String userId;

    public ProfileViewModel(ProfileActivity profileActivity) {
        this.profileActivity = profileActivity;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        unicDataBase = mDatabase.child("users");
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        userId = user.getUid();

    }

    /**  Button click - change all info about user in Firebase **/
    public void changeInformationAboutProfile() {
        unicDataBase.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                userProfile = new UserProfile();
                String[] arrayTitlesAboutUser = {"name", "phone", "email", "password", "city", "street", "house", "flat"};
                ArrayList<String> dbAboutUser = new ArrayList<>();

                for (String title : arrayTitlesAboutUser) {
                    if (title.equals("name") || title.equals("email") || title.equals("phone") || title.equals("password")) {
                        try {
                            dbAboutUser.add(dataSnapshot.child(title).getValue().toString());
                        } catch (NullPointerException e) {
                            dbAboutUser.add("");
                            Log.i(TAG, title);
                        }
                    } else {
                        try {
                            dbAboutUser.add(dataSnapshot.child("adress").child(title).getValue().toString());
                        } catch (NullPointerException e) {
                            dbAboutUser.add("");
                            Log.i(TAG, title);
                        }
                    }
                }

                userProfile.setName(dbAboutUser.get(0));
                userProfile.setPhone(dbAboutUser.get(1));
                userProfile.setEmail(dbAboutUser.get(2));
                userProfile.setPassword(dbAboutUser.get(3));
                userProfile.setCity(dbAboutUser.get(4));
                userProfile.setStreet(dbAboutUser.get(5));
                userProfile.setHouse(dbAboutUser.get(6));
                userProfile.setFlat(dbAboutUser.get(7));

                profileActivity.autoChangeEditTextAboutUser(userProfile);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /**  Save new Inctance to FireBase **/
    public void saveAllNewInformation(String name,
                                      String phoneNumber,
                                      String email,
                                      String password,
                                      String city,
                                      String street,
                                      String house,
                                      String flat) {
        mDatabase.child("users").child(userId).child("name").setValue(name);
        mDatabase.child("users").child(userId).child("phone").setValue(phoneNumber);
        mDatabase.child("users").child(userId).child("email").setValue(email);
        mDatabase.child("users").child(userId).child("password").setValue(password);
        user.updatePassword(password).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "Password updated");
                } else {
                    Log.d(TAG, "Error password not updated");
                }
            }
        });
        mDatabase.child("users").child(userId).child("adress").child("city").setValue(city);
        mDatabase.child("users").child(userId).child("adress").child("street").setValue(street);
        mDatabase.child("users").child(userId).child("adress").child("house").setValue(house);
        mDatabase.child("users").child(userId).child("adress").child("flat").setValue(flat);
    }

    /** Get String inside EditText **/
    public String getTextEditText(EditText input) {
        return input.getText().toString();
    }
}
