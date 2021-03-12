package com.usmba.chariaa.Fragments.Home;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.usmba.chariaa.Adapters.Adapter_Articles;
import com.usmba.chariaa.Interfaces.Interactor;
import com.usmba.chariaa.MainActivity;
import com.usmba.chariaa.Models.Article.Article;
import com.usmba.chariaa.Models.Article.DataArticles;
import com.usmba.chariaa.R;
import com.usmba.chariaa.Tools.POSTData;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Fragment_Home_Articles extends Fragment implements Interactor {

    MainActivity mainActivity;

    View view;
    RelativeLayout main_content_Home_articles;
    RecyclerView recyclerView_Articles;
    ProgressBar progressBar_Articles;
    Adapter_Articles articlesAdapter;
    List<Article> articles = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home_articles, container, false);
        initComponents();
        loadArticles();
        return view;
    }
    @Override
    public void initComponents() {
        main_content_Home_articles = view.findViewById(R.id.main_content_Home_articles);
        recyclerView_Articles = view.findViewById(R.id.recyclerview_Articles);
        progressBar_Articles = view.findViewById(R.id.progressBar_Articles);

        recyclerView_Articles.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView_Articles.addOnScrollListener(OnScroll());

        articlesAdapter = new Adapter_Articles(getContext(), articles, mainActivity);
        recyclerView_Articles.setAdapter(articlesAdapter);
    }

    void loadArticles(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject parameters = new JSONObject();
                            parameters.put("index",String.valueOf(articles.size()));
                            parameters.put("nins","");//nins is empty to load all articles
                            String S = POSTData.getJSONString(getString(R.string.main_url)+getString(R.string.data_Article), parameters);
                            articles.addAll(new Gson().fromJson(S, DataArticles.class).getArticles());
                        }
                        catch (IOException e) { Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                        catch (JSONException e) { Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();}

                        articlesAdapter.notifyDataSetChanged();
                        progressBar_Articles.setVisibility(View.GONE);
                    }
                });
            }
        }).start();
    }

    RecyclerView.OnScrollListener OnScroll(){
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy > 0)
                {
                    if(!recyclerView.canScrollVertically(RecyclerView.FOCUS_DOWN))
                    {
                        progressBar_Articles.setVisibility(View.VISIBLE);
                        loadArticles();
                    }
                }
            }
        };
    }

    @Override
    public void setMainActivity(MainActivity mainActivity) { this.mainActivity = mainActivity;}

    @Override
    public void HideContent(boolean hide) {
        this.main_content_Home_articles.getLayoutParams().height = hide ? 0 : RelativeLayout.LayoutParams.MATCH_PARENT;
    }


}
