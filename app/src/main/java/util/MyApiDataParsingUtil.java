package util;

import android.util.Log;

import com.dharmik.redcard.R;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import model.Match;
import model.Match_;

public class MyApiDataParsingUtil {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private ArrayList<Integer> homeTeamImgIdList;
    private ArrayList<Integer> awayTeamImgIdList;

    public ArrayList<String> getDateList(List<Match_> matches) {
        ArrayList<String> dateList = new ArrayList<>();
        ArrayList<String> x = doTimeZoneThing(matches);
        for (String temp : x) {
            dateList.add(temp.substring(0, temp.indexOf('T')));
        }
        return dateList;
    }

    public ArrayList<String> getTimeList(List<Match_> matches) {
        ArrayList<String> timeList = new ArrayList<>();
        ArrayList<String> x = doTimeZoneThing(matches);
        for (String temp : x) {
            timeList.add(temp.substring(temp.indexOf('T') + 1, temp.indexOf('+')));
        }
        return timeList;
    }

    public ArrayList<Integer> getHomeTeamImgIdList(List<Match_> matches) {
        doImgIdThing(matches);
        return this.homeTeamImgIdList;
    }

    public ArrayList<Integer> getAwayTeamImgIdList(List<Match_> matches) {
        doImgIdThing(matches);
        return this.awayTeamImgIdList;
    }

    public void doImgIdThing(List<Match_> matches) {
        this.homeTeamImgIdList = new ArrayList<>();
        this.awayTeamImgIdList = new ArrayList<>();
        String temp;
        for (Match_ match : matches) {
            temp = match.getHomeTeam().getName();
            if (temp.equals("Chelsea FC"))
                homeTeamImgIdList.add(R.drawable.chelsea);
            else if (temp.equals("Tottenham Hotspur FC"))
                homeTeamImgIdList.add(R.drawable.tot);
            else if (temp.equals("Liverpool FC"))
                homeTeamImgIdList.add(R.drawable.liverpool);
            else if (temp.equals("Cardiff City FC"))
                homeTeamImgIdList.add(R.drawable.cardiff);
            else if (temp.equals("Newcastle United FC"))
                homeTeamImgIdList.add(R.drawable.newcastle);
            else if (temp.equals("Manchester City FC"))
                homeTeamImgIdList.add(R.drawable.manc);
            else if (temp.equals("Manchester United FC"))
                homeTeamImgIdList.add(R.drawable.manu);
            else if (temp.equals("Wolverhampton Wanderers FC"))
                homeTeamImgIdList.add(R.drawable.wolves);
            else if (temp.equals("Leicester City FC"))
                homeTeamImgIdList.add(R.drawable.leicester);
            else if (temp.equals("Everton FC"))
                homeTeamImgIdList.add(R.drawable.everton);
            else if (temp.equals("Watford FC"))
                homeTeamImgIdList.add(R.drawable.watford);
            else if (temp.equals("AFC Bournemouth"))
                homeTeamImgIdList.add(R.drawable.bournemouth);
            else if (temp.equals("Crystal Palace FC"))
                homeTeamImgIdList.add(R.drawable.palace);
            else if (temp.equals("Huddersfield Town AFC"))
                homeTeamImgIdList.add(R.drawable.huddersfield);
            else if (temp.equals("Brighton & Hove Albion FC"))
                homeTeamImgIdList.add(R.drawable.brighton);
            else if (temp.equals("Southampton FC"))
                homeTeamImgIdList.add(R.drawable.southampton);
            else if (temp.equals("West Ham United FC"))
                homeTeamImgIdList.add(R.drawable.westham);
            else if (temp.equals("Fulham FC"))
                homeTeamImgIdList.add(R.drawable.fulham);
            else if (temp.equals("Burnley FC"))
                homeTeamImgIdList.add(R.drawable.burnley);
            else if (temp.equals("Arsenal FC"))
                homeTeamImgIdList.add(R.drawable.arsenal);
            else
                homeTeamImgIdList.add(R.drawable.ic_launcher_background);


            temp = match.getAwayTeam().getName();
            if (temp.equals("Chelsea FC"))
                awayTeamImgIdList.add(R.drawable.chelsea);
            else if (temp.equals("Tottenham Hotspur FC"))
                awayTeamImgIdList.add(R.drawable.tot);
            else if (temp.equals("Liverpool FC"))
                awayTeamImgIdList.add(R.drawable.liverpool);
            else if (temp.equals("Cardiff City FC"))
                awayTeamImgIdList.add(R.drawable.cardiff);
            else if (temp.equals("Newcastle United FC"))
                awayTeamImgIdList.add(R.drawable.newcastle);
            else if (temp.equals("Manchester City FC"))
                awayTeamImgIdList.add(R.drawable.manc);
            else if (temp.equals("Manchester United FC"))
                awayTeamImgIdList.add(R.drawable.manu);
            else if (temp.equals("Wolverhampton Wanderers FC"))
                awayTeamImgIdList.add(R.drawable.wolves);
            else if (temp.equals("Leicester City FC"))
                awayTeamImgIdList.add(R.drawable.leicester);
            else if (temp.equals("Everton FC"))
                awayTeamImgIdList.add(R.drawable.everton);
            else if (temp.equals("Watford FC"))
                awayTeamImgIdList.add(R.drawable.watford);
            else if (temp.equals("AFC Bournemouth"))
                awayTeamImgIdList.add(R.drawable.bournemouth);
            else if (temp.equals("Crystal Palace FC"))
                awayTeamImgIdList.add(R.drawable.palace);
            else if (temp.equals("Huddersfield Town AFC"))
                awayTeamImgIdList.add(R.drawable.huddersfield);
            else if (temp.equals("Brighton & Hove Albion FC"))
                awayTeamImgIdList.add(R.drawable.brighton);
            else if (temp.equals("Southampton FC"))
                awayTeamImgIdList.add(R.drawable.southampton);
            else if (temp.equals("West Ham United FC"))
                awayTeamImgIdList.add(R.drawable.westham);
            else if (temp.equals("Fulham FC"))
                awayTeamImgIdList.add(R.drawable.fulham);
            else if (temp.equals("Burnley FC"))
                awayTeamImgIdList.add(R.drawable.burnley);
            else if (temp.equals("Arsenal FC"))
                awayTeamImgIdList.add(R.drawable.arsenal);
            else
                awayTeamImgIdList.add(R.drawable.ic_launcher_background);
        }
    }

