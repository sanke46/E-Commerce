package com.sanke46.android.e_commerce.ui.navigation.ProfilePages;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.sanke46.android.e_commerce.R;
import com.sanke46.android.e_commerce.ViewModel.ProfileInfoViewModel;
import com.sanke46.android.e_commerce.model.UserProfile;

public class ProfileInfoActivity extends android.support.v4.app.Fragment {

    private ProfileInfoViewModel profileInfoViewModel;
    public RelativeLayout progressBar;
    public RelativeLayout contentLayout;
    public EditText editTextName;
    public EditText editTextPhone;
    public EditText editTextMail;
    public EditText editTextCity;
    public EditText editTextStreet;
    public EditText editTextHouse;
    public EditText editTextFlat;
    public EditText editTextPassword;
    public ImageView seePassButton;
    public Button buttonToSave;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        profileInfoViewModel = new ProfileInfoViewModel( this);
        contentLayout = view.findViewById(R.id.allProfileActivity);
        progressBar = view.findViewById(R.id.progressBarProfile);
        editTextName = view.findViewById(R.id.name);
        editTextPhone = view.findViewById(R.id.editNumber);
        editTextMail = view.findViewById(R.id.editMail);
        editTextCity = view.findViewById(R.id.city_profile);
        editTextStreet = view.findViewById(R.id.street_profile);
        editTextHouse = view.findViewById(R.id.house_profile);
        editTextFlat = view.findViewById(R.id.flat_profile);
        editTextPassword = view.findViewById(R.id.editPassword);
        buttonToSave = (Button) view.findViewById(R.id.saveButton);
        seePassButton = view.findViewById(R.id.seePassButton);
        buttonToSave.setOnClickListener(view1 -> {
            Log.v(profileInfoViewModel.TAG, "CLICKED to save information");
            profileInfoViewModel.saveAllNewInformation(profileInfoViewModel.getTextEditText(editTextName),
                    profileInfoViewModel.getTextEditText(editTextPhone),
                    profileInfoViewModel.getTextEditText(editTextMail),
                    profileInfoViewModel.getTextEditText(editTextPassword),
                    profileInfoViewModel.getTextEditText(editTextCity),
                    profileInfoViewModel.getTextEditText(editTextStreet),
                    profileInfoViewModel.getTextEditText(editTextHouse),
                    profileInfoViewModel.getTextEditText(editTextFlat));
            Toast.makeText(getContext(), "Thanks,Information saved", Toast.LENGTH_LONG).show();
        });

        seePassButton.setOnClickListener(view12 -> visibleAndInvisiblePassword());
        profileInfoViewModel.changeInformationAboutProfile();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_profile_info, container, false);
    }

    /** invisible or visible password **/
    public void visibleAndInvisiblePassword() {
        profileInfoViewModel.unvisiblePassword = !profileInfoViewModel.unvisiblePassword;
        if (profileInfoViewModel.unvisiblePassword) {
            editTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            editTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }

    /** Automatic change all EditText **/
    public void autoChangeEditTextAboutUser(UserProfile userProfile) {
        editTextName.setText(userProfile.getName());
        editTextMail.setText(userProfile.getEmail());
        editTextPhone.setText(userProfile.getPhone());
        editTextPassword.setText(userProfile.getPassword());
        editTextCity.setText(userProfile.getCity());
        editTextStreet.setText(userProfile.getStreet());
        editTextHouse.setText(userProfile.getHouse());
        editTextFlat.setText(userProfile.getFlat());
    }

}
