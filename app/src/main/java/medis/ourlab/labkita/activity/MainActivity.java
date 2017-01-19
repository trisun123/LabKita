package medis.ourlab.labkita.activity;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v13.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.messaging.FirebaseMessaging;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import medis.ourlab.labkita.FeedImageView;

import medis.ourlab.labkita.R;
import medis.ourlab.labkita.app.Config;
import medis.ourlab.labkita.app.EndPoints;
import medis.ourlab.labkita.app.MyApplication;
import medis.ourlab.labkita.helper.CircleImage;
import medis.ourlab.labkita.helper.SQLiteHandler;
import medis.ourlab.labkita.model.Pasien;
import medis.ourlab.labkita.model.PasienList;
import medis.ourlab.labkita.model.Tests_lab;
import medis.ourlab.labkita.util.NotificationUtils;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private BroadcastReceiver mRegistrationBroadcastReceiver;



    FeedImageView profilePic2;
    ImageView profilePic;
    //TextView orderlist;
    LinearLayout orderlist,appointments;
    SQLiteHandler db;

    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    LocationManager locationManager;
    String lat, lon;
    float akurasival;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//disable segala tentang services
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // checking for type intent filter
                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                    // gcm successfully registered
                    // now subscribe to `global` topic to receive app wide notifications
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);



                } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                    // new push notification is received

                    String message = intent.getStringExtra("message");

                    Toast.makeText(getApplicationContext(), "Push notification: " + message, Toast.LENGTH_LONG).show();


                }
            }
        };



        db = new SQLiteHandler(getApplicationContext());

        //DB STUFF
        Integer id_order=1;
        PasienList pasienlist = new PasienList();
        pasienlist.setIdOrder(id_order);
        pasienlist.setInvoice("INV/20160815/VXX/IV/005258");
        //pasienlist.setId_invoice(1);
        pasienlist.setAlamat_pasien("Jl. Pondok Mandar No. 18 RT 04/08 Kalibaru, Sukmajaya, Depok");
        pasienlist.setId_lab(1);
        pasienlist.setNama_lab("Lab Hanan");
        pasienlist.setAlamat_lab("Komplek Pearl Garden No. 3 Sawangan, Depok");
        pasienlist.setId_member(1);
        pasienlist.setNama_member("Eko Pranoto");
        pasienlist.setLongitude_pasien(112.619995);
        pasienlist.setLatitude_pasien(-7.432976);
        pasienlist.setLongitude_lab(112.708280);
        pasienlist.setLatitude_lab(-7.337552);
        pasienlist.setWaktu("01-10-2017 20:00");

        ArrayList<Pasien> x = new ArrayList<Pasien>();

        Pasien pasien = new Pasien();
        pasien.setIdPasien(1);
        pasien.setName("Sari Puspita Dewi");
        pasien.setUsiaPasien(32);
        pasien.setGender(2);
        ArrayList<Tests_lab> test1_array = new ArrayList<Tests_lab>();
        Tests_lab test1 = new Tests_lab();
        test1.setName("Cholesterol Total");
        test1.setHargaMedis(42000);
        test1.setHargaLab(120000);
        test1_array.add(test1);
        test1 = new Tests_lab();
        test1.setName("Kalium Urin");
        test1.setHargaMedis(35000);
        test1.setHargaLab(90000);
        test1_array.add(test1);
        pasien.setTestsLab(test1_array);
        pasien.setTestLabId(1);
        x.add(pasien);

        pasien = new Pasien();
        pasien.setIdPasien(2);
        pasien.setName("Tyas Maheni");
        pasien.setUsiaPasien(35);
        pasien.setGender(2);
        test1_array = new ArrayList<Tests_lab>();
        test1 = new Tests_lab();
        test1.setName("Cholesterol Total");
        test1.setHargaMedis(42000);
        test1.setHargaLab(120000);
        test1_array.add(test1);
        pasien.setTestsLab(test1_array);
        pasien.setTestLabId(2);
        x.add(pasien);

        //20170102 tambah orang
        pasien = new Pasien();
        pasien.setIdPasien(10);
        pasien.setName("Orang Ketiga");
        pasien.setUsiaPasien(17);
        pasien.setGender(1);
        test1_array = new ArrayList<Tests_lab>();
        test1 = new Tests_lab();
        test1.setName("Omepros");
        test1.setHargaMedis(100000);
        test1.setHargaLab(0);
        test1_array.add(test1);
        test1 = new Tests_lab();
        test1.setName("Obat Diabetes");
        test1.setHargaMedis(120000);
        test1.setHargaLab(99000);
        test1_array.add(test1);
        test1 = new Tests_lab();
        test1.setName("Obat Kanker");
        test1.setHargaMedis(10);
        test1.setHargaLab(20);
        test1_array.add(test1);
        pasien.setTestsLab(test1_array);
        pasien.setTestLabId(10);
        x.add(pasien);

        pasienlist.setPasien(x);

        //ADD TO DB
        //MyApplication.getInstance().addPasienToList(pasienlist);


        //tambah pasien lagi

        id_order=2;
        pasienlist = new PasienList();
        pasienlist.setIdOrder(id_order);
        pasienlist.setInvoice("INV/20160815/VXX/IV/005259");
        pasienlist.setId_invoice(id_order);
        pasienlist.setAlamat_pasien("Jl. Pondok Mandar No. 18 RT 04/08 Kalibaru, Sukmajaya, Depok");
        pasienlist.setId_lab(1);
        pasienlist.setNama_lab("Lab Hanan");
        pasienlist.setAlamat_lab("Komplek Pearl Garden No. 3 Sawangan, Depok");
        pasienlist.setId_member(1);
        pasienlist.setNama_member("Eko Pranoto");
        pasienlist.setLongitude_pasien(112.719995);
        pasienlist.setLatitude_pasien(-7.332976);
        pasienlist.setLongitude_lab(112.708280);
        pasienlist.setLatitude_lab(-7.337552);
        pasienlist.setWaktu("01-20-2017 14:00");

        x = new ArrayList<Pasien>();

        pasien = new Pasien();
        pasien.setIdPasien(3);
        pasien.setName("Nanag Wahyudi");
        pasien.setUsiaPasien(40);
        pasien.setGender(1);
        test1_array = new ArrayList<Tests_lab>();
        test1 = new Tests_lab();
        test1.setName("Prolaktin");
        test1.setHargaMedis(49000);
        test1.setHargaLab(150020);
        test1_array.add(test1);
        test1 = new Tests_lab();
        test1.setName("Retikulosit");
        test1.setHargaMedis(49000);
        test1.setHargaLab(83000);
        test1_array.add(test1);
        pasien.setTestsLab(test1_array);
        pasien.setTestLabId(3);
        x.add(pasien);

        pasien = new Pasien();
        pasien.setIdPasien(4);
        pasien.setName("Sonny Satriyono");
        pasien.setUsiaPasien(40);
        pasien.setGender(1);
        test1_array = new ArrayList<Tests_lab>();
        test1 = new Tests_lab();
        test1.setName("Serum Iron");
        test1.setHargaMedis(56000);
        test1.setHargaLab(170000);
        test1_array.add(test1);
        pasien.setTestsLab(test1_array);
        pasien.setTestLabId(4);
        x.add(pasien);

        pasienlist.setPasien(x);
        //ADD TO DB


        //tambah pasien lagi

        id_order=3;
        pasienlist = new PasienList();
        pasienlist.setIdOrder(id_order);
        pasienlist.setInvoice("INV/20160815/VXX/IV/005260");
        pasienlist.setId_invoice(id_order);
        pasienlist.setAlamat_pasien("Jl. Pondok Mandar No. 18 RT 04/08 Kalibaru, Sukmajaya, Depok");
        pasienlist.setId_lab(1);
        pasienlist.setNama_lab("Lab Hanan");
        pasienlist.setAlamat_lab("Komplek Pearl Garden No. 3 Sawangan, Depok");
        pasienlist.setId_member(1);
        pasienlist.setNama_member("Eko Pranoto");
        pasienlist.setLongitude_pasien(112.710995);
        pasienlist.setLatitude_pasien(-7.332076);
        pasienlist.setLongitude_lab(112.708280);
        pasienlist.setLatitude_lab(-7.337552);
        pasienlist.setWaktu("02-20-2017 14:00");

        x = new ArrayList<Pasien>();

        pasien = new Pasien();
        pasien.setIdPasien(5);
        pasien.setName("Ojo Lara Lara");
        pasien.setUsiaPasien(45);
        pasien.setGender(1);
        test1_array = new ArrayList<Tests_lab>();
        test1 = new Tests_lab();
        test1.setName("Tester");
        test1.setHargaMedis(50000);
        test1.setHargaLab(160020);
        test1_array.add(test1);
        test1 = new Tests_lab();
        test1.setName("Omepros");
        test1.setHargaMedis(39000);
        test1.setHargaLab(100000);
        test1_array.add(test1);
        pasien.setTestsLab(test1_array);
        pasien.setTestLabId(5);
        x.add(pasien);

        pasien = new Pasien();
        pasien.setIdPasien(6);
        pasien.setName("Icha Icha Paradise");
        pasien.setUsiaPasien(46);
        pasien.setGender(2);
        test1_array = new ArrayList<Tests_lab>();
        test1 = new Tests_lab();
        test1.setName("APTX 4869");
        test1.setHargaMedis(70000);
        test1.setHargaLab(990000);
        test1_array.add(test1);
        pasien.setTestsLab(test1_array);
        pasien.setTestLabId(6);
        x.add(pasien);



        pasienlist.setPasien(x);
        //ADD TO DB