    public ArrayList<String> doTimeZoneThing(List<Match_> matches) {
        ArrayList<String> dateTimeList = new ArrayList<>();
        String temp;
        for (Match_ match : matches) {
            temp = match.getUtcDate();
            temp = temp.replace('T', ' ');
            temp = temp.substring(0, temp.length() - 1);
            LocalDateTime ldt = LocalDateTime.parse(temp, DateTimeFormatter.ofPattern(DATE_FORMAT));
            ZoneId localTimeZone = ZoneId.of("GMT0");
            temp = ldt.atZone(localTimeZone).toString();
            ZoneId targetTimeZone = ZoneId.of("Asia/Calcutta");
            temp = ldt.atZone(localTimeZone).withZoneSameInstant(targetTimeZone).toString();
            dateTimeList.add(temp);
        }
        return dateTimeList;
    }

    public ArrayList<String> getTeam1List(List<Match_> matches, int comp ) {
//        Log.d("competition int ", String.valueOf(comp));
        ArrayList<String> team1List = null;
        if(comp == 0)
            team1List = TeamNameFormatter.forEplTeamList(matches,0);
        else if(comp==1)
            team1List = TeamNameFormatter.forLaligaTeamList(matches, 0);
        return team1List;
    }

    public ArrayList<String> getTeam2List(List<Match_> matches, int comp) {
        ArrayList<String> team2List = null;
        if(comp == 0)
            team2List = TeamNameFormatter.forEplTeamList(matches,1);
        else if(comp==1)
            team2List = TeamNameFormatter.forLaligaTeamList(matches, 1);
        return team2List;
    }

    public ArrayList<String> getScoreList(List<Match_> matches) {
        ArrayList<String> scoreList = new ArrayList<>();
        String homeScore;
        String awayScore;
        for (Match_ match : matches) {
            if (match.getScore().getFullTime().getHomeTeam() != null) {
                homeScore = match.getScore().getFullTime().getHomeTeam().toString();
                awayScore = match.getScore().getFullTime().getAwayTeam().toString();
                scoreList.add(homeScore.substring(0, homeScore.indexOf('.')) + ":" + awayScore.substring(0, awayScore.indexOf('.')));
            } else
                scoreList.add(null);
        }
        return scoreList;
    }
}
