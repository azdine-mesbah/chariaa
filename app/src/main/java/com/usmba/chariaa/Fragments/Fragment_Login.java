package com.usmba.chariaa.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.usmba.chariaa.Interfaces.Interactor;
import com.usmba.chariaa.MainActivity;
import com.usmba.chariaa.Models.Etudiant.DataEtudiant;
import com.usmba.chariaa.Models.Releve.DataReleve;
import com.usmba.chariaa.R;
import com.usmba.chariaa.Tools.POSTData;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class Fragment_Login extends Fragment implements Interactor {

    MainActivity mainActivity;



    View view;
    RelativeLayout main_content_login;
    Button btn_login;
    EditText txt_Nins;
    EditText txt_Nins_Year;
    EditText txt_DN_Day;
    EditText txt_DN_Month;
    EditText txt_DN_Year;
    ProgressBar Login_ProgressBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);

        initComponents();

        return view;
    }


    @Override
    public void initComponents() {
        main_content_login = view.findViewById(R.id.main_content_login);
        txt_Nins = view.findViewById(R.id.txt_Nins);
        txt_Nins_Year = view.findViewById(R.id.txt_Nins_Year);
        txt_DN_Day = view.findViewById(R.id.txt_DN_Day);
        txt_DN_Month = view.findViewById(R.id.txt_DN_Month);
        txt_DN_Year = view.findViewById(R.id.txt_DN_Year);
        Login_ProgressBar = view.findViewById(R.id.Login_ProgressBar);
        Login_ProgressBar.setVisibility(View.GONE);
        btn_login = view.findViewById(R.id.btn_Login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    @Override
    public void setMainActivity(MainActivity mainActivity)
    {
        this.mainActivity = mainActivity;
    }

    @Override
    public void HideContent(boolean hide) {
        this.main_content_login.getLayoutParams().height = hide ? 0 : RelativeLayout.LayoutParams.MATCH_PARENT;
    }

    void login(){
        if(checkIputs())
        {
            waiting(true);
            String nins =  txt_Nins.getText().toString() + "/" + txt_Nins_Year.getText().toString();
            String DN = txt_DN_Day.getText().toString() + "/" + txt_DN_Month.getText().toString() + "/" + txt_DN_Year.getText().toString();
            mainActivity.Login(nins, DN, true);
            ClearInputs();
            waiting(false);
        }

    }


    public void waiting(boolean wait) {
        if(wait)
        {
            Login_ProgressBar.setVisibility(View.VISIBLE);
            txt_Nins.setEnabled(!wait);
            txt_Nins_Year.setEnabled(!wait);
            txt_DN_Day.setEnabled(!wait);
            txt_DN_Month.setEnabled(!wait);
            txt_DN_Year.setEnabled(!wait);
        }
        else {
            Login_ProgressBar.setVisibility(View.GONE);
            txt_Nins.setEnabled(!wait);
            txt_Nins_Year.setEnabled(!wait);
            txt_DN_Day.setEnabled(!wait);
            txt_DN_Month.setEnabled(!wait);
            txt_DN_Year.setEnabled(!wait);
        }
    }

    boolean checkIputs(){
        if(txt_Nins.getText().toString().equals("") || txt_Nins_Year.getText().toString().equals("") || txt_DN_Day.getText().toString().equals("") || txt_DN_Month.getText().toString().equals("") || txt_DN_Year.getText().toString().equals(""))
            return false;
        return true;
    }

    void ClearInputs(){
        txt_Nins.setText(null);
        txt_Nins_Year.setText(null);
        txt_DN_Day.setText(null);
        txt_DN_Month.setText(null);
        txt_DN_Year.setText(null);
    }
}