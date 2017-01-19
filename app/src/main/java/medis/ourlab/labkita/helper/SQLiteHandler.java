package medis.ourlab.labkita.helper;

/**
 * Created by tri on 10/3/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import medis.ourlab.labkita.app.MyApplication;
import medis.ourlab.labkita.model.Pasien;
import medis.ourlab.labkita.model.PasienList;
import medis.ourlab.labkita.model.Tests_lab;

public class SQLiteHandler extends SQLiteOpenHelper {

    private static final String TAG = SQLiteHandler.class.getSimpleName();

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 25;

    // Database Name
    private static final String DATABASE_NAME = "labkita";

    // Login table name
    private static final String TABLE_USER = "pasien";

    //table names
    private static final String TABLE_PASIENLIST = "pasienlist";
    private static final String TABLE_PASIEN = "pasien";
    private static final String TABLE_TESTLAB = "testlab";


    //common column names
    private static final String KEY_ID = "id";

    // pasienlit Columns names
    private static final String KEY_PASIENLIST_ID_INVOICE = "id_invoice";
    private static final String KEY_PASIENLIST_ID_MEMBER = "pasienlist_id_member";
    private static final String KEY_PASIENLIST_ID_LAB = "pasienlist_id_lab";
    private static final String KEY_PASIENLIST_NAMA_LAB = "pasienlist_nama_lab";
    private static final String KEY_PASIENLIST_NAMA_MEMBER = "pasienlist_nama_member";
    private static final String KEY_PASIENLIST_ALAMAT_LAB = "pasienlist_alamat_lab";
    private static final String KEY_PASIENLIST_ALAMAT_PASIEN = "pasienlist_alamat_pasien";
    private static final String KEY_PASIENLIST_INVOICE = "pasienlist_invoice";
    private static final String KEY_PASIENLIST_LONGITUDE_PASIEN = "pasienlist_longitude_pasien";
    private static final String KEY_PASIENLIST_LATITUDE_PASIEN = "pasienlist_latitude_pasien";
    private static final String KEY_PASIENLIST_LONGITUDE_LAB = "pasienlist_longitude_lab";
    private static final String KEY_PASIENLIST_LATITUDE_LAB = "pasienlist_latitude_lab";
    private static final String KEY_PASIENLIST_TOTAL_HARGA = "pasienlist_total_harga";
    private static final String KEY_PASIENLIST_JENIS = "pasienlist_masih_order"; //0=false 1=true
    private static final String KEY_PASIENLIST_WAKTU = "pasienlist_waktu";
    private static final String CREATE_TABLE_PASIENLIST = "CREATE TABLE "
            + TABLE_PASIENLIST + "(" + KEY_PASIENLIST_INVOICE + " TEXT," +
            KEY_PASIENLIST_ID_MEMBER + " INTEGER," + KEY_PASIENLIST_ID_LAB + " INTEGER," +
            KEY_PASIENLIST_NAMA_LAB + " TEXT," +
            KEY_PASIENLIST_NAMA_MEMBER + " TEXT," +
            KEY_PASIENLIST_ALAMAT_LAB + " TEXT," +
            KEY_PASIENLIST_ALAMAT_PASIEN + " TEXT," +
            KEY_PASIENLIST_LONGITUDE_PASIEN + " REAL," +
            KEY_PASIENLIST_LATITUDE_PASIEN + " REAL," +
            KEY_PASIENLIST_LONGITUDE_LAB + " REAL," +
            KEY_PASIENLIST_LATITUDE_LAB + " REAL," +
            KEY_PASIENLIST_TOTAL_HARGA + " REAL," +
            KEY_PASIENLIST_JENIS + " INTEGER, " +
            KEY_PASIENLIST_WAKTU + " TEXT " +
            ")";

    private static final String KEY_PASIEN_INVOICE = "pasien_id_invoice";
    private static final String KEY_PASIEN_ID_PASIEN = "pasien_id_pasien";
    private static final String KEY_PASIEN_NAMA_PASIEN = "pasien_nama_pasien";
    private static final String KEY_PASIEN_ID_TESTLAB = "pasien_id_testlab";
    private static final String KEY_PASIEN_USIA_PASIEN = "pasien_usia_pasien";
    private static final String KEY_PASIEN_GENDER_PASIEN = "pasien_gender_pasien"; //1=male 2=female
    private static final String CREATE_TABLE_PASIEN = "CREATE TABLE "
            + TABLE_PASIEN + "(" + KEY_PASIEN_INVOICE + " TEXT," +
            KEY_PASIEN_ID_PASIEN + " INTEGER," +
            KEY_PASIEN_NAMA_PASIEN + " TEXT," +
            KEY_PASIEN_ID_TESTLAB + " INTEGER," +
            KEY_PASIEN_USIA_PASIEN + " INTEGER," +
            KEY_PASIEN_GENDER_PASIEN + " INTEGER" +
            ")";

    private static final String KEY_TESTLAB_ID = "testlab_id_testlab";
    private static final String KEY_TESTLAB_NAMA = "testlab_nama_test";
    private static final String KEY_TESTLAB_HARGA_LAB = "testlab_harga_lab";
    private static final String KEY_TESTLAB_HARGA_MEDIS = "testlab_harga_medis";
    private static final String CREATE_TABLE_TESTLAB = "CREATE TABLE "
            + TABLE_TESTLAB + "(" + KEY_TESTLAB_ID + " INTEGER," +
            KEY_TESTLAB_NAMA + " TEXT," +
            KEY_TESTLAB_HARGA_LAB + " REAL," +
            KEY_TESTLAB_HARGA_MEDIS + " REAL" +
            ")";

    private static final String KEY_NAME = "name";
    private static final String KEY_LONGITUDE = "longitude";
    private static final String KEY_LATITUDE = "latitude";
    private static final String KEY_ALAMAT = "alamat";
    private static final String KEY_CREATED_AT = "created_at";

    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        /*String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_LONGITUDE + " TEXT UNIQUE," + KEY_LATITUDE + " TEXT," + KEY_ALAMAT + " TEXT,"
                + KEY_CREATED_AT + " TEXT" + ")";*/
        db.execSQL(CREATE_TABLE_PASIENLIST);
        db.execSQL(CREATE_TABLE_PASIEN);
        db.execSQL(CREATE_TABLE_TESTLAB);

        Log.d(TAG, "Database tables created");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PASIENLIST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PASIEN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TESTLAB);

        // Create tables again
        onCreate(db);
    }

    public String insertPasienList(PasienList pasienList){
        SQLiteDatabase db = this.getWritableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_PASIENLIST + " WHERE "
                + KEY_PASIENLIST_INVOICE + " = ?";
        selectQuery = "SELECT  * FROM " + TABLE_PASIENLIST + " t1, " + TABLE_PASIEN + " t2, " + TABLE_TESTLAB + " t3 WHERE t1."
                + KEY_PASIENLIST_INVOICE + " = ?";
        //Cursor c = db.rawQuery(selectQuery, new String[]{ pasienList.getInvoice() });
        String[] temp={"x"};
        temp[0]=pasienList.getInvoice();
        Cursor c = db.rawQuery(selectQuery, temp);

        if(c.getCount()==0) {

            ContentValues values_pasienlist = new ContentValues();
            values_pasienlist.put(KEY_PASIENLIST_INVOICE, pasienList.getInvoice());
            values_pasienlist.put(KEY_PASIENLIST_ID_MEMBER, pasienList.getId_member());
            values_pasienlist.put(KEY_PASIENLIST_ID_LAB, pasienList.getId_lab());
            values_pasienlist.put(KEY_PASIENLIST_NAMA_LAB, pasienList.getNama_lab());
            values_pasienlist.put(KEY_PASIENLIST_NAMA_MEMBER, pasienList.getNama_member());
            values_pasienlist.put(KEY_PASIENLIST_ALAMAT_LAB, pasienList.getAlamat_lab());
            values_pasienlist.put(KEY_PASIENLIST_ALAMAT_PASIEN, pasienList.getAlamat_pasien());
            values_pasienlist.put(KEY_PASIENLIST_LONGITUDE_PASIEN, pasienList.getLongitude_pasien());
            values_pasienlist.put(KEY_PASIENLIST_LATITUDE_PASIEN, pasienList.getLatitude_pasien());
            values_pasienlist.put(KEY_PASIENLIST_LONGITUDE_LAB, pasienList.getLongitude_lab());
            values_pasienlist.put(KEY_PASIENLIST_LATITUDE_LAB, pasienList.getLatitude_lab());
            values_pasienlist.put(KEY_PASIENLIST_WAKTU, pasienList.getWaktu());
            //values_pasienlist.put(KEY_PASIENLIST_JENIS, 0);

            ArrayList<ContentValues> values_pasien_tot = new ArrayList<ContentValues>();
            ArrayList<ContentValues> values_testlab_tot = new ArrayList<ContentValues>();

            for(int jj=0;jj<pasienList.getPasien().size();jj++){
                ContentValues values_pasien=new ContentValues();
                values_pasien.put(KEY_PASIEN_INVOICE, pasienList.getInvoice());
                values_pasien.put(KEY_PASIEN_ID_PASIEN, pasienList.getPasien().get(jj).getIdPasien());
                values_pasien.put(KEY_PASIEN_NAMA_PASIEN, pasienList.getPasien().get(jj).getName());
                values_pasien.put(KEY_PASIEN_ID_TESTLAB, pasienList.getPasien().get(jj).getTestLabId());
                values_pasien.put(KEY_PASIEN_USIA_PASIEN, pasienList.getPasien().get(jj).getUsiaPasien());
                values_pasien.put(KEY_PASIEN_GENDER_PASIEN, pasienList.getPasien().get(jj).getGender());
                values_pasien_tot.add(values_pasien);
                for(int kk=0;kk<pasienList.getPasien().get(jj).getTestsLab().size();kk++){
                    ContentValues values_testlab=new ContentValues();
                    values_testlab.put(KEY_TESTLAB_ID, pasienList.getPasien().get(jj).getTestLabId());
                    values_testlab.put(KEY_TESTLAB_NAMA, pasienList.getPasien().get(jj).getTestsLab().get(kk).getName());
                    values_testlab.put(KEY_TESTLAB_HARGA_LAB, pasienList.getPasien().get(jj).getTestsLab().get(kk).getHargaLab());
                    values_testlab.put(KEY_TESTLAB_HARGA_MEDIS, pasienList.getPasien().get(jj).getTestsLab().get(kk).getHargaMedis());
                    values_testlab_tot.add(values_testlab);
                }
            }




            try {
                db.insert(TABLE_PASIENLIST, null, values_pasienlist);
                for(int jj=0;jj<values_pasien_tot.size();jj++){
                    db.insert(TABLE_PASIEN, null, values_pasien_tot.get(jj));

                }
                for(int jj=0;jj<values_testlab_tot.size();jj++){
                    db.insert(TABLE_TESTLAB, null, values_testlab_tot.get(jj));

                }
                return "insert success";
            } catch (Exception e) {
                e.printStackTrace();
                return "insert error";
            }
        }else{
            c.moveToFirst();
            //return "data exists : " + c.getString(c.getColumnIndex(KEY_PASIENLIST_INVOICE));
            return "data exists : " + c.getString(c.getColumnIndex(KEY_PASIENLIST_INVOICE)) + " : " + c.getString(c.getColumnIndex(KEY_PASIEN_NAMA_PASIEN));
        }

    }

    public void getPasienList(){
MyApplication.getInstance().clearPasiensFromList();
        SQLiteDatabase db = getReadableDatabase();

        Cursor c_pasienlist = db.rawQuery("select * from pasienlist", null);
        if(c_pasienlist.getCount()>0) {
            if (c_pasienlist.moveToFirst()) {
                do {
                    PasienList pasienlist = new PasienList();

                    pasienlist.setInvoice(c_pasienlist.getString(c_pasienlist.getColumnIndex(KEY_PASIENLIST_INVOICE)));
                    pasienlist.setAlamat_pasien(c_pasienlist.getString(c_pasienlist.getColumnIndex(KEY_PASIENLIST_ALAMAT_PASIEN)));
                    pasienlist.setId_lab(c_pasienlist.getInt(c_pasienlist.getColumnIndex(KEY_PASIENLIST_ID_LAB)));
                    pasienlist.setNama_lab(c_pasienlist.getString(c_pasienlist.getColumnIndex(KEY_PASIENLIST_NAMA_LAB)));
                    pasienlist.setAlamat_lab(c_pasienlist.getString(c_pasienlist.getColumnIndex(KEY_PASIENLIST_ALAMAT_LAB)));
                    pasienlist.setId_member(c_pasienlist.getInt(c_pasienlist.getColumnIndex(KEY_PASIENLIST_ID_MEMBER)));
                    pasienlist.setNama_member(c_pasienlist.getString(c_pasienlist.getColumnIndex(KEY_PASIENLIST_NAMA_MEMBER)));
                    pasienlist.setLongitude_pasien(c_pasienlist.getDouble(c_pasienlist.getColumnIndex(KEY_PASIENLIST_LONGITUDE_PASIEN)));
                    pasienlist.setLatitude_pasien(c_pasienlist.getDouble(c_pasienlist.getColumnIndex(KEY_PASIENLIST_LATITUDE_PASIEN)));
                    pasienlist.setLongitude_lab(c_pasienlist.getDouble(c_pasienlist.getColumnIndex(KEY_PASIENLIST_LONGITUDE_LAB)));
                    pasienlist.setLatitude_lab(c_pasienlist.getDouble(c_pasienlist.getColumnIndex(KEY_PASIENLIST_LATITUDE_LAB)));
                    pasienlist.setWaktu(c_pasienlist.getString(c_pasienlist.getColumnIndex(KEY_PASIENLIST_WAKTU)));
                    Boolean masihorder=false;
                    if(c_pasienlist.getInt(c_pasienlist.getColumnIndex(KEY_PASIENLIST_JENIS))==1){
                        masihorder=true;

                    }
                    pasienlist.setMasih_order(masihorder);

                    ArrayList<Pasien> x = new ArrayList<Pasien>();


                    String[] temp={"x"};
                    temp[0]=c_pasienlist.getString(c_pasienlist.getColumnIndex(KEY_PASIENLIST_INVOICE));
                    Cursor c_pasien = db.rawQuery("select * from " + TABLE_PASIEN + " where " + KEY_PASIEN_INVOICE + "=?", temp);
                    if (c_pasien.moveToFirst()) {
                        do {
                            Pasien pasien = new Pasien();
                            pasien.setIdPasien(c_pasien.getInt(c_pasien.getColumnIndex(KEY_PASIEN_ID_PASIEN)));
                            pasien.setName(c_pasien.getString(c_pasien.getColumnIndex(KEY_PASIEN_NAMA_PASIEN)));
                            pasien.setUsiaPasien(c_pasien.getInt(c_pasien.getColumnIndex(KEY_PASIEN_USIA_PASIEN)));
                            pasien.setGender(c_pasien.getInt(c_pasien.getColumnIndex(KEY_PASIEN_GENDER_PASIEN)));
                            ArrayList<Tests_lab> test1_array = new ArrayList<Tests_lab>();
                            String[] temp2 = {"x"};
                            temp2[0] = String.valueOf(c_pasien.getInt(c_pasien.getColumnIndex(KEY_PASIEN_ID_PASIEN)));
                            Cursor c_testlab = db.rawQuery("select * from " + TABLE_TESTLAB + " where " + KEY_TESTLAB_ID + "=?", temp2);
                            if (c_testlab.moveToFirst()) {
                                do {
                                    Tests_lab test1 = new Tests_lab();
                                    test1.setName(c_testlab.getString(c_testlab.getColumnIndex(KEY_TESTLAB_NAMA)));
                                    test1.setHargaMedis(c_testlab.getDouble(c_testlab.getColumnIndex(KEY_TESTLAB_HARGA_MEDIS)));
                                    test1.setHargaLab(c_testlab.getDouble(c_testlab.getColumnIndex(KEY_TESTLAB_HARGA_LAB)));
                                    test1_array.add(test1);
                                } while (c_testlab.moveToNext());
                            }
                            pasien.setTestsLab(test1_array);
                            x.add(pasien);
                        }while (c_pasien.moveToNext()) ;
                    }
                    pasienlist.setPasien(x);

                    ArrayList<Tests_lab> tests_labs = new ArrayList<Tests_lab>();
                    Cursor c_testlab = db.rawQuery("select " + TABLE_PASIEN + "." + KEY_PASIEN_INVOICE + "," + TABLE_TESTLAB + "." + KEY_TESTLAB_NAMA + ",count(" + TABLE_TESTLAB + "." + KEY_TESTLAB_NAMA + ") as cnt,sum(" + TABLE_TESTLAB + "." + KEY_TESTLAB_HARGA_LAB + ") as harga_lab,sum(" + TABLE_TESTLAB + "." + KEY_TESTLAB_HARGA_MEDIS + ") as harga_medis from " + TABLE_PASIEN + "," + TABLE_TESTLAB + "  where " + TABLE_PASIEN + "." + KEY_PASIEN_ID_TESTLAB + "=" + TABLE_TESTLAB + "." + KEY_TESTLAB_ID + " and " + TABLE_PASIEN + "." + KEY_PASIEN_INVOICE + "=? group by " + TABLE_PASIEN + "." + KEY_PASIEN_INVOICE + "," + TABLE_TESTLAB + "." + KEY_TESTLAB_NAMA , temp);
                    if (c_testlab.moveToFirst()) {
                        do {
                            Tests_lab tl = new Tests_lab();
                            tl.setName(c_testlab.getString(1));
                            tl.setJml(c_testlab.getInt(2));
                            tl.setHargaLab(c_testlab.getDouble(3));
                            tl.setHargaMedis(c_testlab.getDouble(4));
                            tests_labs.add(tl);
                        }while (c_testlab.moveToNext()) ;
                    }
                    pasienlist.setTests_labs(tests_labs);

                    MyApplication.getInstance().addPasienToList(pasienlist);

                } while (c_pasienlist.moveToNext());

            }

        }else{
            //nothing
        }

    }

    public void getPasienList_v1(){
        MyApplication.getInstance().clearPasiensFromList();
        SQLiteDatabase db = getReadableDatabase();

        Cursor c_pasienlist = db.rawQuery("select * from pasienlist", null);
        if(c_pasienlist.getCount()>0) {
            if (c_pasienlist.moveToFirst()) {
                do {
                    PasienList pasienlist = new PasienList();
                    pasienlist.setInvoice(c_pasienlist.getString(c_pasienlist.getColumnIndex(KEY_PASIENLIST_INVOICE)));
                    pasienlist.setAlamat_pasien(c_pasienlist.getString(c_pasienlist.getColumnIndex(KEY_PASIENLIST_ALAMAT_PASIEN)));
                    pasienlist.setId_lab(c_pasienlist.getInt(c_pasienlist.getColumnIndex(KEY_PASIENLIST_ID_LAB)));
                    pasienlist.setNama_lab(c_pasienlist.getString(c_pasienlist.getColumnIndex(KEY_PASIENLIST_NAMA_LAB)));
                    pasienlist.setAlamat_lab(c_pasienlist.getString(c_pasienlist.getColumnIndex(KEY_PASIENLIST_ALAMAT_LAB)));
                    pasienlist.setId_member(c_pasienlist.getInt(c_pasienlist.getColumnIndex(KEY_PASIENLIST_ID_MEMBER)));
                    pasienlist.setNama_member(c_pasienlist.getString(c_pasienlist.getColumnIndex(KEY_PASIENLIST_NAMA_MEMBER)));
                    pasienlist.setLongitude_pasien(c_pasienlist.getDouble(c_pasienlist.getColumnIndex(KEY_PASIENLIST_LONGITUDE_PASIEN)));
                    pasienlist.setLatitude_pasien(c_pasienlist.getDouble(c_pasienlist.getColumnIndex(KEY_PASIENLIST_LATITUDE_PASIEN)));
                    pasienlist.setLongitude_lab(c_pasienlist.getDouble(c_pasienlist.getColumnIndex(KEY_PASIENLIST_LONGITUDE_LAB)));
                    pasienlist.setLatitude_lab(c_pasienlist.getDouble(c_pasienlist.getColumnIndex(KEY_PASIENLIST_LATITUDE_LAB)));
                    pasienlist.setWaktu(c_pasienlist.getString(c_pasienlist.getColumnIndex(KEY_PASIENLIST_WAKTU)));
                    Boolean masihorder=false;
                    if(c_pasienlist.getInt(c_pasienlist.getColumnIndex(KEY_PASIENLIST_JENIS))==1){
                        masihorder=true;

                    }
                    pasienlist.setMasih_order(masihorder);

                    ArrayList<Pasien> x = new ArrayList<Pasien>();
                    String[] temp={"x"};
                    temp[0]=c_pasienlist.getString(c_pasienlist.getColumnIndex(KEY_PASIENLIST_INVOICE));
                    Cursor c_pasien = db.rawQuery("select * from " + TABLE_PASIEN + " where " + KEY_PASIEN_INVOICE + "=?", temp);
                    if (c_pasien.moveToFirst()) {
                        do {
                            Pasien pasien = new Pasien();
                            pasien.setIdPasien(c_pasien.getInt(c_pasien.getColumnIndex(KEY_PASIEN_ID_PASIEN)));
                            pasien.setName(c_pasien.getString(c_pasien.getColumnIndex(KEY_PASIEN_NAMA_PASIEN)));
                            pasien.setUsiaPasien(c_pasien.getInt(c_pasien.getColumnIndex(KEY_PASIEN_USIA_PASIEN)));
                            pasien.setGender(c_pasien.getInt(c_pasien.getColumnIndex(KEY_PASIEN_GENDER_PASIEN)));
                            ArrayList<Tests_lab> test1_array = new ArrayList<Tests_lab>();
                            String[] temp2 = {"x"};
                            temp2[0] = String.valueOf(c_pasien.getInt(c_pasien.getColumnIndex(KEY_PASIEN_ID_PASIEN)));
                            Cursor c_testlab = db.rawQuery("select * from " + TABLE_TESTLAB + " where " + KEY_TESTLAB_ID + "=?", temp2);
                            if (c_testlab.moveToFirst()) {
                                do {
                                    Tests_lab test1 = new Tests_lab();
                                    test1.setName(c_testlab.getString(c_testlab.getColumnIndex(KEY_TESTLAB_NAMA)));
                                    test1.setHargaMedis(c_testlab.getDouble(c_testlab.getColumnIndex(KEY_TESTLAB_HARGA_MEDIS)));
                                    test1.setHargaLab(c_testlab.getDouble(c_testlab.getColumnIndex(KEY_TESTLAB_HARGA_LAB)));
                                    test1_array.add(test1);
                                } while (c_testlab.moveToNext());
                            }
                            pasien.setTestsLab(test1_array);
                            x.add(pasien);
                        }while (c_pasien.moveToNext()) ;
                    }
                    pasienlist.setPasien(x);
                    MyApplication.getInstance().addPasienToList(pasienlist);

                } while (c_pasienlist.moveToNext());

            }

        }else{
            //nothing
        }

    }

    public void setMasihOrder(String inv,Boolean m){
        Integer mm=0;
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();
        if(m==true){
            mm=1;
        }else{
            mm=0;
        }
        if(!inv.equals("")) {
            values.put(KEY_PASIENLIST_JENIS, mm);
            db.update(TABLE_PASIENLIST, values, KEY_PASIENLIST_INVOICE + " = ?",
                    new String[]{inv});
        }
    }

    // closing database
    public void closeDB() {
        SQLiteDatabase db = getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    /**
     * get datetime
     * */
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

}