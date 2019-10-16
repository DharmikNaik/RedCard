package com.dharmik.redcard;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.Serializable;
import java.util.List;

import model.Match;
import model.Match_;
import rest.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import util.RequestParamDate;

public class SplashScreenActivity extends AppCompatActivity {


    List<Match_> matches;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Call<Match> matchCall = ApiClient.getRetro().getEplMatches(RequestParamDate.getFromDate(), RequestParamDate.getToDate());
        matchCall.enqueue(new Callback<Match>() {
            @Override
            public void onResponse(Call<Match> call, Response<Match> response) {
                matches = response.body().getMatches();
            }

            @Override
            public void onFailure(Call<Match> call, Throwable t) {

            }
        });
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                doTheIntentThing();
            }
        }, 2000);
    }

    private void doTheIntentThing() {
        Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
        i.putExtra("matches", (Serializable)this.matches);
        startActivity(i);
    }
}
