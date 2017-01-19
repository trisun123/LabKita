package medis.ourlab.labkita.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import medis.ourlab.labkita.R;
import medis.ourlab.labkita.app.MyApplication;
import medis.ourlab.labkita.helper.MyAdapter;
import medis.ourlab.labkita.model.Pasien;
import medis.ourlab.labkita.model.PasienList;
import medis.ourlab.labkita.model.Tests_lab;

/**
 * Created by tri on 11/1/2016.
 */

public class ListPasien extends ActionBarActivity {
    ListView mylistview;
    ArrayList<PasienList> pasienList;
    MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pasienList=new ArrayList<PasienList>();


        Boolean sudahberisi = false;



        try {
            if (MyApplication.getInstance().getPasienListCount() > 0) {
                sudahberisi = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (sudahberisi == false) {
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
            pasienlist.setLongitude_pasien(112.719995);
            pasienlist.setLatitude_pasien(-7.332976);
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

        setContentView(R.layout.listpasien);
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
toolbar.setTitle(R.string.order_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //do something you want
                finish();
            }
        });

        mylistview = (ListView) findViewById(R.id.list);


        try {
            if (MyApplication.getInstance().getPasienListCount() > 0) {

                Toast.makeText(this, "jumlah order : " + Integer.toString(MyApplication.getInstance().getPasienListCount()), Toast.LENGTH_SHORT).show();
                    pasienList= MyApplication.getInstance().getPasienFromList();
                    MyApplication.getInstance().setAllPasienListFalseForAdapter();
                    adapter=new MyAdapter(this,pasienList);
                    mylistview.setAdapter(adapter);
                    //adapter.notifyDataSetChanged();
            }

        } catch (Exception e) {
            //e.printStackTrace();

        }

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
}
