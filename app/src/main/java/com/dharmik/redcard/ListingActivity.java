//package com.dharmik.redcard;
//
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.support.v7.widget.DefaultItemAnimator;
//import android.support.v7.widget.DividerItemDecoration;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.widget.Toast;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
//import adapters.MyRecyclerViewAdapter;
//import model.Match;
//import model.Match_;
//import rest.ApiClient;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import util.RequestParamDate;
//
//public class ListingActivity extends AppCompatActivity {
//    List<Match_> matches;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_listing);
//        Call<Match> matchCall = ApiClient.getRetro().getEplMatches(RequestParamDate.getFromDate(), RequestParamDate.getToDate());
//        matchCall.enqueue(new Callback<Match>() {
//            @Override
//            public void onResponse(Call<Match> call, Response<Match> response) {
//                setupRecyclerView(response.body().getMatches());
//                List<Match_> matches = response.body().getMatches();
////                Log.d("qwerty",matches.get(1).getHomeTeam().getName()+" vs "+matches.get(1).getAwayTeam().getName());
////                Toast.makeText(getApplicationContext(), matches.get(1).getHomeTeam().getName()+" vs "+matches.get(1).getAwayTeam().getName(), Toast.LENGTH_LONG).show();
//            }
//            @Override
//            public void onFailure(Call<Match> call, Throwable t) {
//                Toast.makeText(getApplicationContext(),"Failure", Toast.LENGTH_LONG).show();
//            }
//        });
//
//    }
//
//    private void setupRecyclerView(List<Match_> matches) {
//        this.matches = matches;
//        RecyclerView rv = (RecyclerView)findViewById(R.id.RecyclerView);
//        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(this,matches);
//        LinearLayoutManager manager = new LinearLayoutManager(this);
//        rv.setLayoutManager(manager);
//        rv.setAdapter(adapter);
//        rv.setItemAnimator(new DefaultItemAnimator());
//        try {
//            rv.scrollToPosition(getFocusPosition());
//        } catch (ParseException e) {
//            Log.d("Parse Exception","Parse Exception");
//            e.printStackTrace();
//        }
//        adapter.notifyDataSetChanged();
//    }
//
//    private int getFocusPosition() throws ParseException {
//        long minDiff=-1;
//        int indexOfMinDiff=-1;
//        if (matches!=null && matches.size()>0){
//            for (int i=0; i<matches.size();i++){
//                if (matches.get(i).getStatus().equals("IN_PLAY") || matches.get(i).getStatus().equals("PAUSED")){
//                    return i;
//                }
//            }
//            for (int i=0; i<matches.size();i++){
//                String dateString = matches.get(i).getUtcDate();
//                Log.d("dateString", dateString);
//                dateString = dateString.substring(0,dateString.length()-1);
//                dateString = dateString.replace('T',' ');
//                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                Date date = simpleDateFormat.parse(dateString);
//                Date curDate = new Date();
//                curDate = simpleDateFormat.parse(simpleDateFormat.format(curDate));
//                long diff = Math.abs(date.getTime() - curDate.getTime());
//                if(minDiff==-1 || diff<minDiff) {
//                    minDiff = diff;
//                    indexOfMinDiff = i;
//                }
//            }
//        }
//        Log.d("focus on itemView", String.valueOf(indexOfMinDiff));
//        return indexOfMinDiff-1;
//    }
//
//}
