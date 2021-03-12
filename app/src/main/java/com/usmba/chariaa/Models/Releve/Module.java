package com.usmba.chariaa.Models.Releve;

import org.json.JSONException;
import org.json.JSONObject;

public class Module {

    private String id;
    private String my;
    private String valid;
    private String titre;
    private String valid_txt;

    public Module(String id, JSONObject jsonObject){

        try {
            this.id = id;
            this.my = jsonObject.getString(id + "_my");
            this.valid = jsonObject.getString(id + "_valid");
            this.titre = jsonObject.getString(id + "_titre");
            this.valid_txt = jsonObject.getString(id + "_valid_txt");
        } catch (JSONException e) {
            e.printStackTrace();
        }

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
    public String getTitre() {
        return titre;
    }
    public String getValid_txt() {
        return valid_txt;
    }
 }
