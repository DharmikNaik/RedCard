package com.dharmik.redcard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import adapters.MyRecyclerViewAdapter;
import model.Match;
import model.Match_;
import rest.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import util.RequestParamDate;

public class TestCall extends AppCompatActivity {

    TextView tv;
    List<Match_> matches;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_call);
        tv = (TextView)findViewById(R.id.tv);
        getData();
    }

    public void getData(){
        Call<Match> matchCall = ApiClient.getRetro().getEplMatches(RequestParamDate.getFromDate(), RequestParamDate.getToDate());

        matchCall.enqueue(new Callback<Match>() {
            @Override
            public void onResponse(Call<Match> call, Response<Match> response) {
               TestCall.this.matches = response.body().getMatches();
               tv.setText(response.body().getMatches().get(0).getAwayTeam().getName());
                Toast.makeText(getApplicationContext(), TestCall.this.matches.get(1).getHomeTeam().getName()+" vs "+TestCall.this.matches.get(1).getAwayTeam().getName(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Match> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Failure", Toast.LENGTH_LONG).show();
            }
        });
        Log.d("a","as");
    }
}
