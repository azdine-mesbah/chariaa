package com.usmba.chariaa.Models;


import com.google.gson.Gson;
import java.util.List;

public class DataMyCollection {
    public List<Integer> myArticles ;
    public List<Integer> myBooks;
    public List<Integer> myQuestions;

    public DataMyCollection(String JsonString){

        myArticles = new Gson().fromJson(JsonString, DataMyCollection.class).myArticles;
        myBooks = new Gson().fromJson(JsonString, DataMyCollection.class).myBooks;
        myQuestions = new Gson().fromJson(JsonString, DataMyCollection.class).myQuestions;

    }
}
