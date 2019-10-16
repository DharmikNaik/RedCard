
package model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Filters implements Serializable {

    @SerializedName("dateFrom")
    @Expose
    private String dateFrom;
    @SerializedName("dateTo")
    @Expose
    private String dateTo;
    @SerializedName("permission")
    @Expose
    private String permission;
    @SerializedName("competitions")
    @Expose
    private List<Integer> competitions = null;

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public List<Integer> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(List<Integer> competitions) {
        this.competitions = competitions;
    }

}
