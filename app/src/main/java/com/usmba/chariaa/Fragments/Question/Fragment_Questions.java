package com.usmba.chariaa.Fragments.Question;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.usmba.chariaa.Interfaces.Interactor;
import com.usmba.chariaa.MainActivity;
import com.usmba.chariaa.R;


public class Fragment_Questions extends Fragment implements Interactor {


    View view;
    RelativeLayout main_content_Question;
    MainActivity mainActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_questions, container, false);
        initComponents();
        return view;
    }

    @Override
    public void initComponents() {
        main_content_Question = view.findViewById(R.id.main_content_Question);
        Toast.makeText(getContext(), getString(R.string.next_update), Toast.LENGTH_LONG).show();
    }
    @Override
    public void setMainActivity(MainActivity mainActivity) { this.mainActivity = mainActivity;}

    @Override
    public void HideContent(boolean hide) {
        this.main_content_Question.getLayoutParams().height = hide ? 0 : RelativeLayout.LayoutParams.MATCH_PARENT;
    }

}
