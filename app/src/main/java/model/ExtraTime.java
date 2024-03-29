
package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ExtraTime implements Serializable {

    @SerializedName("homeTeam")
    @Expose
    private Object homeTeam;
    @SerializedName("awayTeam")
    @Expose
    private Object awayTeam;

    public Object getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Object homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Object getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Object awayTeam) {
        this.awayTeam = awayTeam;
    }

}
