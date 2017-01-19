package medis.ourlab.labkita.app;

/**
 * Created by Lincoln on 14/10/15.
 */

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import medis.ourlab.labkita.activity.LoginActivity;
import medis.ourlab.labkita.helper.MyPreferenceManager;
import medis.ourlab.labkita.model.Pasien;
import medis.ourlab.labkita.model.PasienList;
import medis.ourlab.labkita.volley.LruBitmapCache;

import android.support.multidex.MultiDex;
import android.util.DisplayMetrics;

/**
 * Created by Ravi on 13/05/15.
 */

public class MyApplication extends Application {

    public static final String TAG = MyApplication.class
            .getSimpleName();

    private ImageLoader mImageLoader;
    LruBitmapCache mLruBitmapCache;
    private RequestQueue mRequestQueue;

    ArrayList<PasienList> pasienlist=new ArrayList<PasienList>();

    private static MyApplication mInstance;

    private MyPreferenceManager pref;

    public String invoice="";
    public Double lat, lon;
    public Float akurasi;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        Picasso.Builder builder = new Picasso.Builder(this);
        builder.downloader(new OkHttpDownloader(this,Integer.MAX_VALUE));
        Picasso built = builder.build();
        //built.setIndicatorsEnabled(false); //klo true bakal ada segitiga kecil di picasso
        built.setLoggingEnabled(true);
        Picasso.setSingletonInstance(built);
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }



    public MyPreferenceManager getPrefManager() {
        if (pref == null) {
            pref = new MyPreferenceManager(this);
        }

        return pref;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    public void logout() {
        pref.clear();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            getLruBitmapCache();
            mImageLoader = new ImageLoader(this.mRequestQueue, mLruBitmapCache);
        }

        return this.mImageLoader;
    }

    public LruBitmapCache getLruBitmapCache() {
        if (mLruBitmapCache == null)
            mLruBitmapCache = new LruBitmapCache();
        return this.mLruBitmapCache;
    }

    public Integer getPasienListCount(){
        return this.pasienlist.size();
    }

    public ArrayList<PasienList> getPasienFromList(){

        return pasienlist;
    }

    public void addPasienToList(PasienList a){
        /*this.users.put("name",a.get("name").toString());
        this.users.put("username",a.get("username").toString());
        this.users.put("sheet",a.get("sheet").toString());*/
        //this.users.putAll(a);
        this.pasienlist.add(a);

    }

    public void clearPasiensFromList(){
        try {
            this.pasienlist.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Pasien> getPasienArrayByIdOrder(Integer id_order){
        for(Integer i=0;i<pasienlist.size();i++){
            if(pasienlist.get(i).getIdOrder()==id_order){
                return pasienlist.get(i).getPasien();
            }
        }
        return null;
    }

    public void setAllPasienListFalseForAdapter(){
        for(Integer i=0;i<pasienlist.size();i++) {
            pasienlist.get(i).setProcessed_by_adapter_header(false);
            pasienlist.get(i).setProcessed_by_adapter_child(false);
        }
    }

    public void setAllPasienListFalseForPickOrder(){
        for(Integer i=0;i<pasienlist.size();i++) {
            pasienlist.get(i).setMasih_order(false);
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this); //tambahkan ini
    }

    public void setInvoice(String inv){
        this.invoice=inv;
    }

    public String getInvoice(){
        return this.invoice;
    }



    public static String remainingWaktu(String waktu){
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(waktu));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String jam2 = Long.toString((c.getTimeInMillis() - System.currentTimeMillis()) / 1000 / 60 / 60 % 60);
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
        return hasil;
    }

    public void setLon(Double lon){
        this.lon=lon;
    }
    public Double getLon(){
        return this.lon;
    }
    public void setLat(Double lat){
        this.lat=lat;
    }
    public Double getLat(){
        return this.lat;
    }
    public void setAkurasi(Float akurasi){
        this.akurasi=akurasi;
    }
    public Float getAkurasi(){
        return this.akurasi;
    }

    public static Boolean punyaMinus(String inputx){
        if(inputx.contains("-")){
            return true;
        }else{
            return false;
        }
    }

    public void getScreenSizePixels(int widthHeightInPixels[/*2*/])
    {
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        DisplayMetrics dm = resources.getDisplayMetrics();
        // Note, screenHeightDp isn't reliable
        // (it seems to be too small by the height of the status bar),
        // but we assume screenWidthDp is reliable.
        // Note also, dm.widthPixels,dm.heightPixels aren't reliably pixels
        // (they get confused when in screen compatibility mode, it seems),
        // but we assume their ratio is correct.
        double screenWidthInPixels = (double)config.screenWidthDp * dm.density;

        double screenHeightInPixels = screenWidthInPixels * dm.heightPixels / dm.widthPixels;
        widthHeightInPixels[0] = (int)(screenWidthInPixels + .5);
        widthHeightInPixels[1] = (int)(screenHeightInPixels + .5);
    }

    public float getScreenDensity()
    {
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        DisplayMetrics dm = resources.getDisplayMetrics();
        // Note, screenHeightDp isn't reliable
        // (it seems to be too small by the height of the status bar),
        // but we assume screenWidthDp is reliable.
        // Note also, dm.widthPixels,dm.heightPixels aren't reliably pixels
        // (they get confused when in screen compatibility mode, it seems),
        // but we assume their ratio is correct.

        return dm.density;

    }

}