//tambah pasien lagi

        id_order=4;
        pasienlist = new PasienList();
        pasienlist.setIdOrder(id_order);
        pasienlist.setInvoice("INV/20160815/VXX/IV/005261");
        pasienlist.setId_invoice(id_order);
        pasienlist.setAlamat_pasien("Jl. Pondok Mandar No. 18 RT 04/08 Kalibaru, Sukmajaya, Depok");
        pasienlist.setId_lab(1);
        pasienlist.setNama_lab("Lab Hanan");
        pasienlist.setAlamat_lab("Komplek Pearl Garden No. 3 Sawangan, Depok");
        pasienlist.setId_member(1);
        pasienlist.setNama_member("Eko Pranoto");
        pasienlist.setLongitude_pasien(112.710995);
        pasienlist.setLatitude_pasien(-7.332076);
        pasienlist.setLongitude_lab(112.708280);
        pasienlist.setLatitude_lab(-7.337552);
        pasienlist.setWaktu("03-20-2017 14:00");

        x = new ArrayList<Pasien>();

        pasien = new Pasien();
        pasien.setIdPasien(7);
        pasien.setName("Urip Penak");
        pasien.setUsiaPasien(30);
        pasien.setGender(1);
        test1_array = new ArrayList<Tests_lab>();
        test1 = new Tests_lab();
        test1.setName("Beta Tester");
        test1.setHargaMedis(60000);
        test1.setHargaLab(170090);
        test1_array.add(test1);
        test1 = new Tests_lab();
        test1.setName("Obat Ganteng");
        test1.setHargaMedis(3000);
        test1.setHargaLab(1000);
        test1_array.add(test1);
        pasien.setTestsLab(test1_array);
        pasien.setTestLabId(7);
        x.add(pasien);

        pasien = new Pasien();
        pasien.setIdPasien(8);
        pasien.setName("Cepat Sembuh");
        pasien.setUsiaPasien(77);
        pasien.setGender(1);
        test1_array = new ArrayList<Tests_lab>();
        test1 = new Tests_lab();
        test1.setName("Super Soldier Serum");
        test1.setHargaMedis(70000);
        test1.setHargaLab(9990000);
        test1_array.add(test1);
        pasien.setTestsLab(test1_array);
        pasien.setTestLabId(8);
        x.add(pasien);

        //20170101 test jumlah pasien yang berbeda
        pasien = new Pasien();
        pasien.setIdPasien(9);
        pasien.setName("Orang Tambahan");
        pasien.setUsiaPasien(46);
        pasien.setGender(2);
        test1_array = new ArrayList<Tests_lab>();
        test1 = new Tests_lab();
        test1.setName("Manna Salwa");
        test1.setHargaMedis(70000);
        test1.setHargaLab(990000);
        test1_array.add(test1);
        pasien.setTestsLab(test1_array);
        pasien.setTestLabId(9);
        x.add(pasien);

        pasienlist.setPasien(x);
        //ADD TO DB


        db.getPasienList();

        db.closeDB();
        //END OF DB STUFF




        ImageLoader imageLoader = MyApplication.getInstance().getImageLoader();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        View myLayout = findViewById(R.id.appbarmain);
        View myLayout2 = myLayout.findViewById(R.id.contentmain);
        TextView namaebesar = (TextView) myLayout2.findViewById(R.id.namaebesar);
        TextView motto = (TextView) myLayout2.findViewById(R.id.motto);

        //orderlist = (TextView) myLayout2.findViewById(R.id.orderlist);
        orderlist = (LinearLayout) myLayout2.findViewById(R.id.orderlist);
        orderlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(getApplicationContext(), ListPasien.class));
                //startActivity(new Intent(getApplicationContext(), LiteListDemo.class));
                startActivity(new Intent(getApplicationContext(), ListPasien_tabview_sqlite.class));
            }
        });
        /*appointments = (LinearLayout) myLayout2.findViewById(R.id.appointments);
        appointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ListPasien_tabview.class));
            }
        });*/

        namaebesar.setText(MyApplication.getInstance().getPrefManager().getUser().getNamae());
        motto.setText(MyApplication.getInstance().getPrefManager().getUser().getMotto());

        TextView balancee = (TextView) myLayout2.findViewById(R.id.token);

        //balancee.setText(String.format("%1$,.2f", MyApplication.getInstance().getPrefManager().getUser().getBalance()));
        balancee.setText(String.format(Locale.GERMAN,"%,.0f",MyApplication.getInstance().getPrefManager().getUser().getBalance()));


        //NetworkImageView profilePic = (NetworkImageView) myLayout2.findViewById(R.id.profilePic);
        //profilePic2 = (FeedImageView) myLayout2.findViewById(R.id.profilePic);
        profilePic= (ImageView ) myLayout2.findViewById(R.id.profilePic);
        Picasso.with(getApplicationContext())
                .load(EndPoints.BASE_URL + "/" + MyApplication.getInstance().getPrefManager().getUser().getFoto())
                .placeholder(R.drawable.orang)
                .transform(new CircleImage())
                .into(profilePic);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Home");
        toolbar.setTitle("Home");


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onDestroy() {
        try {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        super.onDestroy();

    }

    @Override
    public void onUserLeaveHint(){
        try {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        super.onUserLeaveHint();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            //return true;
            MyApplication.getInstance().logout();

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.logout) {
            MyApplication.getInstance().logout();
        }
        /* if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        } */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    @Override
    public void onConnected(Bundle bundle) {

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        //mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {

            if(mGoogleApiClient.isConnected())
                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {

        mLastLocation = location;
        lat = String.valueOf(location.getLatitude());
        lon = String.valueOf(location.getLongitude());
        akurasival = location.getAccuracy();
        MyApplication.getInstance().setLon(location.getLongitude());
        MyApplication.getInstance().setLat(location.getLatitude());
        MyApplication.getInstance().setAkurasi(location.getAccuracy());



    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

        //buildGoogleApiClient();

    }

    @Override
    public void onResume(){
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            checkLocationPermission();
        }
        else
        {
            if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
            {
                showGPSDisabledAlertToUser();
            }
        }

        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
            {
                buildGoogleApiClient();
                //mGoogleMap.setMyLocationEnabled(true);
            }
        }
        else
        {
            buildGoogleApiClient();
            //mGoogleMap.setMyLocationEnabled(true);
        }




        super.onResume();

        //disable segala tentang services
        // register GCM registration complete receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.REGISTRATION_COMPLETE));

        //disable segala tentang services
        // register new push message receiver
        // by doing this, the activity will be notified each time a new message arrives
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.PUSH_NOTIFICATION));

        //disable segala tentang services
        // clear the notification area when the app is opened
        NotificationUtils.clearNotifications(getApplicationContext());
    }



    @Override
    public void onPause()
    {
        super.onPause();
//disable segala tentang services
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        //stop location updates when Activity is no longer active
        /*if (mGoogleApiClient != null)
        {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }*/
    }


    private void showGPSDisabledAlertToUser()
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("GPS is disabled in your device. Would you like to enable it?")
                .setCancelable(false)
                .setPositiveButton("Settings", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        Intent callGPSSettingIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(callGPSSettingIntent);

                        //mapFrag.getMapAsync(MainActivity.this);
                    }
                });
        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                dialog.cancel();
            }
        });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    protected synchronized void buildGoogleApiClient()
    {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    public boolean checkLocationPermission()
    {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.ACCESS_FINE_LOCATION))
            {
                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            else
            {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        }
        else
        {
            if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
            {
                showGPSDisabledAlertToUser();
            }

            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults)
    {
        switch (requestCode)
        {
            case MY_PERMISSIONS_REQUEST_LOCATION:
            {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                    {

                        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
                        {
                            showGPSDisabledAlertToUser();
                        }

                        if (mGoogleApiClient == null)
                        {
                            buildGoogleApiClient();
                        }
                        //mGoogleMap.setMyLocationEnabled(true);
                    }
                }
                else
                {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}


