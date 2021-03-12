package com.usmba.chariaa.Fragments.Etudiant;


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


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Etudiant_Documents extends Fragment implements Interactor {

    View view;
    RelativeLayout main_content;
    MainActivity mainActivity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_etudiant_documents, container, false);
    }

    @Override
    public void initComponents() {
        main_content = view.findViewById(R.id.main_content_Etudiant_documents);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(), getString(R.string.next_update), Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public void setMainActivity(MainActivity mainActivity) { this.mainActivity = mainActivity;}

    @Override
    public void HideContent(boolean hide) {
        this.main_content.getLayoutParams().height = hide ? 0 : RelativeLayout.LayoutParams.MATCH_PARENT;
    }

}
