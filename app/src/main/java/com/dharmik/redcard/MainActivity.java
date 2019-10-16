package com.dharmik.redcard;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import java.io.Serializable;
import java.util.List;

import fragments.Epl;
import model.Match;
import model.Match_;
import rest.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import util.RequestParamDate;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    android.support.v7.app.ActionBar actionBar;


    Epl eplFragment;
    List<Match_> matchList;
    int currComp = 0;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Premier League");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        progressBar = (ProgressBar) findViewById(R.id.pb);
        this.matchList = (List<Match_>) getIntent().getExtras().getSerializable("matches");
        Epl.setMatches(this.matchList);
        Epl.setComp(0);
        this.eplFragment = new Epl();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, eplFragment).commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_epl) {
            if (currComp != 0) {
                actionBar.setTitle("Premier League");
                progressBar.setVisibility(View.VISIBLE);
                Call<Match> matchCall = ApiClient.getRetro().getEplMatches(RequestParamDate.getFromDate(), RequestParamDate.getToDate());
                matchCall.enqueue(new Callback<Match>() {
                    @Override
                    public void onResponse(Call<Match> call, Response<Match> response) {
                        progressBar.setVisibility(View.GONE);
                        Epl.setMatches(response.body().getMatches());
                        currComp = 0;
                        Epl.setComp(currComp);
                        eplFragment = new Epl();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, eplFragment).commit();
                    }

                    @Override
                    public void onFailure(Call<Match> call, Throwable t) {
                    }
                });
            }
        } else if (id == R.id.nav_laliga) {
            if (currComp != 1) {
                actionBar.setTitle("La Liga");
                progressBar.setVisibility(View.VISIBLE);
                Call<Match> matchCall = ApiClient.getRetro().getLaligaMatches(RequestParamDate.getFromDate(), RequestParamDate.getToDate());
                matchCall.enqueue(new Callback<Match>() {
                    @Override
                    public void onResponse(Call<Match> call, Response<Match> response) {
                        progressBar.setVisibility(View.GONE);
                        Epl.setMatches(response.body().getMatches());
                        currComp = 1;
                        Epl.setComp(currComp);
                        eplFragment = new Epl();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, eplFragment).commit();
                    }

                    @Override
                    public void onFailure(Call<Match> call, Throwable t) {
                    }
                });
            }
        }
//        else if (id == R.id.nav_bundesliga) {
//            if (!(currentFragment instanceof Bundesliga)) {
//                actionBar.setTitle("Bundesliga");
//                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, bundesligaFragment).commit();
//                currentFragment = bundesligaFragment;
//            }
//        } else if (id == R.id.nav_serie_a) {
//            if (!(currentFragment instanceof SerieA)) {
//                actionBar.setTitle("Serie A");
//                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, serieAFragment).commit();
//                currentFragment = serieAFragment;
//            }
//        } else if (id == R.id.nav_ligue_1) {
//            if (!(currentFragment instanceof Ligue1)) {
//                actionBar.setTitle("Ligue 1");
//                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, ligue1Fragment).commit();
//                currentFragment = ligue1Fragment;
//            }
//        } else if (id == R.id.nav_ucl) {
//            if (!(currentFragment instanceof Ucl)) {
//                actionBar.setTitle("Champions League");
//                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, uclFragment).commit();
//                currentFragment = uclFragment;
//            }
//        } else if (id == R.id.nav_uel) {
//            if (!(currentFragment instanceof Uel)) {
//                actionBar.setTitle("Europa League");
//                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, uelFragment).commit();
//                currentFragment = uelFragment;
//            }
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
