package com.usmba.chariaa.Models.Releve;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Semestre {

    private List<Module> modules;



    private String id;
    private String my;
    private String valid;
    private String valid_txt;
    private String valid_system;
    private String date_inscr;
    private String date_valid;
    private String Nb_module_Valide;
    private String Nb_module_Total_Valide;

    public Semestre(String id, JSONObject jsonObject){
        this.id = id;
        this.my = loadAttribute("my", jsonObject);
        this.valid = loadAttribute("valid", jsonObject);
        this.valid_txt = loadAttribute("valid_txt", jsonObject);
        this.valid_system = loadAttribute("valid_system", jsonObject);
        this.date_inscr = loadAttribute("date_inscr", jsonObject);
        this.date_valid = loadAttribute("date_valid", jsonObject);
        this.Nb_module_Valide = loadAttribute("Nb_module_Valide", jsonObject);
        this.Nb_module_Total_Valide = loadAttribute("Nb_module_Total_Valide", jsonObject);
        LoadModules(jsonObject);
    }
    public List<Module> getModules() {
        return modules;
    }
    public String getId() {
        return id;
    }
    public String getMy() {
        return my;
    }
    public String getValid() {
        return valid;
    }
    public String getValid_txt() {
        return valid_txt;
    }
    public String getValid_system() {
        return valid_system;
    }
    public String getDate_inscr() {
        return date_inscr;
    }
    public String getDate_valid() {
        return date_valid;
    }
    public String getNb_module_Valide() {
        return Nb_module_Valide;
    }
    public String getNb_module_Total_Valide() {
        return Nb_module_Total_Valide;
    }


    String loadAttribute(String Attr, JSONObject jsonObject){
        try {
            return jsonObject.getString(id + "_" + Attr);
        } catch (JSONException e) {
            return "";
        }
    }


    void LoadModules(JSONObject jsonObject){
        modules = new ArrayList();
        int start = 0 ,end = 0;
        switch(id){
            case "S1": start=1; end=7; break;
            case "S2": start=8; end=14; break;
            case "S3": start=15; end=20; break;
            case "S4": start=21; end=26; break;
            case "S5": start=27; end=32; break;
            case "S6": start=33; end=38; break;
        }
        for(int i = start; i <= end; i++){
            Module m = new Module(id + "M" + i, jsonObject);
            modules.add(m);
        }
    }

    public String getAid(){
        String S = "الفصل ";
        switch (id){
            case "S1": S += "الاول";break;
            case "S2": S += "الثاني";break;
            case "S3": S += "الثالث";break;
            case "S4": S += "الرابع";break;
            case "S5": S += "الخامس";break;
            case "S6": S += "السادس";break;
        }
        return S;
    }
}
