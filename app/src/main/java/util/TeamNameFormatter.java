package util;

import java.util.ArrayList;
import java.util.List;

import model.Match_;

public class TeamNameFormatter {
    public static ArrayList<String> forEplTeamList(List<Match_> matches, int homeOrAway) {
        String temp;
        ArrayList<String> team1List = new ArrayList<>();
        for (Match_ match : matches) {
            if (homeOrAway==0)
                temp = match.getHomeTeam().getName();
            else
                temp = match.getAwayTeam().getName();
            if (temp.split(" ").length == 2 && !(temp.equals("AFC Bournemouth")))
                temp = temp.substring(0, temp.indexOf(' '));
            else if (temp.equals("Tottenham Hotspur FC"))
                temp = "Tottenham";
            else if (temp.equals("Cardiff City FC"))
                temp = "Cardiff";
            else if (temp.equals("Newcastle United FC"))
                temp = "Newcastle";
            else if (temp.equals("Manchester City FC"))
                temp = "Man City";
            else if (temp.equals("Manchester United FC"))
                temp = "Man United";
            else if (temp.equals("Wolverhampton Wanderers FC"))
                temp = "Wolves";
            else if (temp.equals("Leicester City FC"))
                temp = "Leicester";
            else if (temp.equals("AFC Bournemouth"))
                temp = "Bournemouth";
            else if (temp.equals("Crystal Palace FC"))
                temp = "Crystal Palace";
            else if (temp.equals("Huddersfield Town AFC"))
                temp = "Huddersfield";
            else if (temp.equals("Brighton & Hove Albion FC"))
                temp = "Brighton";
            else if (temp.equals("West Ham United FC"))
                temp = "West Ham";
            else
                temp = "Not Available";
            team1List.add(temp);
        }
        return  team1List;
    }

    public static ArrayList<String> forLaligaTeamList(List<Match_> matches, int homeOrAway) {
        String temp;
        ArrayList<String> team1List = new ArrayList<>();
        for (Match_ match : matches) {
            if (homeOrAway==0)
                temp = match.getHomeTeam().getName();
            else
                temp = match.getAwayTeam().getName();
            if (temp.equals("Rayo Vallecano de Madrid"))
                temp = "Rayo Vallecano";
            else if (temp.equals("Real Betis Balompié"))
                temp = "Real Betis";
            else if (temp.equals("RCD Espanyol de Barcelona"))
                temp = "Espanyol";
            else {
                if (temp.contains("RCD"))
                    temp = temp.replace("RCD", "").trim();
                if (temp.contains("SD"))
                    temp = temp.replace("SD", "").trim();
                if (temp.contains("FC"))
                    temp = temp.replace("FC", "").trim();
                if (temp.contains("CF"))
                    temp = temp.replace("CF", "").trim();
                if (temp.contains("de"))
                    temp = temp.replace("de", "").trim();
                if (temp.contains("UD"))
                    temp = temp.replace("UD", "").trim();
                if (temp.contains("CD"))
                    temp = temp.replace("CD", "").trim();
                if (temp.contains("Club"))
                    temp = temp.replace("Club", "").trim();
                if (temp.contains("RC"))
                    temp = temp.replace("RC", "").trim();
                if (temp.contains("Fútbol"))
                    temp = temp.replace("Fútbol", "").trim();
                if (temp.contains("Fútbol"))
                    temp = temp.replace("Fútbol", "").trim();
            }
            team1List.add(temp);
        }
        return  team1List;
    }

}
