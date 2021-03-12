package com.usmba.chariaa.Models.Etudiant;


import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class DataEtudiant {

    private boolean error;
    private String error_msg;
    private Etudiant etudiant;

    public DataEtudiant(String JsonString){
        try {
            JSONObject jsonObject = new JSONObject(JsonString);
            error = jsonObject.getBoolean("error");
            if(error){
                error_msg = jsonObject.getString("error_msg");
            }
            else {
                etudiant = new Gson().fromJson(jsonObject.getString("etudiant"), Etudiant.class);
            }
        } catch (JSONException e) {
            error = true;
            error_msg = e.getMessage();
        }
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }
}
