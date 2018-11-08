package com.sanke46.android.e_commerce.ui.navigation.ProfilePages;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.sanke46.android.e_commerce.R;
import com.sanke46.android.e_commerce.Utility.Helper;
import com.sanke46.android.e_commerce.ViewModel.ProfileHistoryModelView;
import com.sanke46.android.e_commerce.adapter.ChatListAdapter;
import com.sanke46.android.e_commerce.adapter.HistoryListAdapter;
import com.sanke46.android.e_commerce.adapter.HistoryListItemsAdapter;
import com.sanke46.android.e_commerce.fireBase.FirebaseHandler;

public class ProfileHistoryActivity extends Fragment {

    private ProfileHistoryModelView profileHistoryModelView;
    private ListView historyListView;
    private HistoryListAdapter historyListAdapter;
    private Helper helper = new Helper(getContext());

    private RelativeLayout emptyScreen;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_profile_history, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        profileHistoryModelView = new ProfileHistoryModelView();
        emptyScreen = view.findViewById(R.id.emptyHistory);
        historyListView = view.findViewById(R.id.history_listview);

        historyListAdapter = new HistoryListAdapter(getContext(), profileHistoryModelView.historyList);
        historyListView.setAdapter(historyListAdapter);
        profileHistoryModelView.getAllUserHistory(historyListAdapter);

        helper.isEmpty(profileHistoryModelView.historyList.size(), emptyScreen, historyListView);

        super.onViewCreated(view, savedInstanceState);
    }

//    private Integer getInt() {
//        Integer resultList = 0;
//        if(profileHistoryModelView.historyList == null) {
//            return resultList;
//        } else {
//            return profileHistoryModelView.historyList.size();
//        }
//    }
}
