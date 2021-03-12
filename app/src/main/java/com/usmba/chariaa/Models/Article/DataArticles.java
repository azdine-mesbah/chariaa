package com.usmba.chariaa.Models.Article;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class DataArticles {

    private List<Article> articles;

    public DataArticles(String JsonString){
        try {
            JSONObject jsonObject = new JSONObject(JsonString);
            this.articles = (List<Article>) jsonObject.get("articles");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public List<Article> getArticles() {
        return articles;
    }
}
