package com.usmba.chariaa.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.usmba.chariaa.Models.Releve.Module;
import com.usmba.chariaa.Models.Releve.Semestre;
import com.usmba.chariaa.R;

import java.util.List;


public class Adapter_Semester extends RecyclerView.Adapter<Adapter_Semester.SemestreViewHolder> {
    List<Semestre> semestres;
    Context context;
    View parentview;

    public Adapter_Semester(Context context, List<Semestre> semestres, View parentview){
        this.semestres = semestres;
        this.context = context;
        this.parentview = parentview;
    }
    @NonNull
    @Override
    public SemestreViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

         return new SemestreViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_semestre, viewGroup, false), semestres.get(i).getModules());
    }

    @Override
    public void onBindViewHolder(@NonNull SemestreViewHolder semestreViewHolder, int i) {

        final Semestre semestre = semestres.get(i);
        final SemestreViewHolder holder = semestreViewHolder;

        holder.txt_Semestre_Aid.setText(semestre.getAid());

        if(!semestre.getDate_valid().equals(""))
            holder.img_Semestre_valid.setImageResource(R.drawable.icon_valid);
        else if(!semestre.getDate_inscr().equals(""))
            holder.img_Semestre_valid.setImageResource(R.drawable.icon_encour);
        else holder.img_Semestre_valid.setImageResource(R.drawable.icon_non_inscris);

        SemestreDetail(View.GONE);

        holder.card_view.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
                showSemestre(semestre);
        }
        });
    }


    private void showSemestre(Semestre semestre){
        SemestreDetail(View.VISIBLE);
        RecyclerView recyclerView = parentview.findViewById(R.id.Modules_recyclerview);
        TextView txt_Semestre_Titre = parentview.findViewById(R.id.txt_Semestre_Titre);
        TextView txt_Semestre_My = parentview.findViewById(R.id.txt_Semestre_My);
        TextView txt_Semestre_valid_txt = parentview.findViewById(R.id.txt_Semestre_valid);
        txt_Semestre_Titre.setText(semestre.getAid());
        txt_Semestre_My.setText(semestre.getMy());
        txt_Semestre_valid_txt.setText(semestre.getValid());
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new Adapter_Module(context, semestre.getModules()));

    }

    void SemestreDetail(int visibility){
        View view_Semestre_detail = parentview.findViewById(R.id.view_Semestre_detail);
        View Modules_recyclerview = parentview.findViewById(R.id.Modules_recyclerview);

        view_Semestre_detail.setVisibility(visibility);
        Modules_recyclerview.setVisibility(visibility);
    }
    @Override
    public int getItemCount() {
        return semestres.size();
    }

    class SemestreViewHolder extends RecyclerView.ViewHolder {
        Context context;
        List<Module> modules;

        TextView txt_Semestre_Aid;
        ImageView img_Semestre_valid;
        CardView card_view;

        public SemestreViewHolder(@NonNull View view, List<Module> modules) {
            super(view);
            context = view.getContext();
            this.modules = modules;

            txt_Semestre_Aid = view.findViewById(R.id.txt_Semestre_Aid);
            img_Semestre_valid = view.findViewById(R.id.img_Semestre_valid);
            card_view = view.findViewById(R.id.Semestre_card_view);

        }
    }
}
