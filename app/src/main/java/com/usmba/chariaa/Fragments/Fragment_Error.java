package com.usmba.chariaa.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.usmba.chariaa.Interfaces.Interactor;
import com.usmba.chariaa.MainActivity;
import com.usmba.chariaa.R;


public class Fragment_Error extends Fragment implements Interactor {

    MainActivity mainActivity;
    View view;
    RelativeLayout main_content;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_error, container, false);

        initComponents();

        return view;
    }

    @Override
    public void initComponents() {

        main_content = view.findViewById(R.id.main_content_error);

    }
    @Override
    public void setMainActivity(MainActivity mainActivity) {this.mainActivity = mainActivity;}

    @Override
    public void HideContent(boolean hide) {
        this.main_content.getLayoutParams().height = hide ? 0 : RelativeLayout.LayoutParams.MATCH_PARENT;
    }

}
