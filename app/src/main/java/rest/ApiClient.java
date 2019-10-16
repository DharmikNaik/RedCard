package rest;

import model.Match;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public class ApiClient {
    public static final String BASE_URL = "http://api.football-data.org/v2/";
    public static final String KEY1 = "fe8b2b1b7ca4446da4fc19e1ce5fc478";
    public static final String KEY2 = "058ee2780038432ba9dc3187ffd3afc0";
    public static final String KEY3 = "b562ab6ebf79442490b001f187df02b6";
    public static final String KEY4 = "07219593a8084f8b9c5127f5243a87c1";
    public static MatchService matchService = null;

    public static MatchService getRetro(){
        if(matchService == null){
            Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
            matchService = retrofit.create(MatchService.class);
        }
        return matchService;
    }

    public interface MatchService{
        //epl
        @Headers({"X-Auth-Token: "+KEY1})
        @GET("matches?competitions=2021")
        Call<Match> getEplMatches(@Query("dateFrom") String fromDate, @Query("dateTo") String toDate);

        //ucl
        @Headers({"X-Auth-Token: "+KEY2})
        @GET("matches?competitions=2001")
        Call<Match> getUclMatches(@Query("dateFrom") String fromDate, @Query("dateTo") String toDate);

        //uel
        @Headers({"X-Auth-Token: "+KEY2})
        @GET("matches?competitions=2146")
        Call<Match> getUelMatches(@Query("dateFrom") String fromDate, @Query("dateTo") String toDate);

        //laliga
        @Headers({"X-Auth-Token: "+KEY3})
        @GET("matches?competitions=2014")
        Call<Match> getLaligaMatches(@Query("dateFrom") String fromDate, @Query("dateTo") String toDate);

        //Serie A
        @Headers({"X-Auth-Token: "+KEY4})
        @GET("matches?competitions=2019")
        Call<Match> getSerieAMatches(@Query("dateFrom") String fromDate, @Query("dateTo") String toDate);

        //Bundesliga
        @Headers({"X-Auth-Token: "+KEY4})
        @GET("matches?competitions=2002")
        Call<Match> getBundesligaMatches(@Query("dateFrom") String fromDate, @Query("dateTo") String toDate);

        //ligue 1
        @Headers({"X-Auth-Token: "+KEY3})
        @GET("matches?competitions=2015")
        Call<Match> getLigue1Matches(@Query("dateFrom") String fromDate, @Query("dateTo") String toDate);

    }

}
