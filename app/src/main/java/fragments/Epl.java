package fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dharmik.redcard.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import adapters.MyRecyclerViewAdapter;
import model.Match;
import model.Match_;
import rest.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import util.RequestParamDate;

public class Epl extends Fragment {

    private ViewGroup container;
    private static List<Match_> matches;
    private static int comp;

    public Epl() {
        // Required empty public constructor
    }

    public static void setComp(int x){
        comp=x;
    }

    public static int getComp(){
        return comp;
    }

    public static void setMatches(List<Match_> x){
        matches = x;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.container = container;
//
        View view = inflater.inflate(R.layout.fragment_epl, container, false);
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.rv);
        if (matches != null && matches.size() > 0) {
            MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(Epl.this.getContext(), this.matches, comp);
            LinearLayoutManager manager = new LinearLayoutManager(getContext());
            rv.setLayoutManager(manager);
            rv.setAdapter(adapter);
            rv.setItemAnimator(new DefaultItemAnimator());
            try {
                rv.scrollToPosition(getFocusPosition(matches));
            } catch (ParseException e) {
                Log.d("Parse Exception", "Parse Exception");
                e.printStackTrace();
            }
            adapter.notifyDataSetChanged();
        }

        return view;
    }

    private int getFocusPosition(List<Match_> matches) throws ParseException {
        long minDiff = -1;
        int indexOfMinDiff = -1;
        if (matches != null && matches.size() > 0) {
            for (int i = 0; i < matches.size(); i++) {
                if (matches.get(i).getStatus().equals("IN_PLAY") || matches.get(i).getStatus().equals("PAUSED")) {
                    return i;
                }
            }
            for (int i = 0; i < matches.size(); i++) {
                String dateString = matches.get(i).getUtcDate();
                Log.d("dateString", dateString);
                dateString = dateString.substring(0, dateString.length() - 1);
                dateString = dateString.replace('T', ' ');
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = simpleDateFormat.parse(dateString);
                Date curDate = new Date();
                curDate = simpleDateFormat.parse(simpleDateFormat.format(curDate));
                long diff = Math.abs(date.getTime() - curDate.getTime());
                if (minDiff == -1 || diff < minDiff) {
                    minDiff = diff;
                    indexOfMinDiff = i;
                }
            }
        }
        Log.d("focus on itemView", String.valueOf(indexOfMinDiff));
        return indexOfMinDiff - 1;
    }

}
