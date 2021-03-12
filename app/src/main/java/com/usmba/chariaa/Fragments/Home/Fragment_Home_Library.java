package com.usmba.chariaa.Fragments.Home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.RelativeLayout;

import com.usmba.chariaa.Interfaces.Interactor;
import com.usmba.chariaa.MainActivity;
import com.usmba.chariaa.R;

public class Fragment_Home_Library extends Fragment implements Interactor {

    MainActivity mainActivity;

    View view;
    RelativeLayout main_content_Home_library;
    WebView Library_webview;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home_library, container, false);
        initComponents();
        return view;
    }
    @Override
    public void initComponents() {
        main_content_Home_library = view.findViewById(R.id.main_content_Home_library);
        Library_webview = view.findViewById(R.id.Library_webview);
        Library_webview.getSettings().setJavaScriptEnabled(false);
        Library_webview.getSettings().setBuiltInZoomControls(true);
        Library_webview.getSettings().setUseWideViewPort(true);
        Library_webview.getSettings().setLoadWithOverviewMode(true);
        Library_webview.loadUrl(getString(R.string.url_library));
    }
    @Override
    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void HideContent(boolean hide) {
        this.main_content_Home_library.getLayoutParams().height = hide ? 0 : RelativeLayout.LayoutParams.MATCH_PARENT;
    }


}
