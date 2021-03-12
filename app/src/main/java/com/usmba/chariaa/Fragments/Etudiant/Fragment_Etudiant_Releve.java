package com.usmba.chariaa.Fragments.Etudiant;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.usmba.chariaa.Adapters.Adapter_Semester;
import com.usmba.chariaa.Interfaces.Interactor;
import com.usmba.chariaa.MainActivity;
import com.usmba.chariaa.Models.Releve.DataReleve;
import com.usmba.chariaa.R;
import com.usmba.chariaa.Tools.POSTData;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class Fragment_Etudiant_Releve extends Fragment implements Interactor {

    MainActivity mainActivity;
    View view;

    RelativeLayout main_content_Etudiant_releve;
    RecyclerView recyclerView_Semestre;
    RecyclerView recyclerView_Modules;
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_etudiant_releve, container, false);
        initComponents();
        FillDataReleve();
        return view;
    }

    @Override
    public void initComponents() {
        main_content_Etudiant_releve = view.findViewById(R.id.main_content_Etudiant_releve);
        recyclerView_Semestre = view.findViewById(R.id.Semestre_recyclerview);
        recyclerView_Modules = view.findViewById(R.id.Modules_recyclerview);
        progressBar = view.findViewById(R.id.progressBar);
    }
    @Override
    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void HideContent(boolean hide) {this.main_content_Etudiant_releve.getLayoutParams().height = hide ? 0 : RelativeLayout.LayoutParams.MATCH_PARENT;}

    public void FillDataReleve(){

        recyclerView_Semestre.setLayoutManager(new GridLayoutManager(getContext(), mainActivity.dataReleve.getSemestres().size()/2));
        recyclerView_Semestre.setAdapter(new Adapter_Semester(getContext(), mainActivity.dataReleve.getSemestres(), view));
        recyclerView_Semestre.setHasFixedSize(true);
        progressBar.setVisibility(View.GONE);
        HideContent(false);
    }


}
