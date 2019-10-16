package adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dharmik.redcard.R;


import java.util.ArrayList;
import java.util.List;

import model.Match_;
import util.MyApiDataParsingUtil;
import util.RequestParamDate;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {
    private int comp;
    Context activity;
    List<Match_> matches;
    ArrayList<String> alDate;
    ArrayList<String> alTime;
    ArrayList<Integer> alImg1;
    ArrayList<Integer> alImg2;
    ArrayList<String> alTeam1;
    ArrayList<String> alTeam2;
    ArrayList<String> alScore;
    ArrayList<String> alMinutesPlayed;
    MyApiDataParsingUtil util;

    public MyRecyclerViewAdapter(Context activity, List<Match_> matches, int comp) {
        this.activity = activity;
        this.matches = matches;
        this.comp = comp;
        Log.d("Init", "Started");
        util = new MyApiDataParsingUtil();
        alDate = util.getDateList(matches);
        alTime = util.getTimeList(matches);
        alImg1 = util.getHomeTeamImgIdList(matches);
        alImg2 = util.getAwayTeamImgIdList(matches);
        alTeam1 = util.getTeam1List(matches, comp);
        alTeam2 = util.getTeam2List(matches, comp);
        alScore = util.getScoreList(matches);
//        alMinutesPlayed =
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View myView = null;
        if (i==0)
            myView = LayoutInflater.from(activity).inflate(R .layout.recycler_view_completed_match_template, viewGroup, false);
        else if(i==1)
            myView = LayoutInflater.from(activity).inflate(R.layout.recycler_view_live_match_template, viewGroup, false);
        else if(i==2)
            myView = LayoutInflater.from(activity).inflate(R.layout.recycler_view_template, viewGroup, false);
        else
            myView = LayoutInflater.from(activity).inflate(R.layout.recycler_view_went_wrong, viewGroup, false);
        return new MyViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.iv_team_1.setImageResource(alImg1.get(i));
        myViewHolder.iv_team_2.setImageResource(alImg2.get(i));
        myViewHolder.tv_team1.setText(alTeam1.get(i));
        myViewHolder.tv_team2.setText(alTeam2.get(i));
        if (this.matches.get(i).getStatus().equals("SCHEDULED")) {
            myViewHolder.txt_1.setText(alDate.get(i));
            myViewHolder.txt_2.setText(alTime.get(i));
        } else if (this.matches.get(i).getStatus().equals("IN_PLAY") || this.matches.get(i).getStatus().equals("PAUSED") || this.matches.get(i).getStatus().equals("FINISHED")){
            myViewHolder.txt_1.setText(alScore.get(i));
            if (this.matches.get(i).getStatus().equals("IN_PLAY") || this.matches.get(i).getStatus().equals("PAUSED")){
//                myViewHolder.txt_2.setText(minutesplayed);
            }
            else {
                myViewHolder.txt_2.setText(alDate.get(i));
            }
        }
    }

    @Override
    public int getItemCount() {
        return alDate.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView txt_1, txt_2,tv_team1,tv_team2;
        public ImageView iv_team_1, iv_team_2;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_1 = (TextView) itemView.findViewById(R.id.txt_1);
            txt_2 = (TextView) itemView.findViewById(R.id.txt_2);
            iv_team_1 = (ImageView) itemView.findViewById(R.id.iv_team_1);
            iv_team_2 = (ImageView) itemView.findViewById(R.id.iv_team_2);
            tv_team1 = (TextView) itemView.findViewById(R.id.tv_team1);
            tv_team2 = (TextView) itemView.findViewById(R.id.tv_team2);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (this.matches.get(position).getStatus().equals("FINISHED")) {
            return 0;
        }
        else if(this.matches.get(position).getStatus().equals("IN_PLAY") || this.matches.get(position).getStatus().equals("PAUSED")){
            return 1;
        }
        else if(this.matches.get(position).getStatus().equals("SCHEDULED")){
            return 2;
        }else
            return -1;
    }
}
