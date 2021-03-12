package com.usmba.chariaa.Fragments.Etudiant;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.usmba.chariaa.Adapters.Adapter_SectionsPager;
import com.usmba.chariaa.Interfaces.Interactor;
import com.usmba.chariaa.MainActivity;
import com.usmba.chariaa.R;


public class Fragment_Etudiant extends Fragment implements Interactor {

    View view;
    MainActivity mainActivity;

    public Fragment_Etudiant_Profile fragment_etudiant_profile;
    public Fragment_Etudiant_Releve fragment_etudiant_releve;
    public Fragment_Etudiant_Documents fragment_etudiant_documents;

    RelativeLayout main_content;
    ViewPager view_pager;
    TabLayout tab_layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_etudiant, container, false);
        LoadData();
        return view;
    }
    @Override
    public void initComponents() {

        fragment_etudiant_profile = new Fragment_Etudiant_Profile();
        fragment_etudiant_releve = new Fragment_Etudiant_Releve();
        fragment_etudiant_documents = new Fragment_Etudiant_Documents();
        fragment_etudiant_profile.setMainActivity(mainActivity);
        fragment_etudiant_releve.setMainActivity(mainActivity);
        fragment_etudiant_documents.setMainActivity(mainActivity);


        main_content = view.findViewById(R.id.main_content_Etudiant);
        view_pager = view.findViewById(R.id.Etudiant_view_pager);
        setupViewPager(view_pager);
        tab_layout = view.findViewById(R.id.Etudiant_tab_layout);
        tab_layout.setupWithViewPager(view_pager);
        view_pager.setCurrentItem(0);
    }


    private void setupViewPager(ViewPager viewPager) {
        Adapter_SectionsPager adapter = new Adapter_SectionsPager(getChildFragmentManager());
        adapter.addFragment(fragment_etudiant_profile, "معلوماتي");
        adapter.addFragment(fragment_etudiant_releve, "بيان النقط");
        adapter.addFragment(fragment_etudiant_documents, "طلب وثيقة");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void setMainActivity(MainActivity mainActivity) { this.mainActivity = mainActivity;}

    @Override
    public void HideContent(boolean hide) {
        this.main_content.getLayoutParams().height = hide ? 0 : RelativeLayout.LayoutParams.MATCH_PARENT;
    }

    void LoadData(){

        if(mainActivity.dataEtudiant == null)
            mainActivity.CallLoginForm();
        else {
            initComponents();
        }
    }
}
