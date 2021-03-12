package com.usmba.chariaa.Fragments.Etudiant;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.usmba.chariaa.Interfaces.Interactor;
import com.usmba.chariaa.MainActivity;
import com.usmba.chariaa.R;


public class Fragment_Etudiant_Profile extends Fragment implements Interactor {

    MainActivity mainActivity;

    RelativeLayout main_content_Etudiant_profile;

    View view;
    TextView txt_Nins ;
    TextView txt_AFullName ;
    TextView txt_FullName ;
    TextView txt_CNE ;
    TextView txt_CIN;
    TextView txt_dateNaiss ;
    TextView txt_lieuNaiss ;
    TextView txt_Filiere ;
    TextView txt_Season1 ;
    TextView txt_Season2 ;
    View View_Filiere;
    ProgressBar progressBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_etudiant_profile, container, false);

        initComponents();

        FillEtudiantView();

        return view;
    }
    @Override
    public void initComponents(){
        main_content_Etudiant_profile = view.findViewById(R.id.main_content_Etudiant_profile);
        txt_Nins = view.findViewById(R.id.txt_Nins);
        txt_AFullName = view.findViewById(R.id.txt_Anom_Aprenom);
        txt_FullName = view.findViewById(R.id.txt_Nom_Prenom);
        txt_CNE = view.findViewById(R.id.txt_CNE);
        txt_CIN = view.findViewById(R.id.txt_CIN);
        txt_dateNaiss = view.findViewById(R.id.txt_dateNaiss);
        txt_lieuNaiss = view.findViewById(R.id.txt_lieuNaiss);
        txt_Filiere = view.findViewById(R.id.txt_Filiere);
        txt_Season1 = view.findViewById(R.id.txt_Season1);
        txt_Season2 = view.findViewById(R.id.txt_Season2);
        View_Filiere = view.findViewById(R.id.View_Filiere);
        progressBar = view.findViewById(R.id.progressBar);

    }

    @Override
    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void HideContent(boolean hide) {
        this.main_content_Etudiant_profile.getLayoutParams().height = hide ? 0 : RelativeLayout.LayoutParams.MATCH_PARENT;
    }

    public void FillEtudiantView(){
        txt_Nins.setText(mainActivity.dataEtudiant.getEtudiant().getNins());
        txt_AFullName.setText(mainActivity.dataEtudiant.getEtudiant().getAnom() + " " + mainActivity.dataEtudiant.getEtudiant().getAPrenom());
        txt_FullName.setText(mainActivity.dataEtudiant.getEtudiant().getNom() + " " + mainActivity.dataEtudiant.getEtudiant().getPrenom());
        txt_CNE.setText(mainActivity.dataEtudiant.getEtudiant().getCNE());
        txt_CIN.setText(mainActivity.dataEtudiant.getEtudiant().getCIN());
        txt_dateNaiss.setText(mainActivity.dataEtudiant.getEtudiant().getDN());
        txt_lieuNaiss.setText(mainActivity.dataEtudiant.getEtudiant().getLN());
        txt_Filiere.setText("-");
        txt_Season1.setText(mainActivity.dataEtudiant.getEtudiant().getSeason(1));
        txt_Season2.setText(mainActivity.dataEtudiant.getEtudiant().getSeason(2));
        Boolean S5 = mainActivity.dataEtudiant.getEtudiant().getInscriptionSoudassiya1().contains("S5");
        Boolean S6 = mainActivity.dataEtudiant.getEtudiant().getInscriptionSoudassiya2().contains("S6");
        if(S5 || S6)
        {
            View_Filiere.setVisibility(View.VISIBLE);
            txt_Filiere.setText(mainActivity.dataEtudiant.getEtudiant().getChoix());
        }
    progressBar.setVisibility(View.GONE);
    HideContent(false);
    }
}
