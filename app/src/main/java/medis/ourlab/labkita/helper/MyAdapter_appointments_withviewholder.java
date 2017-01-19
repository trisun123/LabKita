package medis.ourlab.labkita.helper;

/**
 * Created by tri on 11/1/2016.
 */


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import medis.ourlab.labkita.R;
import medis.ourlab.labkita.app.Config;
import medis.ourlab.labkita.app.MyApplication;
import medis.ourlab.labkita.model.Pasien;
import medis.ourlab.labkita.model.PasienList;

public class MyAdapter_appointments_withviewholder extends BaseAdapter  {
    //namae tampung;
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private ArrayList<HashMap<String, String>> filteredData;
private Boolean mapready=false;
    private Integer counterr=1;
    private List<PasienList> pasienList;

    private static LayoutInflater inflater=null;



    //public ImageLoader imageLoader;

    //public static final String MY_DATA = null;
    Context mContext;
    //tools.SessionManager session;
    /*public MyAdapter(Context context) {
        mContext = context;
    }*/


    public MyAdapter_appointments_withviewholder(Activity a, List<PasienList> pasienList) {
        /*activity = a;
        data=d;
        filteredData = d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);*/
        //imageLoader=new ImageLoader(activity.getApplicationContext());
        this.activity=a;
        mContext=a;
        this.pasienList=pasienList;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        //Log.e("traced","getcount : " + filteredData.size());


        //return filteredData.size();
        return pasienList.size();

    }

