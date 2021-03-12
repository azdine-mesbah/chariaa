package com.usmba.chariaa.Fragments.Home;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.usmba.chariaa.Adapters.Adapter_SectionsPager;
import com.usmba.chariaa.Interfaces.Interactor;
import com.usmba.chariaa.MainActivity;
import com.usmba.chariaa.R;


public class Fragment_Home extends Fragment implements Interactor {

    View view;
    MainActivity mainActivity;

    public Fragment_Home_Articles fragment_home_articles;
    public Fragment_Home_Library fragment_home_library;

    RelativeLayout main_content_Home;
    TabLayout home_tab_layout;
    ViewPager home_view_pager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        initComponents();
        return view;

    }

    @Override
    public void initComponents() {

        fragment_home_articles = new Fragment_Home_Articles();
        fragment_home_articles.setMainActivity(this.mainActivity);
        fragment_home_library = new Fragment_Home_Library();
        fragment_home_library.setMainActivity(this.mainActivity);

        Adapter_SectionsPager adapter = new Adapter_SectionsPager(getChildFragmentManager());
        adapter.addFragment(fragment_home_articles, "اعلانات");
        adapter.addFragment(fragment_home_library, "المكتبة");

        main_content_Home = view.findViewById(R.id.main_content_Home);
        home_tab_layout = view.findViewById(R.id.home_tab_layout);
        home_view_pager = view.findViewById(R.id.home_view_pager);
        home_view_pager.setAdapter(adapter);
        home_tab_layout.setupWithViewPager(home_view_pager);
    }

    @Override
    public void setMainActivity(MainActivity mainActivity){this.mainActivity = mainActivity;}

    @Override
    public void HideContent(boolean hide) {
        this.main_content_Home.getLayoutParams().height = hide ? 0 : RelativeLayout.LayoutParams.MATCH_PARENT;
    }




}
