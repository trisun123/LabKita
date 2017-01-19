package medis.ourlab.labkita.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import medis.ourlab.labkita.app.MyApplication;

/**
 * Created by tri on 12/11/2016.
 */

public class dummyBuatItung2anWaktu extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String waktu="03-20-2017 14:00";
        Long hari= Long.valueOf(0);
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm");

        Calendar c_palsu = Calendar.getInstance();
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(waktu));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //String jam2 = Long.toString((c.getTimeInMillis() - System.currentTimeMillis()) / (1000 * 60 * 60));
        String jam2 = Long.toString((c.getTimeInMillis() - System.currentTimeMillis()) / 1000 / 60 / 60 % 60);
        //String hari2 = Long.toString((c.getTimeInMillis() - System.currentTimeMillis()) / (1000 * 60 * 60 * 24));
        String hari2 = Long.toString((c.getTimeInMillis() - System.currentTimeMillis()) / 1000 / 60 / 60 / 24 );
        String menit = Long.toString((c.getTimeInMillis() - System.currentTimeMillis()) / 1000 / 60 % 60);
        if(Integer.valueOf(jam2)>=24){
            jam2=String.valueOf(Integer.valueOf(jam2)%24);
        }
        String hasil="";
        if(Integer.valueOf(hari2)>0){
            if(Integer.valueOf(hari2)>1){
                hasil=hari2 + " days";
            }else{
                hasil=hari2 + " day";
            }
        }
        if(Integer.valueOf(jam2)>0){
            hasil = hasil + " " + jam2 + " hours";
        }
        hasil = hasil + " " + menit + " minutes";



    }
}