    @Override
    public Object getItem(int position) {
        //return filteredData.get(position);
        return pasienList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        /*if (inflater == null)
        inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);*/
        final PasienList mappingan = pasienList.get(position);
        final myViewHolder mholder;

        int kebawah=0;
        int screenwidthheight[]={0,0};
        float density=MyApplication.getInstance().getScreenDensity();
        MyApplication.getInstance().getScreenSizePixels(screenwidthheight);

        if (convertView == null || (myViewHolder) convertView.getTag()==null) {
            convertView = inflater.inflate(R.layout.my_adapter_appointments_item, parent,false);
            mholder = new myViewHolder();
            mholder.rl=(RelativeLayout) convertView.findViewById(R.id.pick_order);

            mholder.rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    MyApplication.getInstance().getPasienFromList().get(mholder.position).setMasih_order(false);
                    MyApplication.getInstance().setAllPasienListFalseForAdapter();

                    MyApplication.getInstance().setInvoice(mappingan.getInvoice());

                    //MyApplication.getInstance().getPasienFromList().remove(position);
                    //mappingan.setMasih_order(false);
                    Intent intent = new Intent("pick order diklik");
                    LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
                            /*if(mContext instanceof ListPasien_tabview){
                                LocalBroadcastManager.getInstance((ListPasien_tabview)mContext).sendBroadcast(intent);
                            }*/


                }
            });

            mholder.alamatpasien = (TextView) convertView.findViewById(R.id.alamatpasien);
            mholder.namalab = (TextView) convertView.findViewById(R.id.namalab);
            mholder.alamatlab = (TextView) convertView.findViewById(R.id.alamatlab);
            mholder.namamember = (TextView) convertView.findViewById(R.id.namamember);
            //mholder.invoice = (TextView) convertView.findViewById(R.id.invoice);

            /*mholder.inv_medcheck_subtotal = (TextView) convertView.findViewById(R.id.inv_medcheck_subtotal);
            mholder.inv_labtest_subtotal = (TextView) convertView.findViewById(R.id.inv_labtest_subtotal);
            mholder.inv_total = (TextView) convertView.findViewById(R.id.inv_total);*/

            mholder.pasien = (LinearLayout) convertView.findViewById(R.id.layoutpasien);

            //20170111
            mholder.pasien.setClipToPadding(false);
            mholder.pasien.setClipChildren(false);

            //20170109
            //mholder.framelayoutForIconRight = (FrameLayout) convertView.findViewById(R.id.appointmentsframelayout);
            //mholder.layoutacuanlebar = (LinearLayout) convertView.findViewById(R.id.layoutacuanlebar);

            /*mholder.inv_medcheck_items = (LinearLayout) convertView.findViewById(R.id.inv_medcheck_items);
            mholder.inv_labtest_items = (LinearLayout) convertView.findViewById(R.id.inv_labtest_items);*/

            //if(mappingan.getMasih_order()==true) { //20170103 supaya re-create
            if(true) {
                ArrayList<Pasien> pasien_ini=mappingan.getPasien();
                mholder.child = new View[pasien_ini.size()];
                mholder.namapasien = new TextView[pasien_ini.size()];
                mholder.usiadangender = new TextView[pasien_ini.size()];

                mholder.childiconright = new View[pasien_ini.size()];

                /*Integer jsize=pasien_ini.get(0).getTestsLab().size();
                for (Integer i = 0; i < pasien_ini.size(); i++) {
                    if(pasien_ini.get(i).getTestsLab().size()>jsize){
                        jsize=pasien_ini.get(i).getTestsLab().size();
                    }
                }*/

                Integer jsize=0;
                for (Integer i = 0; i < pasien_ini.size(); i++) {
                    if(pasien_ini.get(i).getTestsLab().size()>jsize){
                        jsize=pasien_ini.get(i).getTestsLab().size();
                    }
                }

                mholder.tests=new View[pasien_ini.size()][jsize];

                //20170109
                //mholder.iconright= new ImageView[pasien_ini.size()];

                //20170111
                //ViewGroup.MarginLayoutParams iconrightparams[] = new ViewGroup.MarginLayoutParams[pasien_ini.size()];

                /*mholder.inv_medcheck_items_view=new View[pasien_ini.size()][jsize];
                mholder.inv_labtest_items_view=new View[pasien_ini.size()][jsize];*/

                mholder.namatest=new TextView[pasien_ini.size()][jsize];

                /*mholder.inv_medcheck_namatest=new TextView[pasien_ini.size()][jsize];
                mholder.inv_medcheck_unit=new TextView[pasien_ini.size()][jsize];
                mholder.inv_medcheck_harga=new TextView[pasien_ini.size()][jsize];
                mholder.inv_labtest_namatest=new TextView[pasien_ini.size()][jsize];
                mholder.inv_labtest_unit=new TextView[pasien_ini.size()][jsize];
                mholder.inv_labtest_harga=new TextView[pasien_ini.size()][jsize];*/

                for (Integer i = 0; i < pasien_ini.size(); i++) {
                    mholder.child[i] = inflater.inflate(R.layout.pasien_item, mholder.pasien, false);

                    //20170109
                    //mholder.childiconright[i]=inflater.inflate(R.layout.iconright_items, mholder.framelayoutForIconRight, false);
                    //mholder.iconright[i] = (ImageView) mholder.childiconright[i].findViewById(R.id.iconright);
                    //iconrightparams[i] = (ViewGroup.MarginLayoutParams)mholder.iconright[i].getLayoutParams();
                    //iconrightparams.leftMargin = (int) (mholder.layoutacuanlebar.getMeasuredWidth() * Config.POSISI_ICON_RIGHT);
                    //iconrightparams.leftMargin = 20 + (20 * i);
                    //iconrightparams.leftMargin = 270;

                    //iconrightparams[i].leftMargin = (int) (screenwidthheight[0] * Config.POSISI_ICON_RIGHT - (Config.MARGIN_PASIEN*density));


                    mholder.namapasien[i] = (TextView) mholder.child[i].findViewById(R.id.namapasien);
                    mholder.usiadangender[i] = (TextView) mholder.child[i].findViewById(R.id.usiadangender);

                    //20170111
                    if(i>0) {
                        kebawah = kebawah + 18 +10;
                    }

                    for (Integer j = 0; j < pasien_ini.get(i).getTestsLab().size(); j++) {

                        //20170111
                        if(j>0){
                            kebawah=kebawah+0;

                        }

                        mholder.tests[i][j] = inflater.inflate(R.layout.pasien_tests_item, (LinearLayout) mholder.child[i], false);

                        ((LinearLayout) mholder.child[i]).addView(mholder.tests[i][j]);

                        /*mholder.inv_medcheck_items_view[i][j] = inflater.inflate(R.layout.invoice_medcheck_tests_item, mholder.inv_medcheck_items, false);
                        mholder.inv_labtest_items_view[i][j] = inflater.inflate(R.layout.invoice_medcheck_tests_item, mholder.inv_labtest_items, false);*/

                        mholder.namatest[i][j] = (TextView) mholder.tests[i][j].findViewById(R.id.namatest);

                        /*mholder.inv_medcheck_namatest[i][j] = (TextView) mholder.inv_medcheck_items_view[i][j].findViewById(R.id.inv_medcheck_namatest);
                        mholder.inv_medcheck_unit[i][j] = (TextView) mholder.inv_medcheck_items_view[i][j].findViewById(R.id.inv_medcheck_unit);
                        mholder.inv_medcheck_harga[i][j] = (TextView) mholder.inv_medcheck_items_view[i][j].findViewById(R.id.inv_medcheck_harga);

                        mholder.inv_labtest_namatest[i][j] = (TextView) mholder.inv_labtest_items_view[i][j].findViewById(R.id.inv_medcheck_namatest);
                        mholder.inv_labtest_unit[i][j] = (TextView) mholder.inv_labtest_items_view[i][j].findViewById(R.id.inv_medcheck_unit);
                        mholder.inv_labtest_harga[i][j] = (TextView) mholder.inv_labtest_items_view[i][j].findViewById(R.id.inv_medcheck_harga);

                        if (mappingan.getProcessed_by_adapter_header() == false) {
                            ((LinearLayout) mholder.inv_medcheck_items).addView(mholder.inv_medcheck_items_view[i][j]);
                            ((LinearLayout) mholder.inv_labtest_items).addView(mholder.inv_labtest_items_view[i][j]);
                        }*/
                    }

                    //20170111
                    if(i>0) {
                        //iconrightparams[i].topMargin = (int) (density * kebawah);
                    }

                    //if (mappingan.getProcessed_by_adapter_header() == false)
                        mholder.pasien.addView(mholder.child[i]);



                        //20170109
                        //mholder.framelayoutForIconRight.addView(mholder.childiconright[i]);


                }


            }

            //20170109
            //mholder.framelayoutForIconRight.bringToFront();
            convertView.setTag(mholder);
        }else{

            mholder = (myViewHolder) convertView.getTag();

            //20170103 re-create
            mholder.rl=(RelativeLayout) convertView.findViewById(R.id.pick_order);

            mholder.rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    MyApplication.getInstance().getPasienFromList().get(mholder.position).setMasih_order(false);
                    MyApplication.getInstance().setAllPasienListFalseForAdapter();

                    MyApplication.getInstance().setInvoice(mappingan.getInvoice());

                    //MyApplication.getInstance().getPasienFromList().remove(position);
                    //mappingan.setMasih_order(false);
                    Intent intent = new Intent("pick order diklik");
                    LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
                            /*if(mContext instanceof ListPasien_tabview){
                                LocalBroadcastManager.getInstance((ListPasien_tabview)mContext).sendBroadcast(intent);
                            }*/


                }
            });

            mholder.alamatpasien = (TextView) convertView.findViewById(R.id.alamatpasien);
            mholder.namalab = (TextView) convertView.findViewById(R.id.namalab);
            mholder.alamatlab = (TextView) convertView.findViewById(R.id.alamatlab);
            mholder.namamember = (TextView) convertView.findViewById(R.id.namamember);
            //mholder.invoice = (TextView) convertView.findViewById(R.id.invoice);

            /*mholder.inv_medcheck_subtotal = (TextView) convertView.findViewById(R.id.inv_medcheck_subtotal);
            mholder.inv_labtest_subtotal = (TextView) convertView.findViewById(R.id.inv_labtest_subtotal);
            mholder.inv_total = (TextView) convertView.findViewById(R.id.inv_total);*/

            mholder.pasien = (LinearLayout) convertView.findViewById(R.id.layoutpasien);

            //20170111
            mholder.pasien.setClipToPadding(false);
            mholder.pasien.setClipChildren(false);

            //20170109
            //mholder.framelayoutForIconRight = (FrameLayout) convertView.findViewById(R.id.appointmentsframelayout);
            //mholder.layoutacuanlebar = (LinearLayout) convertView.findViewById(R.id.layoutacuanlebar);

            /*mholder.inv_medcheck_items = (LinearLayout) convertView.findViewById(R.id.inv_medcheck_items);
            mholder.inv_labtest_items = (LinearLayout) convertView.findViewById(R.id.inv_labtest_items);*/

            //if(mappingan.getMasih_order()==true) { //20170103 supaya re-create
            if(true) {
                ArrayList<Pasien> pasien_ini=mappingan.getPasien();
                mholder.child = new View[pasien_ini.size()];
                mholder.namapasien = new TextView[pasien_ini.size()];
                mholder.usiadangender = new TextView[pasien_ini.size()];

                //mholder.childiconright = new View[pasien_ini.size()];

                /*Integer jsize=pasien_ini.get(0).getTestsLab().size();
                for (Integer i = 0; i < pasien_ini.size(); i++) {
                    if(pasien_ini.get(i).getTestsLab().size()>jsize){
                        jsize=pasien_ini.get(i).getTestsLab().size();
                    }
                }*/

                Integer jsize=0;
                for (Integer i = 0; i < pasien_ini.size(); i++) {
                    if(pasien_ini.get(i).getTestsLab().size()>jsize){
                        jsize=pasien_ini.get(i).getTestsLab().size();
                    }
                }

                mholder.tests=new View[pasien_ini.size()][jsize];

                //20170109
                //mholder.iconright= new ImageView[pasien_ini.size()];

                //20170111
                //ViewGroup.MarginLayoutParams iconrightparams[] = new ViewGroup.MarginLayoutParams[pasien_ini.size()];

                /*mholder.inv_medcheck_items_view=new View[pasien_ini.size()][jsize];
                mholder.inv_labtest_items_view=new View[pasien_ini.size()][jsize];*/

                mholder.namatest=new TextView[pasien_ini.size()][jsize];

                /*mholder.inv_medcheck_namatest=new TextView[pasien_ini.size()][jsize];
                mholder.inv_medcheck_unit=new TextView[pasien_ini.size()][jsize];
                mholder.inv_medcheck_harga=new TextView[pasien_ini.size()][jsize];
                mholder.inv_labtest_namatest=new TextView[pasien_ini.size()][jsize];
                mholder.inv_labtest_unit=new TextView[pasien_ini.size()][jsize];
                mholder.inv_labtest_harga=new TextView[pasien_ini.size()][jsize];*/

                //20170103 remove dulu semua
                try {
                    mholder.pasien.removeAllViews();
                    mholder.pasien.invalidate();
                        /*if (mappingan.getProcessed_by_adapter_header() == false) {
                            for (Integer l = 0; l < holder.pasien.getChildCount(); l++) {
                                //holder.pasien.removeViewAt(l);
                            }
                        }*/
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //20170109
                try {
                    mholder.framelayoutForIconRight.removeAllViews();
                    mholder.framelayoutForIconRight.invalidate();
                        /*if (mappingan.getProcessed_by_adapter_header() == false) {
                            for (Integer l = 0; l < holder.pasien.getChildCount(); l++) {
                                //holder.pasien.removeViewAt(l);
                            }
                        }*/
                } catch (Exception e) {
                    e.printStackTrace();
                }

                for (Integer i = 0; i < pasien_ini.size(); i++) {
                    mholder.child[i] = inflater.inflate(R.layout.pasien_item, mholder.pasien, false);
                    mholder.namapasien[i] = (TextView) mholder.child[i].findViewById(R.id.namapasien);
                    mholder.usiadangender[i] = (TextView) mholder.child[i].findViewById(R.id.usiadangender);


                    for (Integer j = 0; j < pasien_ini.get(i).getTestsLab().size(); j++) {


                        mholder.tests[i][j] = inflater.inflate(R.layout.pasien_tests_item, (LinearLayout) mholder.child[i], false);

                        ((LinearLayout) mholder.child[i]).addView(mholder.tests[i][j]);

                        /*mholder.inv_medcheck_items_view[i][j] = inflater.inflate(R.layout.invoice_medcheck_tests_item, mholder.inv_medcheck_items, false);
                        mholder.inv_labtest_items_view[i][j] = inflater.inflate(R.layout.invoice_medcheck_tests_item, mholder.inv_labtest_items, false);*/

                        mholder.namatest[i][j] = (TextView) mholder.tests[i][j].findViewById(R.id.namatest);

                        /*mholder.inv_medcheck_namatest[i][j] = (TextView) mholder.inv_medcheck_items_view[i][j].findViewById(R.id.inv_medcheck_namatest);
                        mholder.inv_medcheck_unit[i][j] = (TextView) mholder.inv_medcheck_items_view[i][j].findViewById(R.id.inv_medcheck_unit);
                        mholder.inv_medcheck_harga[i][j] = (TextView) mholder.inv_medcheck_items_view[i][j].findViewById(R.id.inv_medcheck_harga);

                        mholder.inv_labtest_namatest[i][j] = (TextView) mholder.inv_labtest_items_view[i][j].findViewById(R.id.inv_medcheck_namatest);
                        mholder.inv_labtest_unit[i][j] = (TextView) mholder.inv_labtest_items_view[i][j].findViewById(R.id.inv_medcheck_unit);
                        mholder.inv_labtest_harga[i][j] = (TextView) mholder.inv_labtest_items_view[i][j].findViewById(R.id.inv_medcheck_harga);

                        if (mappingan.getProcessed_by_adapter_header() == false) {
                            ((LinearLayout) mholder.inv_medcheck_items).addView(mholder.inv_medcheck_items_view[i][j]);
                            ((LinearLayout) mholder.inv_labtest_items).addView(mholder.inv_labtest_items_view[i][j]);
                        }*/
                    }
                    //if (mappingan.getProcessed_by_adapter_header() == false)
                    mholder.pasien.addView(mholder.child[i]);

                    //20170109
                    //mholder.framelayoutForIconRight.addView(mholder.childiconright[i]);
                }


            }

            //20170109
            //mholder.framelayoutForIconRight.bringToFront();
            convertView.setTag(mholder);

        }

        mholder.position=position;

            if(mappingan.getMasih_order()==true) {

                if (mappingan.getMasih_order()==true) {

                    //try {
                    //RelativeLayout rl = (RelativeLayout) convertView.findViewById(R.id.pick_order);





                    mholder.alamatpasien.setText(mappingan.getAlamat_pasien());
                    mholder.namalab.setText(mappingan.getNama_lab());
                    mholder.alamatlab.setText(mappingan.getAlamat_lab());
                    mholder.namamember.setText(mappingan.getNama_member());
                    //mholder.invoice.setText(mappingan.getInvoice());



                    ArrayList<Pasien> pasien_ini=mappingan.getPasien();


                    /*Double inv_medcheck_subtotal_num = 0.0;
                    Double inv_labtest_subtotal_num = 0.0;*/

                    for (Integer i = 0; i < pasien_ini.size(); i++) {


                        mholder.namapasien[i].setText(pasien_ini.get(i).getName());
                        String gender = "";
                        if (pasien_ini.get(i).getGender() == 1) {
                            gender = "Male";
                        } else {
                            gender = "Female";
                        }
                        mholder.usiadangender[i].setText(gender + ", " + Integer.toString(pasien_ini.get(i).getUsiaPasien()) + " years old");

                        for (Integer j = 0; j < pasien_ini.get(i).getTestsLab().size(); j++) {

                            mholder.namatest[i][j].setText("\u2022" + pasien_ini.get(i).getTestsLab().get(j).getName());


                            //((LinearLayout) mholder.child[i]).addView(mholder.tests[i][j]);



                            /*mholder.inv_medcheck_namatest[i][j].setText(pasien_ini.get(i).getTestsLab().get(j).getName());
                            mholder.inv_medcheck_unit[i][j].setText("1");

                            mholder.inv_labtest_namatest[i][j].setText(pasien_ini.get(i).getTestsLab().get(j).getName());
                            mholder.inv_labtest_unit[i][j].setText("1");

                            //inv_medcheck_harga.setText("IDR " + Double.toString(pasien_ini.get(i).getTestsLab().get(j).getHargaMedis()));
                            //inv_medcheck_harga.setText("IDR " + String.format("%,.2f",pasien_ini.get(i).getTestsLab().get(j).getHargaMedis()));
                            mholder.inv_medcheck_harga[i][j].setText("IDR " + String.format(Locale.GERMAN, "%,.0f", pasien_ini.get(i).getTestsLab().get(j).getHargaMedis()));
                            mholder.inv_labtest_harga[i][j].setText("IDR " + String.format(Locale.GERMAN, "%,.0f", pasien_ini.get(i).getTestsLab().get(j).getHargaLab()));

                            inv_medcheck_subtotal_num = inv_medcheck_subtotal_num + pasien_ini.get(i).getTestsLab().get(j).getHargaMedis();
                            inv_labtest_subtotal_num = inv_labtest_subtotal_num + pasien_ini.get(i).getTestsLab().get(j).getHargaLab();

                            if (mappingan.getProcessed_by_adapter_header() == false) {
                                ((LinearLayout) mholder.inv_medcheck_items).addView(mholder.inv_medcheck_items_view[i][j]);
                                ((LinearLayout) mholder.inv_labtest_items).addView(mholder.inv_labtest_items_view[i][j]);
                            }*/
                        }

                            /*if (mappingan.getProcessed_by_adapter_header() == false)
                                mholder.pasien.addView(mholder.child[i]);*/

                    }
                    /*mholder.inv_medcheck_subtotal.setText("IDR " + String.format(Locale.GERMAN, "%,.0f", inv_medcheck_subtotal_num));
                    mholder.inv_labtest_subtotal.setText("IDR " + String.format(Locale.GERMAN, "%,.0f", inv_labtest_subtotal_num));
                    mholder.inv_total.setText("IDR " + String.format(Locale.GERMAN, "%,.0f", inv_medcheck_subtotal_num + inv_labtest_subtotal_num));*/
        /*pasien.invalidate();
        vi.invalidate();*/

                    mappingan.setProcessed_by_adapter_header(true);



                    //counterr++;
            /*} catch (Exception e) {
                //e.printStackTrace();

            }*/
                }
            }else{
                convertView = inflater.inflate(R.layout.kosong, parent,false);
            }


            //vi = inflater.inflate(R.layout.my_adapter_appointments_item, null);





        return convertView;
    }
    /*public Filter getFilter() {
        // TODO Auto-generated method stub
        return new Filter()
        {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence)
            {
                FilterResults results = new FilterResults();

                //If there's nothing to filter on, return the original data for your list
                if(charSequence == null || charSequence.length() == 0)
                {
                    results.values = data;
                    results.count = data.size();
                }
                else
                {
                    ArrayList<HashMap<String,String>> filterResultsData = new ArrayList<HashMap<String,String>>();

                    for(HashMap<String,String> datax : data)
                    {
                        //In this loop, you'll filter through originalData and compare each item to charSequence.
                        //If you find a match, add it to your new ArrayList
                        //I'm not sure how you're going to do comparison, so you'll need to fill out this conditional


                        String kk=datax.get("namapasien").toLowerCase();
                        if(kk.contains(charSequence.toString().toLowerCase()))
                        //kk.substring(0,charSequence.length()).equals(charSequence.toString().toLowerCase());
                        //if(kk.startsWith(charSequence.toString().toLowerCase()))
                        //if(kk.substring(0,charSequence.length()).equals(charSequence.toString().toLowerCase()))
                        {
                            filterResultsData.add(datax);
                        }

                    }

                    results.values = filterResultsData;
                    results.count = filterResultsData.size();
                    //Log.e("traced","jumlah terfilter : " + String.valueOf(results.count));
                }

                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults)
            {
                filteredData = (ArrayList<HashMap<String,String>>)filterResults.values;
                notifyDataSetChanged();
            }
        };
    }*/

    static class myViewHolder {
        RelativeLayout rl;
        TextView alamatpasien;
        TextView namalab;
        TextView alamatlab;
        TextView namamember;
        TextView invoice;
        TextView inv_medcheck_subtotal;
        TextView inv_labtest_subtotal;
        TextView inv_total;
        LinearLayout pasien;
        LinearLayout inv_medcheck_items;
        LinearLayout inv_labtest_items;
        TextView namapasien[];
        TextView usiadangender[];
        View tests[][];
        View inv_medcheck_items_view[][];
        View inv_labtest_items_view[][];
        TextView namatest[][];
        TextView inv_medcheck_namatest[][];
        TextView inv_medcheck_unit[][];
        TextView inv_medcheck_harga[][];
        TextView inv_labtest_namatest[][];
        TextView inv_labtest_unit[][];
        TextView inv_labtest_harga[][];
        View child[];
        Integer position;

        FrameLayout framelayoutForIconRight;
        ImageView iconright[];
        View childiconright[];

        LinearLayout layoutacuanlebar;
    }

}