package com.usmba.chariaa.Models.Article;

public class Article {
    public String idArticle;
    public String title;
    public String description;
    public String file;
    public String date;


    public String getdate_day(){
        return date.split("/")[2];
    }

    public String getdate_month(){
        int month = Integer.parseInt(date.split("/")[1]);
        String S = "";
        switch (month){
            case 1 : S = "يناير";break;
            case 2 : S = "فبراير";break;
            case 3 : S = "مارس";break;
            case 4 : S = "ابريل";break;
            case 5 : S = "ماي";break;
            case 6 : S = "يونيو";break;
            case 7 : S = "يوليوز";break;
            case 8 : S = "غشت";break;
            case 9 : S = "شتنبر";break;
            case 10 : S = "اكتوبر";break;
            case 11 : S = "نونبر";break;
            case 12 : S = "دجنبر";break;
        }
        return S;
    }
}
