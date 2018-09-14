package com.sanke46.android.e_commerce.ViewModel;

import com.sanke46.android.e_commerce.adapter.HistoryListAdapter;
import com.sanke46.android.e_commerce.fireBase.FirebaseHandler;
import com.sanke46.android.e_commerce.model.Order;
import com.sanke46.android.e_commerce.ui.navigation.ProfilePages.ProfileHistoryActivity;

import java.util.ArrayList;

public class ProfileHistoryModelView {
    private FirebaseHandler firebaseHandler = new FirebaseHandler();
    public ArrayList<Order> historyList = new ArrayList<>();

    public void getAllUserHistory(HistoryListAdapter adapter) {
         historyList = firebaseHandler.getHistoryOrder(historyList, adapter);
    }
}
