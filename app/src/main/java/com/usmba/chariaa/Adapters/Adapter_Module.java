package com.usmba.chariaa.Adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

import com.usmba.chariaa.Models.Releve.Module;
import com.usmba.chariaa.R;




public class Adapter_Module extends RecyclerView.Adapter<Adapter_Module.ModuleViewHolder> {

    List<Module> modules;
    Context context;

    public Adapter_Module(Context context, List<Module> modules){
        this.modules = modules;
        this.context = context;
    }

    @NonNull
    @Override
    public ModuleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return new ModuleViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_module, viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ModuleViewHolder moduleViewHolder, int i) {
        final Module module = modules.get(i);
        final ModuleViewHolder holder = moduleViewHolder;
        holder.txt_Module_Titre.setText(module.getTitre());
        holder.txt_Module_my.setText(module.getMy());

        if(module.getValid().equals("مستوف"))
            holder.img_Module_valid.setImageResource(R.drawable.icon_valid);
        else holder.img_Module_valid.setImageResource(R.drawable.icon_encour);

    }

    @Override
    public int getItemCount() {
        return modules.size();
    }

    class ModuleViewHolder extends RecyclerView.ViewHolder {

        TextView txt_Module_Titre;
        TextView txt_Module_my;
        ImageView img_Module_valid;
        CardView Module_cardview;
        public ModuleViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_Module_Titre = itemView.findViewById(R.id.txt_Module_titre);
            txt_Module_my = itemView.findViewById(R.id.txt_Module_my);
            img_Module_valid = itemView.findViewById(R.id.img_Module_valid);
            Module_cardview = itemView.findViewById(R.id.Module_cardview);
        }
    }
}
