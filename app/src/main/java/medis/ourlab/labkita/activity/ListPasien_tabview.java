package medis.ourlab.labkita.activity;

import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import medis.ourlab.labkita.R;
import medis.ourlab.labkita.app.MyApplication;
import medis.ourlab.labkita.model.Pasien;
import medis.ourlab.labkita.model.PasienList;
import medis.ourlab.labkita.model.Tests_lab;
import medis.ourlab.labkita.model.TwoFragment;
import medis.ourlab.labkita.model.OneFragment_expandable_withmap_v2;
/**
 * Created by tri on 11/1/2016.
 */

public class ListPasien_tabview extends AppCompatActivity {
    //ListView mylistview;
    ArrayList<PasienList> pasienList;
    //MyAdapter adapter;
    Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int[] tabIcons = {
            R.drawable.orderlist,
            R.drawable.appointments

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pasienList=new ArrayList<PasienList>();

        /*ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }*/

        Boolean sudahberisi = false;



        try {
            if (MyApplication.getInstance().getPasienListCount() > 0) {

                sudahberisi = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (sudahberisi == false) {
        //if(true){
            Integer id_order=1;
            PasienList pasienlist = new PasienList();
            pasienlist.setIdOrder(id_order);
            pasienlist.setInvoice("INV/20160815/VXX/IV/005258");
            pasienlist.setId_invoice(1);
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
            pasien.setTestLabId(id_order);
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
            pasien.setTestLabId(id_order);
            x.add(pasien);

            //pasienlist.setMasih_order(true);
            pasienlist.setPasien(x);

            MyApplication.getInstance().addPasienToList(pasienlist);

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

            x = new ArrayList<Pasien>();

            pasien = new Pasien();
            pasien.setIdPasien(1);
            pasien.setName("Nanag Wahyudi");
            pasien.setUsiaPasien(40);
            pasien.setGender(1);
            test1_array = new ArrayList<Tests_lab>();
            test1 = new Tests_lab();
            test1.setName("Prolaktin");
            test1.setHargaMedis(42000);
            test1.setHargaLab(120000);
            test1_array.add(test1);
            test1 = new Tests_lab();
            test1.setName("Retikulosit");
            test1.setHargaMedis(35000);
            test1.setHargaLab(90000);
            test1_array.add(test1);
            pasien.setTestsLab(test1_array);
            pasien.setTestLabId(id_order);
            x.add(pasien);

            pasien = new Pasien();
            pasien.setIdPasien(2);
            pasien.setName("Sonny Satriyono");
            pasien.setUsiaPasien(40);
            pasien.setGender(1);
            test1_array = new ArrayList<Tests_lab>();
            test1 = new Tests_lab();
            test1.setName("Serum Iron");
            test1.setHargaMedis(55000);
            test1.setHargaLab(140000);
            test1_array.add(test1);
            pasien.setTestsLab(test1_array);
            pasien.setTestLabId(id_order);
            x.add(pasien);


            pasienlist.setPasien(x);


            MyApplication.getInstance().addPasienToList(pasienlist);
        }

        setContentView(R.layout.listpasien_tabview);
        toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(R.string.order_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        //viewPager.setCurrentItem(0);


        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        //tabLayout.getTabAt(0).select();



        tabLayout.addOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {

                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        super.onTabSelected(tab);
                        int tabIconColor = ContextCompat.getColor(getApplicationContext(), R.color.tabSelectedIconColor);
                        tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);

                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        super.onTabUnselected(tab);
                        int tabIconColor = ContextCompat.getColor(getApplicationContext(), R.color.tabUnselectedIconColor);
                        tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                        super.onTabReselected(tab);
                    }
                }
        );

        //selectPage(1);
        int tabIconColor = ContextCompat.getColor(getApplicationContext(), R.color.tabUnselectedIconColor);
        tabLayout.getTabAt(1).getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);

        /*TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
        //tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        final PagerAdapter padapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(padapter);*/
        //viewPager.setOffscreenPageLimit(3);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //do something you want
                tabLayout.getTabAt(0).getIcon().clearColorFilter();
                tabLayout.getTabAt(1).getIcon().clearColorFilter();
                finish();
            }
        });



        LayoutInflater inflater=null;
        View tempo = inflater.inflate(R.layout.listpasien, null);





        /*

        id_pasien=2
name2=Tyas Maheni
usia2=35
gender2=2
test1=Albumin
hargatestmedis1=55000
hargatestlab1=140000

        longitude_pasien=112.719995
latitude_pasien=-7.332976
longitude_lab=112.708280
latitude_lab=-7.337552

        id_pasien = 1
                name1=Sari Puspita Dewi
usia1=32
gender1=2
test1=Cholesterol Total
hargatestmedis1=42000
hargatestlab1=120000
test2=Kalium Urin
hargatestmedis2=35000
hargatestlab2=90000


invoice=INV/20160815/VXX/IV/005258
id_invoice=1

alamat=Jl. Pondok Mandar No. 18 RT 04/08 Kalibaru, Sukmajaya, Depok
id_lab=1
alamat_lab=Komplek Pearl Garden No. 3 Sawangan, Depok
nama_lab=Lab Hanan
id_member=1
nama_member=Eko Pranoto



                */


    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new OneFragment_expandable_withmap_v2(), "Order List");

        adapter.addFragment(new TwoFragment(), "Appointments");

        viewPager.setAdapter(adapter);

    }

    class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    private void setupTabIcons() {

        Drawable image = getApplicationContext().getResources().getDrawable( R.drawable.orderlist);
        int h = image.getIntrinsicHeight();
        int w = image.getIntrinsicWidth();
        image.setBounds( 0, 0, w, h );

        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabOne.setText("ONE");

        tabOne.setCompoundDrawables( image, null, null, null );
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabTwo.setText("TWO");
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.appointments, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);
    }


    @Override
    protected void onDestroy() {
        tabLayout.getTabAt(0).getIcon().clearColorFilter();
        tabLayout.getTabAt(1).getIcon().clearColorFilter();
        super.onDestroy();

    }

    @Override
    public void onBackPressed() {
        tabLayout.getTabAt(0).getIcon().clearColorFilter();
        tabLayout.getTabAt(1).getIcon().clearColorFilter();
        super.onBackPressed();
        this.finish();
    }

    void selectPage(int pageIndex){
        tabLayout.setScrollPosition(pageIndex,0f,true);
        viewPager.setCurrentItem(pageIndex);
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

}
