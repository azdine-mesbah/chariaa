package com.usmba.chariaa.Fragments;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebView;
import android.widget.RelativeLayout;

import com.usmba.chariaa.Interfaces.Interactor;
import com.usmba.chariaa.MainActivity;
import com.usmba.chariaa.Models.Article.Article;
import com.usmba.chariaa.R;


public class Fragment_ShowArticle extends DialogFragment implements Interactor {

    MainActivity mainActivity;

    View view;
    RelativeLayout main_content_show_article;
    WebView webView;
    FloatingActionButton btn_save;
    FloatingActionButton btn_delete;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_show_article, container, false);
        initComponents();
        return view;
    }
    @Override
    public void initComponents() {
        main_content_show_article = view.findViewById(R.id.main_content_show_article);
        btn_save = view.findViewById(R.id.btn_save);
        btn_delete = view.findViewById(R.id.btn_delete);

        webView = view.findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(false);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.loadUrl(getUrl(mainActivity.article));

        if(mainActivity.dataEtudiant != null) {
            if(mainActivity.myCollection.myArticles.contains(Integer.parseInt(mainActivity.article.idArticle)))
            {
                btn_delete.setVisibility(View.VISIBLE);
                btn_save.setVisibility(View.GONE);
            }
            else {
                btn_save.setVisibility(View.VISIBLE);
                btn_delete.setVisibility(View.GONE);
            }
        }
        else{
            btn_save.setVisibility(View.GONE);
            btn_delete.setVisibility(View.GONE);
        }

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveArticle();
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteArticle();
            }
        });
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    String getUrl(Article article){

        return  (article.file.toLowerCase().contains(".pdf")? getString(R.string.pdf_viewer) : "") + getString(R.string.file_source) + article.file;
    }

    @Override
    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void HideContent(boolean hide) {
        this.main_content_show_article.getLayoutParams().height = hide ? 0 : RelativeLayout.LayoutParams.MATCH_PARENT;
    }

    void SaveArticle(){

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Save Article ?");
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                btn_save.setVisibility(View.GONE);
                btn_delete.setVisibility(View.VISIBLE);
                mainActivity.SaveArticle();
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();


    }
    void DeleteArticle(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Discard Article ?");
        builder.setPositiveButton("Discard", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                btn_save.setVisibility(View.VISIBLE);
                btn_delete.setVisibility(View.GONE);
                mainActivity.DeleteArticle();
            }
        });
        builder.setNegativeButton("cancel", null);
        builder.show();
    }
    public void showsyncmsg(String msg){
        Snackbar.make(getView(), msg, Snackbar.LENGTH_LONG).show();
    }
}
