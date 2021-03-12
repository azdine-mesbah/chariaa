package com.usmba.chariaa.Models.Releve;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DataReleve {

    private List<Semestre> Semestres = new ArrayList<>();
    private String S1S2_takamol;
    private String S3S4_takamol;
    private String Deug_my;
    private String Deug_mention;
    private String DEUG_N_ordre;
    private String DEUG_date;
    private String License_my;
    private String License_mention;
    private String License_N_ordre;
    private String License_date;

    public DataReleve(String JsonString){

        try {
            JSONObject jsonObject = new JSONObject(JsonString);

            Semestre S;
            for(int i = 1; i <= 6; i++){
                S = new Semestre("S" + i, jsonObject);
                if(!S.getDate_inscr().equals(""))
                    this.Semestres.add(S);
            }
            S1S2_takamol = getAttribute(jsonObject, "S1S2_takamol");
            S1S2_takamol = getAttribute(jsonObject, "S1S2_takamol");
            Deug_my = getAttribute(jsonObject, "Deug_my");
            Deug_mention = getAttribute(jsonObject, "Deug_mention");
            DEUG_N_ordre = getAttribute(jsonObject, "DEUG_N_ordre");
            DEUG_date = getAttribute(jsonObject, "DEUG_date");
            License_my = getAttribute(jsonObject, "License_my");
            License_mention = getAttribute(jsonObject, "License_mention");
            License_N_ordre = getAttribute(jsonObject, "License_N_ordre");
            License_date = getAttribute(jsonObject, "License_date");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private String getAttribute(JSONObject jsonObject, String Attr){
        try {
            return jsonObject.getString(Attr);
        } catch (JSONException e) {
            return "";
        }
    }

    public List<Semestre> getSemestres() {
        return Semestres;
    }

    public void setSemestres(List<Semestre> semestres) {
        Semestres = semestres;
    }

    public String getS1S2_takamol() {
        return S1S2_takamol;
    }

    public void setS1S2_takamol(String s1S2_takamol) {
        S1S2_takamol = s1S2_takamol;
    }

    public String getS3S4_takamol() {
        return S3S4_takamol;
    }

    public void setS3S4_takamol(String s3S4_takamol) {
        S3S4_takamol = s3S4_takamol;
    }

    public String getDeug_my() {
        return Deug_my;
    }

    public void setDeug_my(String deug_my) {
        Deug_my = deug_my;
    }

    public String getDeug_mention() {
        return Deug_mention;
    }

    public void setDeug_mention(String deug_mention) {
        Deug_mention = deug_mention;
    }

    public String getDEUG_N_ordre() {
        return DEUG_N_ordre;
    }

    public void setDEUG_N_ordre(String DEUG_N_ordre) {
        this.DEUG_N_ordre = DEUG_N_ordre;
    }

    public String getDEUG_date() {
        return DEUG_date;
    }

    public void setDEUG_date(String DEUG_date) {
        this.DEUG_date = DEUG_date;
    }

    public String getLicense_my() {
        return License_my;
    }

    public void setLicense_my(String license_my) {
        License_my = license_my;
    }

    public String getLicense_mention() {
        return License_mention;
    }

    public void setLicense_mention(String license_mention) {
        License_mention = license_mention;
    }

    public String getLicense_N_ordre() {
        return License_N_ordre;
    }

    public void setLicense_N_ordre(String license_N_ordre) {
        License_N_ordre = license_N_ordre;
    }

    public String getLicense_date() {
        return License_date;
    }

    public void setLicense_date(String license_date) {
        License_date = license_date;
    }



}
