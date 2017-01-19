package medis.ourlab.labkita.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

import medis.ourlab.labkita.FeedImageView;
import medis.ourlab.labkita.R;
import medis.ourlab.labkita.app.EndPoints;
import medis.ourlab.labkita.app.MyApplication;
import medis.ourlab.labkita.helper.CircleImage;
import medis.ourlab.labkita.helper.SQLiteHandler;
import medis.ourlab.labkita.model.Pasien;
import medis.ourlab.labkita.model.PasienList;
import medis.ourlab.labkita.model.Tests_lab;

public class MainActivity_ori_sebelum_implement_gps extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    FeedImageView profilePic2;
    ImageView profilePic;
    //TextView orderlist;
    LinearLayout orderlist,appointments;
    SQLiteHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        pasienlist.setWaktu("12-12-2016 13:00");

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
        test1.setName("Albumin");
        test1.setHargaMedis(55000);
        test1.setHargaLab(140000);
        test1_array.add(test1);
        pasien.setTestsLab(test1_array);
        pasien.setTestLabId(2);
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

        db.getPasienList();

        db.closeDB();
        //END OF DB STUFF
        PasienList ada=MyApplication.getInstance().getPasienFromList().get(0);
        //MyApplication.getInstance().setAllPasienListFalseForPickOrder();



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


        balancee.setText(String.format(Locale.GERMAN,"%,.0f",MyApplication.getInstance().getPrefManager().getUser().getBalance()));



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
        /* View headerLayout =navigationView.getHeaderView(0);
        TextView namae,emaile;
        namae = (TextView) headerLayout.findViewById(R.id.namae);
        namae.setText(MyApplication.getInstance().getPrefManager().getUser().getNamae());
        emaile = (TextView) headerLayout.findViewById(R.id.emaile);
        emaile.setText(MyApplication.getInstance().getPrefManager().getUser().getEmail()); */


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






}
