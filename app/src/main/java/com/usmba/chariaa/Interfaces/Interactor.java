package com.usmba.chariaa.Interfaces;


import com.usmba.chariaa.MainActivity;

public interface Interactor {
    void setMainActivity(MainActivity mainActivity);//to gain access to MainActivity instence
    void HideContent(boolean hide);//to hide the main content
    void initComponents();//Initialization Components
}
