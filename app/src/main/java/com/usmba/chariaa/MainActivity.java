package com.usmba.chariaa;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.usmba.chariaa.Fragments.Etudiant.Fragment_Etudiant;
import com.usmba.chariaa.Fragments.Fragment_Error;
import com.usmba.chariaa.Fragments.Fragment_Login;
import com.usmba.chariaa.Fragments.Fragment_ShowArticle;
import com.usmba.chariaa.Fragments.Fragment_ShowMyArticles;
import com.usmba.chariaa.Fragments.Home.Fragment_Home;
import com.usmba.chariaa.Fragments.Question.Fragment_Questions;
import com.usmba.chariaa.Models.Article.Article;
import com.usmba.chariaa.Models.DataMyCollection;
import com.usmba.chariaa.Models.Etudiant.DataEtudiant;
import com.usmba.chariaa.Models.Releve.DataReleve;
import com.usmba.chariaa.Tools.POSTData;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    Menu menu ;
    public Fragment_Home fragment_home;
    public Fragment_Etudiant fragment_etudiant;
    public Fragment_Questions fragment_questions;
    public Fragment_Login fragment_login;
    public Fragment_Error fragment_error;
    public Fragment_ShowArticle fragment_showArticle;
    public Fragment_ShowMyArticles fragment_showMyArticles;

    public DataEtudiant dataEtudiant;
    public DataReleve dataReleve;
    public DataMyCollection myCollection;
    public Article article;


    /** binding views **/
    @BindView(R.id.parent_content)
    RelativeLayout parent_content;
    @BindView(R.id.main_content)
    RelativeLayout main_content;
    @BindView(R.id.nav_view)
    NavigationView nav_view;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.bottomBavigationView)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        LoadSession();
        setupNavigationComponents();
        setupFragments();
        NavigateToMain();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_setting, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_search)
        {
            LunchSearchFragment();
        }
        return true;
    }
    @Override
    public SharedPreferences getSharedPreferences(String name, int mode) {
        return super.getSharedPreferences(name, mode);
    }

    void setupFragments() {
        fragment_home = new Fragment_Home();
        fragment_etudiant = new Fragment_Etudiant();
        fragment_questions = new Fragment_Questions();
        fragment_login = new Fragment_Login();
        fragment_error = new Fragment_Error();

        fragment_home.setMainActivity(this);
        fragment_etudiant.setMainActivity(this);
        fragment_questions.setMainActivity(this);
        fragment_login.setMainActivity(this);
        fragment_error.setMainActivity(this);
    }
    void setupNavigationComponents(){
        //setupToolbar
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(toolbar);


        //setupbottomNavigationView
        new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                getSupportActionBar().setTitle(menuItem.getTitle());
                switch (menuItem.getItemId()){
                    case R.id.navigation_home : NavigateToMain(); break;
                    case R.id.navigation_Etudiant : NavigateToEtudiant();break;
                    case R.id.navigation_Chats : NavigateToChats();break;
                }
                return true;
            }
        });

        //setupNavigationMenu
        menu = nav_view.getMenu();
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(final MenuItem item) {
                switch (item.getItemId()){
                    case 0: DisplayMyArticles();break;
                    case 1: DisplayMyBooks();break;
                    case 2: DisplaySavedQuestions();break;
                    case 3: DisplayAbout();break;
                    case 4: Logout();break;
                }
                drawer.closeDrawers();
                return true;
            }
        });
        menu.add(R.id.grp_about,3,4,R.string.about);
    }

    public void CallLoginForm(){
        HideContent(true);
        getSupportFragmentManager().beginTransaction().replace(android.R.id.content,fragment_login).commit();
    }
    public void CallError(int fragment){

        getSupportFragmentManager().beginTransaction().replace(fragment, fragment_error).commit();
    }

    public void LoadMyCollection(){
        menu.removeItem(0);
        menu.removeItem(1);
        menu.removeItem(2);
        menu.removeItem(4);

        menu.add(R.id.grp_mycollection,0,1,getString(R.string.ads) + "(" + myCollection.myArticles.size() + ")");
        menu.add(R.id.grp_mycollection,1,2,getString(R.string.books) + "(" + myCollection.myBooks.size() + ")");
        menu.add(R.id.grp_mycollection,2,3,getString(R.string.questions) + "(" + myCollection.myQuestions.size() + ")");
        menu.add(R.id.grp_logout,4,5,R.string.logout);

    }

    public void NavigateToMain(){getSupportFragmentManager().beginTransaction().replace(R.id.main_content,fragment_home).commit();}
    public void NavigateToEtudiant(){getSupportFragmentManager().beginTransaction().replace(R.id.main_content,fragment_etudiant).commit();}
    public void NavigateToChats(){getSupportFragmentManager().beginTransaction().replace(R.id.main_content,fragment_questions).commit();}


    public void DisplayMyArticles(){
        fragment_showMyArticles = new Fragment_ShowMyArticles();
        fragment_showMyArticles.setMainActivity(MainActivity.this);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.add(android.R.id.content, fragment_showMyArticles).addToBackStack(null).commit();
    }
    public void DisplayMyBooks(){Toast.makeText(getApplicationContext(), getString(R.string.books), Toast.LENGTH_SHORT).show();}
    public void DisplaySavedQuestions(){Toast.makeText(getApplicationContext(), getString(R.string.questions), Toast.LENGTH_SHORT).show();}
    public void DisplayAbout(){ Toast.makeText(getApplicationContext(), "About", Toast.LENGTH_SHORT).show();}
    public void Logout(){
        getSharedPreferences("session", 0).edit().clear().apply();
        dataEtudiant = null;
        dataReleve = null;
        myCollection = null;

        bottomNavigationView.setSelectedItemId(R.id.navigation_home);
        menu.removeGroup(R.id.grp_logout);
        menu.removeGroup(R.id.grp_mycollection);
        NavigateToMain();
    }

    public void HideContent(boolean hide){
        parent_content.getLayoutParams().height = hide ? 0 : RelativeLayout.LayoutParams.MATCH_PARENT;
    }

    public void LunchSearchFragment(){Toast.makeText(getApplicationContext(), "Search", Toast.LENGTH_SHORT).show();}

    public void DisplayArticle(Article article){
        fragment_showArticle = new Fragment_ShowArticle();
        fragment_showArticle.setMainActivity(MainActivity.this);
        this.article = article;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.add(android.R.id.content, fragment_showArticle).addToBackStack(null).commit();
    }

    public void Login(final String nins, final String DN, final boolean login){
        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String url_Dataetudiant = getString(R.string.main_url) + getString(R.string.data_Etudiant);
                            String url_DataReleve = getString(R.string.main_url) + getString(R.string.data_Releve);
                            String url_DataMyCollection = getString(R.string.main_url) + getString(R.string.data_MyCollection);
                            JSONObject parameters = new JSONObject();
                            parameters.put("nins",nins);
                            parameters.put("DN", DN);
                            dataEtudiant = new DataEtudiant(POSTData.getJSONString(url_Dataetudiant, parameters));
                            if(!dataEtudiant.isError()){
                                dataReleve = new DataReleve(POSTData.getJSONString(url_DataReleve, parameters));
                                myCollection = new DataMyCollection(POSTData.getJSONString(url_DataMyCollection, parameters));
                                getSharedPreferences("session",0).edit().putString("nins",nins).apply();
                                getSharedPreferences("session",0).edit().putString("DN",DN).apply();
                                LoadMyCollection();

                                if(login)
                                {
                                    HideContent(false);
                                    getSupportFragmentManager().beginTransaction().remove(fragment_login).commit();
                                    fragment_etudiant.initComponents();
                                }
                            }
                            else{
                                Toast.makeText(getApplicationContext(),dataEtudiant.getError_msg(),Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                        } catch (IOException e) {
                            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        }).start();

    }

    void LoadSession() {
        String nins =  getSharedPreferences("session",0).getString("nins","");
        String DN =  getSharedPreferences("session",0).getString("DN","");
        if(!nins.equals("") && !DN.equals(""))
        {
            Login(nins, DN, false);
        }
    }

    public void SaveArticle(){
        myCollection.myArticles.add(Integer.parseInt(article.idArticle));
        syncMyCollection(true);

    }
    public void DeleteArticle(){
        int index = myCollection.myArticles.indexOf(Integer.parseInt(article.idArticle));
        myCollection.myArticles.remove(index);
        syncMyCollection(false);
    }

    void syncMyCollection(final boolean save){
        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String articles = "";
                            String books = "";
                            String questions = "";
                            for (Integer I: myCollection.myArticles) { articles += "," + I;}
                            for (Integer I: myCollection.myBooks) { books += "," + I;}
                            for (Integer I: myCollection.myQuestions) { questions += "," + I; }

                            articles = articles.length() > 0 ? articles.substring(1): "";
                            books = books.length() > 0 ? books.substring(1): "";
                            questions =  questions.length() > 0 ? questions.substring(1): "";

                            String url_DataMyCollection = getString(R.string.main_url) + getString(R.string.data_MyCollection);
                            JSONObject parameters = new JSONObject();
                            parameters.put("nins",dataEtudiant.getEtudiant().getNins());
                            parameters.put("articles", articles);
                            parameters.put("books", books);
                            parameters.put("questions", questions);
                            myCollection = new DataMyCollection(POSTData.getJSONString(url_DataMyCollection, parameters));
                            if(save){
                                fragment_showArticle.showsyncmsg("Article Saved");
                            }
                            else {
                                fragment_showArticle.showsyncmsg("Article Discarded");
                            }
                            LoadMyCollection();
                        }
                        catch (JSONException e) {
                            if(save){
                                fragment_showArticle.showsyncmsg("Save failed : " + e.getMessage());
                            }
                            else {
                                fragment_showArticle.showsyncmsg("Discard failed : " + e.getMessage());
                            }
                        }
                        catch (IOException e) {
                            if(save){
                                fragment_showArticle.showsyncmsg("Save failed : " + e.getMessage());
                            }
                            else {
                                fragment_showArticle.showsyncmsg("Discard failed : " + e.getMessage());
                            }
                        }
                    }
                });
            }
        }).start();
    }

}
