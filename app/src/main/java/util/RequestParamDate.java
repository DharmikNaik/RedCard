package util;

import android.util.Log;

import org.joda.time.DateTime;

public class RequestParamDate {
    public static String getFromDate(){
        DateTime time = new DateTime().minusDays(5);
        String temp = time.toString();
        temp = temp.substring(0,temp.indexOf('T'));
        Log.d("joda time", temp);
        return temp;
    }
    public static String getToDate(){
        DateTime time = new DateTime().plusDays(5);
        String temp = time.toString();
        temp = temp.substring(0,temp.indexOf('T'));
        Log.d("joda time", temp);
        return temp;
    }
}
