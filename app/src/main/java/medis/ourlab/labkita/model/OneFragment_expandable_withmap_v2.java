package medis.ourlab.labkita.model;

/**
 * Created by tri on 11/8/2016.
 */
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

import medis.ourlab.labkita.R;
import medis.ourlab.labkita.app.MyApplication;
import medis.ourlab.labkita.helper.SQLiteHandler;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;


public class OneFragment_expandable_withmap_v2 extends Fragment{
    HashSet<MapView> mMaps = new HashSet<MapView>();
    MapView mapView;
    GoogleMap map;
    ArrayList<PasienList> pasienList;
    static Context mContext;
    myAdapterOrderExpandableWithmap adapter;
    ExpandableListView mylistview;
    public OneFragment_expandable_withmap_v2() {
        // Required empty public constructor
    }
    SQLiteHandler db;
    //PopupWindow mPopupWindow;
    RelativeLayout mRelativeLayout;
    //TextView jarakpasien, jaraklab;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=getContext();
        db = new SQLiteHandler(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_one_expandable, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        View view = getView();
        LocalBroadcastManager.getInstance(mContext).registerReceiver(mMessageReceiver,
                new IntentFilter("pick order diklik"));
        if(view != null) {
            //view.findViewById(R.id.my_button).setOnClickListener(new MyClickListener());
            try {
                mylistview= (ExpandableListView) view.findViewById(R.id.list);




                if (MyApplication.getInstance().getPasienListCount() > 0) {


                    //Toast.makeText(getContext(), "jumlah order : " + Integer.toString(MyApplication.getInstance().getPasienListCount()), Toast.LENGTH_SHORT).show();
                    pasienList= MyApplication.getInstance().getPasienFromList();
                    MyApplication.getInstance().setAllPasienListFalseForAdapter();
                    adapter=new myAdapterOrderExpandableWithmap(getActivity(),pasienList);
                    mylistview.setAdapter(adapter);

                    //ini ga berfungsi
                    /*mylistview.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                    @Override
                    public boolean onGroupClick(ExpandableListView parent, View v,
                    int groupPosition, long id) {
                        //TextView judul=(TextView) v.findViewById(R.id.pickorder_judul);
                        if(parent.isGroupExpanded(groupPosition))
                        {
                            //judul.setText("isGroupExpanded");
                            // Do your Staff
                            Toast.makeText(mContext, "expanded", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            //judul.setText("isGroupCollapsed");
                            // Expanded ,Do your Staff
                            Toast.makeText(mContext, "collapsed", Toast.LENGTH_SHORT).show();
                        }


                        return false;
                    }
                }

                );*/




                    DisplayMetrics metrics = new DisplayMetrics();
                    WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
                    windowManager.getDefaultDisplay().getMetrics(metrics);
                    int width = metrics.widthPixels;
                    if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2) {
                        mylistview.setIndicatorBounds(width - GetPixelFromDips(70), width - GetPixelFromDips(10));

                        //searchExpListView.setIndicatorBounds(width - GetPixelFromDips(50), width - GetPixelFromDips(10));
                    } else {
                        mylistview.setIndicatorBoundsRelative(width - GetPixelFromDips(70), width - GetPixelFromDips(10));
                        //searchExpListView.setIndicatorBoundsRelative(width - GetPixelFromDips(50), width - GetPixelFromDips(10));
                    }
                    //adapter.notifyDataSetChanged();

                    //ini bisa yang pertama saja, yang lain FC
                    /*mylistview.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

                        @Override
                        public void onGroupExpand(int groupPosition) {
                            //TextView judul= (TextView)mylistview.getSelectedView().findViewById(R.id.judul_pickorder);
                            //mylistview.getChildAt(groupPosition).


                            //mylistview.getChildCount();
                            RelativeLayout rl=(RelativeLayout) mylistview.getChildAt(groupPosition).findViewById(R.id.layout_judul_pickorder); //cuma bisa di yang pertama doank

                            rl.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.C1));

                            TextView judul= (TextView)mylistview.getChildAt(groupPosition).findViewById(R.id.judul_pickorder);
                            judul.setText("GOT THE ORDER");
                            judul.setTextColor(ContextCompat.getColor(getContext(),R.color.C2));
                            Toast.makeText(mContext,
                                    " List Expanded." + Integer.toString(mylistview.getChildCount()),
                                    Toast.LENGTH_SHORT).show();
                        }
                    });

                    mylistview.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

                        @Override
                        public void onGroupCollapse(int groupPosition) {
                            *//*RelativeLayout rl=(RelativeLayout) mylistview.getChildAt(groupPosition).findViewById(R.id.layout_judul_pickorder);
                            rl.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.C2));

                            TextView judul= (TextView)mylistview.getChildAt(groupPosition).findViewById(R.id.judul_pickorder);
                            judul.setText("PICK ORDER");
                            judul.setTextColor(ContextCompat.getColor(getContext(),R.color.white));*//*
                            Toast.makeText(mContext,
                                    " List Collapsed." + Integer.toString(mylistview.getChildCount()),
                                    Toast.LENGTH_SHORT).show();
                        }
                    });*/

                }

            } catch (Exception e) {
                //e.printStackTrace();

            }
        }

        /*AbsListView lv = mylistview;
        lv.setRecyclerListener(mRecycleListener);*/
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            String message = intent.getStringExtra("message");

            //adapter.notifyDataSetChanged(); //gak iso
            View view = getView();
            if(view!=null) {
                ExpandableListView xx = (ExpandableListView) view.findViewById(R.id.list);
                //((BaseAdapter)xx.getAdapter()).notifyDataSetChanged();
                //MyApplication.getInstance().setAllPasienListFalseForAdapter();

                xx.setAdapter(new myAdapterOrderExpandableWithmap(getActivity(), MyApplication.getInstance().getPasienFromList()));
            }
        }
    };

    public int GetPixelFromDips(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }

    public class myAdapterOrderExpandableWithmap extends BaseExpandableListAdapter  {
        //namae tampung;

        private Activity activity;
        private ArrayList<HashMap<String, String>> data;
        private ArrayList<HashMap<String, String>> filteredData;
        private Boolean mapready=false;
        private List<PasienList> pasienList;

        private LayoutInflater inflater=null;



        //public ImageLoader imageLoader;

        //public static final String MY_DATA = null;
        //Context mContext;
        //tools.SessionManager session;
    /*public MyAdapter(Context context) {
        mContext = context;
    }*/

        public myAdapterOrderExpandableWithmap(Context context, List<PasienList> pasienList) {
        /*activity = a;
        data=d;
        filteredData = d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);*/
            //imageLoader=new ImageLoader(activity.getApplicationContext());
            mContext=context;
            //mContext=context;
            this.pasienList=pasienList;
            inflater = (LayoutInflater) mContext
                    .getSystemService(LAYOUT_INFLATER_SERVICE);
        }






        @Override
        public int getGroupCount() {


            return pasienList.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return 1; //always 1
        }

        @Override
        public Object getGroup(int groupPosition) {
            return pasienList.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return null;
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }



        @Override
        public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            final ViewHolder holder;
            //PasienList mappingan = pasienList.get(groupPosition);
            PasienList mappingan = MyApplication.getInstance().getPasienFromList().get(groupPosition);


            //20170105
            parent.setClipChildren(false);
            parent.setClipToPadding(false);


            if (convertView == null || (ViewHolder) convertView.getTag()==null) {
            //if (convertView == null) {
            //if (true) {

                convertView = inflater.inflate(R.layout.my_adapter_item_header_withmap, parent,false);



                holder=new ViewHolder(new MapView(mContext));
                //mapView = (MapView) convertView.findViewById(R.id.lite_listrow_map);


                holder.mapView2 = (MapView) convertView.findViewById(R.id.lite_listrow_map);
                holder.mapView2.setFocusable(false);


                holder.rl=(RelativeLayout) convertView.findViewById(R.id.layout_judul_pickorder);
                holder.judul= (TextView)convertView.findViewById(R.id.judul_pickorder);
                holder.waktu = (TextView) convertView.findViewById(R.id.waktu_orderlist);
                holder.remainingwaktu = (TextView) convertView.findViewById(R.id.remainingwaktu_orderlist);
                holder.jarakpasien = (TextView) convertView.findViewById(R.id.jarakpasien_orderlist);
                holder.jaraklab = (TextView) convertView.findViewById(R.id.jaraklab_orderlist);
                holder.alamatpasien = (TextView) convertView.findViewById(R.id.alamatpasien);
                holder.namalab = (TextView) convertView.findViewById(R.id.namalab);
                holder.alamatlab = (TextView) convertView.findViewById(R.id.alamatlab);
                holder.namamember = (TextView) convertView.findViewById(R.id.namamember);
                holder.pasien = (LinearLayout) convertView.findViewById(R.id.layoutpasien);

                //20170105
                holder.pasien.setClipChildren(false);
                holder.pasien.setClipToPadding(false);

                ArrayList<Pasien> pasien_ini = mappingan.getPasien();
                holder.ukuran=pasien_ini.size();

                holder.child = new View[pasien_ini.size()];
                holder.namapasien = new TextView[pasien_ini.size()];
                holder.usiadangender = new TextView[pasien_ini.size()];

                //20170105
                /*holder.holderpasien=new RelativeLayout[pasien_ini.size()];
                holder.iconpasien=new ImageView[pasien_ini.size()];*/


                Integer jsize=0;

                for (Integer i = 0; i < pasien_ini.size(); i++) {
                    if(pasien_ini.get(i).getTestsLab().size()>jsize){
                        jsize=pasien_ini.get(i).getTestsLab().size();
                    }
                }

                holder.tests = new View[pasien_ini.size()][jsize];
                holder.namatest = new TextView[pasien_ini.size()][jsize];

                for (Integer i = 0; i < pasien_ini.size(); i++) {
                    holder.child[i] = inflater.inflate(R.layout.pasien_item, holder.pasien, false);

                    //20170105
                    ((LinearLayout) holder.child[i]).setClipChildren(false);
                    ((LinearLayout) holder.child[i]).setClipToPadding(false);


                    holder.namapasien[i] = (TextView) holder.child[i].findViewById(R.id.namapasien);

                    //20170105
                    /*holder.holderpasien[i]= (LinearLayout) holder.child[i].findViewById(R.id.holderpasien);
                    holder.iconpasien[i]=(ImageView) holder.child[i].findViewById(R.id.iconpasien);*/

                    holder.usiadangender[i] = (TextView) holder.child[i].findViewById(R.id.usiadangender);
                    for (Integer j = 0; j < pasien_ini.get(i).getTestsLab().size(); j++) {
                        holder.tests[i][j] = inflater.inflate(R.layout.pasien_tests_item, (LinearLayout) holder.child[i], false);
                        holder.namatest[i][j] = (TextView) holder.tests[i][j].findViewById(R.id.namatest);
                        ((LinearLayout) holder.child[i]).addView(holder.tests[i][j]);
                    }
                    if (mappingan.getProcessed_by_adapter_header() == false) {
                        holder.pasien.addView(holder.child[i]);
                    }
                }



                convertView.setTag(holder);

                holder.initializeMapView();
            }else{

                holder = (ViewHolder) convertView.getTag();

                //20170101 butuh recreate sesuai jumlah pasien
                //if(holder.ukuran != mappingan.getPasien().size()){
                if(true){
                    //holder=new ViewHolder(new MapView(mContext));
                    //mapView = (MapView) convertView.findViewById(R.id.lite_listrow_map);


                    holder.mapView2 = (MapView) convertView.findViewById(R.id.lite_listrow_map);
                    holder.mapView2.setFocusable(false);


                    holder.rl=(RelativeLayout) convertView.findViewById(R.id.layout_judul_pickorder);
                    holder.judul= (TextView)convertView.findViewById(R.id.judul_pickorder);
                    holder.waktu = (TextView) convertView.findViewById(R.id.waktu_orderlist);
                    holder.remainingwaktu = (TextView) convertView.findViewById(R.id.remainingwaktu_orderlist);
                    holder.jarakpasien = (TextView) convertView.findViewById(R.id.jarakpasien_orderlist);
                    holder.jaraklab = (TextView) convertView.findViewById(R.id.jaraklab_orderlist);
                    holder.alamatpasien = (TextView) convertView.findViewById(R.id.alamatpasien);
                    holder.namalab = (TextView) convertView.findViewById(R.id.namalab);
                    holder.alamatlab = (TextView) convertView.findViewById(R.id.alamatlab);
                    holder.namamember = (TextView) convertView.findViewById(R.id.namamember);
                    holder.pasien = (LinearLayout) convertView.findViewById(R.id.layoutpasien);

                    //20170105
                    holder.pasien.setClipChildren(false);
                    holder.pasien.setClipToPadding(false);

                    ArrayList<Pasien> pasien_ini = mappingan.getPasien();
                    holder.ukuran=pasien_ini.size();

                    holder.child = new View[pasien_ini.size()];
                    holder.namapasien = new TextView[pasien_ini.size()];
                    holder.usiadangender = new TextView[pasien_ini.size()];


                    Integer jsize=0;

                    for (Integer i = 0; i < pasien_ini.size(); i++) {
                        if(pasien_ini.get(i).getTestsLab().size()>jsize){
                            jsize=pasien_ini.get(i).getTestsLab().size();
                        }
                    }

                    holder.tests = new View[pasien_ini.size()][jsize];
                    holder.namatest = new TextView[pasien_ini.size()][jsize];


                    //20170102 remove dulu semua
                    try {
                        holder.pasien.removeAllViews();
                        holder.pasien.invalidate();
                        /*if (mappingan.getProcessed_by_adapter_header() == false) {
                            for (Integer l = 0; l < holder.pasien.getChildCount(); l++) {
                                //holder.pasien.removeViewAt(l);
                            }
                        }*/
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    for (Integer i = 0; i < pasien_ini.size(); i++) {
                        holder.child[i] = inflater.inflate(R.layout.pasien_item, holder.pasien, false);

                        //20170105
                        ((LinearLayout) holder.child[i]).setClipChildren(false);
                        ((LinearLayout) holder.child[i]).setClipToPadding(false);


                        //20170102 remove dulu semua
                        try {
                            /*((LinearLayout) holder.child[i]).removeAllViews();
                            ((LinearLayout) holder.child[i]).invalidate();*/
                            //((LinearLayout) holder.child[i]).removeAllViewsInLayout();
                            /*for(Integer k=0;k<((LinearLayout) holder.child[i]).getChildCount();k++){
                                //((LinearLayout) holder.child[i]).removeViewAt(k);
                            }*/
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                        holder.namapasien[i] = (TextView) holder.child[i].findViewById(R.id.namapasien);
                        holder.usiadangender[i] = (TextView) holder.child[i].findViewById(R.id.usiadangender);



                        for (Integer j = 0; j < pasien_ini.get(i).getTestsLab().size(); j++) {
                            holder.tests[i][j] = inflater.inflate(R.layout.pasien_tests_item, (LinearLayout) holder.child[i], false);
                            holder.namatest[i][j] = (TextView) holder.tests[i][j].findViewById(R.id.namatest);

                            //20170102 remove itself ?
                            try {
                                //((ViewManager)holder.tests[i][j].getParent()).removeView(holder.tests[i][j]);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            ((LinearLayout) holder.child[i]).addView(holder.tests[i][j]);
                        }

                        if (mappingan.getProcessed_by_adapter_header() == false) {

                            //20170102 remove itself ?
                            try {
                                //((ViewManager)holder.child[i].getParent()).removeView(holder.child[i]);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            //holder.pasien.addView(holder.child[i]); //20170102 pindah ke luar dari cek
                        }
                        //20170102 pindah ke luar dari cek
                        holder.pasien.addView(holder.child[i]);
                    }



                    convertView.setTag(holder);

                }






            }



                if(mappingan.getMasih_order()==false && !MyApplication.punyaMinus(MyApplication.remainingWaktu(mappingan.getWaktu())) ) {


                    if (mappingan.getMasih_order()==false) {




                        Location loc_pasien =new Location("");
                        loc_pasien.setLatitude(mappingan.getLatitude_pasien());
                        loc_pasien.setLongitude(mappingan.getLongitude_pasien());
                        Location loc_lab =new Location("");
                        loc_lab.setLatitude(mappingan.getLatitude_lab());
                        loc_lab.setLongitude(mappingan.getLongitude_lab());

                        try {
                            Location loc_aku =new Location("");
                            loc_aku.setLatitude(MyApplication.getInstance().getLat());
                            loc_aku.setLongitude(MyApplication.getInstance().getLon());
                            float jarak_pasien = loc_pasien.distanceTo(loc_aku);
                            float jarak_lab = loc_lab.distanceTo(loc_aku);
                            String jarak_pasien_str,jarak_lab_str;
                            if(jarak_pasien>=1000){
                                jarak_pasien=jarak_pasien/1000;
                                jarak_pasien_str= Float.toString(jarak_pasien) + " km";
                            }else{
                                jarak_pasien_str= Float.toString(jarak_pasien) + " m";
                            }
                            if(jarak_lab>=1000){
                                jarak_lab=jarak_lab/1000;
                                jarak_lab_str= Float.toString(jarak_lab) + " km";
                            }else{
                                jarak_lab_str= Float.toString(jarak_lab) + " m";
                            }
                            String tmp="";
                            if(jarak_pasien_str.contains(".")){
                                tmp= jarak_pasien_str.substring(0,jarak_pasien_str.indexOf(".")+2);
                                if(jarak_pasien_str.contains("km")){
                                    tmp=tmp + " km";
                                }else{
                                    tmp=tmp + " m";
                                }
                                jarak_pasien_str=tmp;
                            }
                            if(jarak_lab_str.contains(".")){
                                tmp= jarak_lab_str.substring(0,jarak_lab_str.indexOf(".")+2);
                                if(jarak_lab_str.contains("km")){
                                    tmp=tmp + " km";
                                }else{
                                    tmp=tmp + " m";
                                }
                                jarak_lab_str=tmp;
                            }

                            holder.jarakpasien.setText("Distance to patient (direct line) : " + jarak_pasien_str);
                            holder.jaraklab.setText("Distance to Lab (direct line) : " + jarak_lab_str);
                        } catch (Exception e) {
                            holder.jarakpasien.setText("Distance to patient (direct line) : unable to get my coordinate");
                            holder.jaraklab.setText("Distance to Lab (direct line) : unable to get my coordinate");
                        }

                        //SimpleDateFormat sdf1 = new SimpleDateFormat("MMM dd,yyyy HH:mm"); //Jan 10, 2017 20:00
                        SimpleDateFormat sdf1 = new SimpleDateFormat("E, MMM dd, yyyy hh:mm a"); //Tue, Jan 10, 2017 08:00 PM
                        SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd-yyyy HH:mm");
                        try {
                            holder.waktu.setText(sdf1.format(sdf2.parse(mappingan.getWaktu())));
                        } catch (ParseException e) {
                            holder.waktu.setText(mappingan.getWaktu());
                        }

                        holder.remainingwaktu.setText(MyApplication.remainingWaktu(mappingan.getWaktu()));

                        if(isExpanded){
                            holder.rl.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.C1));
                            holder.judul.setText("GOT THE ORDER");
                            holder.judul.setTextColor(ContextCompat.getColor(getContext(),R.color.C2));
                        }else{
                            holder.rl.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.C2));
                            holder.judul.setText("PICK ORDER");
                            holder.judul.setTextColor(ContextCompat.getColor(getContext(),R.color.white));
                        }


                        //TextView invoice = (TextView) convertView.findViewById(R.id.invoice);

                    /*TextView inv_medcheck_subtotal = (TextView) convertView.findViewById(R.id.inv_medcheck_subtotal);
                    TextView inv_labtest_subtotal = (TextView) convertView.findViewById(R.id.inv_labtest_subtotal);
                    TextView inv_total = (TextView) convertView.findViewById(R.id.inv_total);*/

                        holder.alamatpasien.setText(mappingan.getAlamat_pasien());
                        holder.namalab.setText(mappingan.getNama_lab());
                        holder.alamatlab.setText(mappingan.getAlamat_lab());
                        holder.namamember.setText(mappingan.getNama_member());
                        //invoice.setText(mappingan.getInvoice());


                    /*LinearLayout inv_medcheck_items = (LinearLayout) convertView.findViewById(R.id.inv_medcheck_items);
                    LinearLayout inv_labtest_items = (LinearLayout) convertView.findViewById(R.id.inv_labtest_items);*/

                        ArrayList<Pasien> pasien_ini = mappingan.getPasien();



                    /*Double inv_medcheck_subtotal_num = 0.0;
                    Double inv_labtest_subtotal_num = 0.0;*/

                        for (Integer i = 0; i < pasien_ini.size(); i++) {

                            holder.namapasien[i].setText(pasien_ini.get(i).getName());
                            String gender = "";
                            if (pasien_ini.get(i).getGender() == 1) {
                                gender = "Male";
                            } else {
                                gender = "Female";
                            }
                            holder.usiadangender[i].setText(gender + ", " + Integer.toString(pasien_ini.get(i).getUsiaPasien()) + " years old");

                            for (Integer j = 0; j < pasien_ini.get(i).getTestsLab().size(); j++) {

                                holder.namatest[i][j].setText("\u2022" + pasien_ini.get(i).getTestsLab().get(j).getName());


                            /*TextView inv_medcheck_namatest = (TextView) inv_medcheck_items_view.findViewById(R.id.inv_medcheck_namatest);
                            TextView inv_medcheck_unit = (TextView) inv_medcheck_items_view.findViewById(R.id.inv_medcheck_unit);
                            TextView inv_medcheck_harga = (TextView) inv_medcheck_items_view.findViewById(R.id.inv_medcheck_harga);*/

                            /*TextView inv_labtest_namatest = (TextView) inv_labtest_items_view.findViewById(R.id.inv_medcheck_namatest);
                            TextView inv_labtest_unit = (TextView) inv_labtest_items_view.findViewById(R.id.inv_medcheck_unit);
                            TextView inv_labtest_harga = (TextView) inv_labtest_items_view.findViewById(R.id.inv_medcheck_harga);*/

                            /*inv_medcheck_namatest.setText(pasien_ini.get(i).getTestsLab().get(j).getName());
                            inv_medcheck_unit.setText("1");*/

                            /*inv_labtest_namatest.setText(pasien_ini.get(i).getTestsLab().get(j).getName());
                            inv_labtest_unit.setText("1");*/

                                //inv_medcheck_harga.setText("IDR " + Double.toString(pasien_ini.get(i).getTestsLab().get(j).getHargaMedis()));
                                //inv_medcheck_harga.setText("IDR " + String.format("%,.2f",pasien_ini.get(i).getTestsLab().get(j).getHargaMedis()));

                            /*inv_medcheck_harga.setText("IDR " + String.format(Locale.GERMAN, "%,.0f", pasien_ini.get(i).getTestsLab().get(j).getHargaMedis()));
                            inv_labtest_harga.setText("IDR " + String.format(Locale.GERMAN, "%,.0f", pasien_ini.get(i).getTestsLab().get(j).getHargaLab()));*/

                            /*inv_medcheck_subtotal_num = inv_medcheck_subtotal_num + pasien_ini.get(i).getTestsLab().get(j).getHargaMedis();
                            inv_labtest_subtotal_num = inv_labtest_subtotal_num + pasien_ini.get(i).getTestsLab().get(j).getHargaLab();*/

                                if (mappingan.getProcessed_by_adapter_header() == false) {
                                /*((LinearLayout) inv_medcheck_items).addView(inv_medcheck_items_view);
                                ((LinearLayout) inv_labtest_items).addView(inv_labtest_items_view);*/
                                }
                            }
                            if (mappingan.getProcessed_by_adapter_header() == false) {







                            }
                        }

                        //NamedLocation item=LIST_LOCATIONS[0];
                        NamedLocation item1=new NamedLocation("",new LatLng(mappingan.getLatitude_pasien(),mappingan.getLongitude_pasien()));
                        NamedLocation item2=new NamedLocation("",new LatLng(mappingan.getLatitude_lab(),mappingan.getLongitude_lab()));
                        List<NamedLocation> item= Arrays.asList(item1,item2);

                        holder.mapView2.setTag(item);
                        //holder.mapView2.setClickable(false);
                        holder.mapView2.setFocusable(false);





                        mappingan.setProcessed_by_adapter_header(true); //disable supaya yang child bisa ke-create

            /*} catch (Exception e) {
                //e.printStackTrace();

            }*/
                    }
                }else{
                    convertView = inflater.inflate(R.layout.kosong, parent,false);
                }

            //vi = inflater.inflate(R.layout.my_adapter_appointments_item, null);



            // Get the NamedLocation for this item and attach it to the MapView
            //NamedLocation item = get(groupPosition);
            //NamedLocation item = new NamedLocation("",new LatLng(112.000,-7.000));




            return convertView;
        }

        @Override
        public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            //final PasienList mappingan = pasienList.get(groupPosition);
            final PasienList mappingan = MyApplication.getInstance().getPasienFromList().get(groupPosition);
            final myViewHolder mholder;
            if (convertView == null || (myViewHolder) convertView.getTag()==null) {
                convertView = inflater.inflate(R.layout.my_adapter_item_child, parent,false); //20161218 pake false instead of null
                mholder = new myViewHolder();

                mholder.rl = (LinearLayout) convertView.findViewById(R.id.pick_order);

                mholder.rl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        MyApplication.getInstance().getPasienFromList().get(mholder.position).setMasih_order(true);
                        MyApplication.getInstance().setAllPasienListFalseForAdapter();

                        db.setMasihOrder(mappingan.getInvoice(),true);
                        db.closeDB();

                        //MyApplication.getInstance().getPasienFromList().remove(position);
                        //mappingan.setMasih_order(false);
                        Intent intent = new Intent("pick order diklik");
                        LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
                            /*if(mContext instanceof ListPasien_tabview){
                                LocalBroadcastManager.getInstance((ListPasien_tabview)mContext).sendBroadcast(intent);
                            }*/


                    }
                });

                mholder.invoice = (TextView) convertView.findViewById(R.id.invoice);

                mholder.inv_medcheck_subtotal = (TextView) convertView.findViewById(R.id.inv_medcheck_subtotal);
                mholder.inv_labtest_subtotal = (TextView) convertView.findViewById(R.id.inv_labtest_subtotal);
                mholder.inv_total = (TextView) convertView.findViewById(R.id.inv_total);

                mholder.inv_medcheck_items = (LinearLayout) convertView.findViewById(R.id.inv_medcheck_items);
                mholder.inv_labtest_items = (LinearLayout) convertView.findViewById(R.id.inv_labtest_items);

                //ArrayList<Pasien> pasien_ini = mappingan.getPasien();

                //20170101 0638 sekarang dapat testlab dari testlab per invoice, bukan per pasien
                //Integer jsize=pasien_ini.get(0).getTestsLab().size();
                Integer jsize=mappingan.getTests_labs().size();
                /*for (Integer i = 0; i < pasien_ini.size(); i++) {
                    if(pasien_ini.get(i).getTestsLab().size()>jsize){
                        jsize=pasien_ini.get(i).getTestsLab().size();
                    }
                }*/

                mholder.inv_medcheck_items_view = new View[1][jsize];
                mholder.inv_labtest_items_view = new View[1][jsize];
                mholder.inv_medcheck_namatest = new TextView[1][jsize];
                mholder.inv_medcheck_unit = new TextView[1][jsize];
                mholder.inv_medcheck_harga = new TextView[1][jsize];
                mholder.inv_labtest_namatest = new TextView[1][jsize];
                mholder.inv_labtest_unit = new TextView[1][jsize];
                mholder.inv_labtest_harga = new TextView[1][jsize];

                for (Integer i = 0; i < 1; i++) {
                    for (Integer j = 0; j < jsize; j++) {
                        mholder.inv_medcheck_items_view[i][j] = inflater.inflate(R.layout.invoice_medcheck_tests_item, mholder.inv_medcheck_items, false);
                        mholder.inv_labtest_items_view[i][j] = inflater.inflate(R.layout.invoice_medcheck_tests_item, mholder.inv_labtest_items, false);

                        mholder.inv_medcheck_namatest[i][j] = (TextView) mholder.inv_medcheck_items_view[i][j].findViewById(R.id.inv_medcheck_namatest);
                        mholder.inv_medcheck_unit[i][j] = (TextView) mholder.inv_medcheck_items_view[i][j].findViewById(R.id.inv_medcheck_unit);
                        mholder.inv_medcheck_harga[i][j] = (TextView) mholder.inv_medcheck_items_view[i][j].findViewById(R.id.inv_medcheck_harga);

                        mholder.inv_labtest_namatest[i][j] = (TextView) mholder.inv_labtest_items_view[i][j].findViewById(R.id.inv_medcheck_namatest);
                        mholder.inv_labtest_unit[i][j] = (TextView) mholder.inv_labtest_items_view[i][j].findViewById(R.id.inv_medcheck_unit);
                        mholder.inv_labtest_harga[i][j] = (TextView) mholder.inv_labtest_items_view[i][j].findViewById(R.id.inv_medcheck_harga);

                        ((LinearLayout) mholder.inv_medcheck_items).addView(mholder.inv_medcheck_items_view[i][j]);
                        ((LinearLayout) mholder.inv_labtest_items).addView(mholder.inv_labtest_items_view[i][j]);
                    }
                }
                convertView.setTag(mholder);
            }else{
                mholder = (myViewHolder) convertView.getTag();

                //20170102 tambahkan re-create
                mholder.rl = (LinearLayout) convertView.findViewById(R.id.pick_order);

                mholder.rl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        MyApplication.getInstance().getPasienFromList().get(mholder.position).setMasih_order(true);
                        MyApplication.getInstance().setAllPasienListFalseForAdapter();

                        db.setMasihOrder(mappingan.getInvoice(),true);
                        db.closeDB();

                        //MyApplication.getInstance().getPasienFromList().remove(position);
                        //mappingan.setMasih_order(false);
                        Intent intent = new Intent("pick order diklik");
                        LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
                            /*if(mContext instanceof ListPasien_tabview){
                                LocalBroadcastManager.getInstance((ListPasien_tabview)mContext).sendBroadcast(intent);
                            }*/


                    }
                });

                mholder.invoice = (TextView) convertView.findViewById(R.id.invoice);

                mholder.inv_medcheck_subtotal = (TextView) convertView.findViewById(R.id.inv_medcheck_subtotal);
                mholder.inv_labtest_subtotal = (TextView) convertView.findViewById(R.id.inv_labtest_subtotal);
                mholder.inv_total = (TextView) convertView.findViewById(R.id.inv_total);

                mholder.inv_medcheck_items = (LinearLayout) convertView.findViewById(R.id.inv_medcheck_items);
                mholder.inv_labtest_items = (LinearLayout) convertView.findViewById(R.id.inv_labtest_items);

                //ArrayList<Pasien> pasien_ini = mappingan.getPasien();

                //20170101 0638 sekarang dapat testlab dari testlab per invoice, bukan per pasien
                //Integer jsize=pasien_ini.get(0).getTestsLab().size();
                Integer jsize=mappingan.getTests_labs().size();
                /*for (Integer i = 0; i < pasien_ini.size(); i++) {
                    if(pasien_ini.get(i).getTestsLab().size()>jsize){
                        jsize=pasien_ini.get(i).getTestsLab().size();
                    }
                }*/

                mholder.inv_medcheck_items_view = new View[1][jsize];
                mholder.inv_labtest_items_view = new View[1][jsize];
                mholder.inv_medcheck_namatest = new TextView[1][jsize];
                mholder.inv_medcheck_unit = new TextView[1][jsize];
                mholder.inv_medcheck_harga = new TextView[1][jsize];
                mholder.inv_labtest_namatest = new TextView[1][jsize];
                mholder.inv_labtest_unit = new TextView[1][jsize];
                mholder.inv_labtest_harga = new TextView[1][jsize];

                //20170102 re-create
                if(((LinearLayout) mholder.inv_medcheck_items).getChildCount()>0){
                    ((LinearLayout) mholder.inv_medcheck_items).removeAllViews();
                    ((LinearLayout) mholder.inv_medcheck_items).invalidate();
                }
                if(((LinearLayout) mholder.inv_labtest_items).getChildCount()>0){
                    ((LinearLayout) mholder.inv_labtest_items).removeAllViews();
                    ((LinearLayout) mholder.inv_labtest_items).invalidate();
                }

                for (Integer i = 0; i < 1; i++) {
                    for (Integer j = 0; j < jsize; j++) {
                        mholder.inv_medcheck_items_view[i][j] = inflater.inflate(R.layout.invoice_medcheck_tests_item, mholder.inv_medcheck_items, false);
                        mholder.inv_labtest_items_view[i][j] = inflater.inflate(R.layout.invoice_medcheck_tests_item, mholder.inv_labtest_items, false);

                        mholder.inv_medcheck_namatest[i][j] = (TextView) mholder.inv_medcheck_items_view[i][j].findViewById(R.id.inv_medcheck_namatest);
                        mholder.inv_medcheck_unit[i][j] = (TextView) mholder.inv_medcheck_items_view[i][j].findViewById(R.id.inv_medcheck_unit);
                        mholder.inv_medcheck_harga[i][j] = (TextView) mholder.inv_medcheck_items_view[i][j].findViewById(R.id.inv_medcheck_harga);

                        mholder.inv_labtest_namatest[i][j] = (TextView) mholder.inv_labtest_items_view[i][j].findViewById(R.id.inv_medcheck_namatest);
                        mholder.inv_labtest_unit[i][j] = (TextView) mholder.inv_labtest_items_view[i][j].findViewById(R.id.inv_medcheck_unit);
                        mholder.inv_labtest_harga[i][j] = (TextView) mholder.inv_labtest_items_view[i][j].findViewById(R.id.inv_medcheck_harga);





                        ((LinearLayout) mholder.inv_medcheck_items).addView(mholder.inv_medcheck_items_view[i][j]);
                        ((LinearLayout) mholder.inv_labtest_items).addView(mholder.inv_labtest_items_view[i][j]);
                    }
                }
                convertView.setTag(mholder);
            }

            mholder.position=groupPosition;

                if(mappingan.getMasih_order()==false) {

                    if (mappingan.getMasih_order()==false) {







                        mholder.invoice.setText(mappingan.getInvoice());

                        //LinearLayout pasien = (LinearLayout) convertView.findViewById(R.id.layoutpasien);


                        //ArrayList<Pasien> pasien_ini = mappingan.getPasien(); //20170101 0638 sekarang dapat testlab dari testlab per invoice, bukan per pasien
                        ArrayList<Tests_lab> tests_labs = mappingan.getTests_labs();


                        Double inv_medcheck_subtotal_num = 0.0;
                        Double inv_labtest_subtotal_num = 0.0;

                        //20170101 0638 sekarang dapat testlab dari testlab per invoice, bukan per pasien
                        //start comment
                        /*for (Integer i = 0; i < pasien_ini.size(); i++) {
                            //View child = inflater.inflate(R.layout.pasien_item, pasien, false);
                        *//*TextView namapasien = (TextView) child.findViewById(R.id.namapasien);
                        TextView usiadangender = (TextView) child.findViewById(R.id.usiadangender);
                        namapasien.setText(pasien_ini.get(i).getName());
                        String gender = "";
                        if (pasien_ini.get(i).getGender() == 1) {
                            gender = "Male";
                        } else {
                            gender = "Female";
                        }
                        usiadangender.setText(gender + ", " + Integer.toString(pasien_ini.get(i).getUsiaPasien()) + " years old");*//*

                            for (Integer j = 0; j < pasien_ini.get(i).getTestsLab().size(); j++) {
                                //View tests = inflater.inflate(R.layout.pasien_tests_item, (LinearLayout) child, false);



                                mholder.inv_medcheck_namatest[i][j].setText(pasien_ini.get(i).getTestsLab().get(j).getName());
                                mholder.inv_medcheck_unit[i][j].setText("1");

                                mholder.inv_labtest_namatest[i][j].setText(pasien_ini.get(i).getTestsLab().get(j).getName());
                                mholder.inv_labtest_unit[i][j].setText("1");

                                //inv_medcheck_harga.setText("IDR " + Double.toString(pasien_ini.get(i).getTestsLab().get(j).getHargaMedis()));
                                //inv_medcheck_harga.setText("IDR " + String.format("%,.2f",pasien_ini.get(i).getTestsLab().get(j).getHargaMedis()));
                                mholder.inv_medcheck_harga[i][j].setText("IDR " + String.format(Locale.GERMAN, "%,.0f", pasien_ini.get(i).getTestsLab().get(j).getHargaMedis()));
                                mholder.inv_labtest_harga[i][j].setText("IDR " + String.format(Locale.GERMAN, "%,.0f", pasien_ini.get(i).getTestsLab().get(j).getHargaLab()));

                                inv_medcheck_subtotal_num = inv_medcheck_subtotal_num + pasien_ini.get(i).getTestsLab().get(j).getHargaMedis();
                                inv_labtest_subtotal_num = inv_labtest_subtotal_num + pasien_ini.get(i).getTestsLab().get(j).getHargaLab();

                            *//*if (mappingan.getProcessed_by_adapter_child() == false) {*//*

                            *//*}*//*
                            }
                        *//*if (mappingan.getProcessed_by_adapter_child() == false) {
                            try {
                                Log.e("catcher","pasien dalam childview : " + pasien.toString());
                                } catch (Exception e) {
                                e.printStackTrace();
                                Log.e("catcher","pasien dalam childview : ERROR" + e.getMessage());
                            }
                            try {
                                Log.e("catcher","child dalam childview : " + child.toString());
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e("catcher","child dalam childview : ERROR" + e.getMessage());
                            }
                            try {
                                pasien.addView(child);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }*//*
                        }*/
                        //end comment


                        for (Integer i = 0; i < 1; i++) {
                            for (Integer j = 0; j < tests_labs.size(); j++) {
                                //View tests = inflater.inflate(R.layout.pasien_tests_item, (LinearLayout) child, false);



                                mholder.inv_medcheck_namatest[i][j].setText(tests_labs.get(j).getName());
                                mholder.inv_medcheck_unit[i][j].setText(String.valueOf(tests_labs.get(j).getJml()));

                                mholder.inv_labtest_namatest[i][j].setText(tests_labs.get(j).getName());
                                mholder.inv_labtest_unit[i][j].setText(String.valueOf(tests_labs.get(j).getJml()));

                                //inv_medcheck_harga.setText("IDR " + Double.toString(pasien_ini.get(i).getTestsLab().get(j).getHargaMedis()));
                                //inv_medcheck_harga.setText("IDR " + String.format("%,.2f",pasien_ini.get(i).getTestsLab().get(j).getHargaMedis()));
                                mholder.inv_medcheck_harga[i][j].setText("IDR " + String.format(Locale.GERMAN, "%,.0f", tests_labs.get(j).getHargaMedis()));
                                mholder.inv_labtest_harga[i][j].setText("IDR " + String.format(Locale.GERMAN, "%,.0f", tests_labs.get(j).getHargaLab()));

                                inv_medcheck_subtotal_num = inv_medcheck_subtotal_num + tests_labs.get(j).getHargaMedis();
                                inv_labtest_subtotal_num = inv_labtest_subtotal_num + tests_labs.get(j).getHargaLab();

                            /*if (mappingan.getProcessed_by_adapter_child() == false) {*/

                            /*}*/
                            }
                        /*if (mappingan.getProcessed_by_adapter_child() == false) {
                            try {
                                Log.e("catcher","pasien dalam childview : " + pasien.toString());
                                } catch (Exception e) {
                                e.printStackTrace();
                                Log.e("catcher","pasien dalam childview : ERROR" + e.getMessage());
                            }
                            try {
                                Log.e("catcher","child dalam childview : " + child.toString());
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e("catcher","child dalam childview : ERROR" + e.getMessage());
                            }
                            try {
                                pasien.addView(child);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }*/
                        }
                        mholder.inv_medcheck_subtotal.setText("IDR " + String.format(Locale.GERMAN, "%,.0f", inv_medcheck_subtotal_num));
                        mholder.inv_labtest_subtotal.setText("IDR " + String.format(Locale.GERMAN, "%,.0f", inv_labtest_subtotal_num));
                        mholder.inv_total.setText("IDR " + String.format(Locale.GERMAN, "%,.0f", inv_medcheck_subtotal_num + inv_labtest_subtotal_num));
        /*pasien.invalidate();
        vi.invalidate();*/

                        mappingan.setProcessed_by_adapter_child(true);
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

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }



        /**
         * Retuns the set of all initialised {@link MapView} objects.
         *
         * @return All MapViews that have been initialised programmatically by this adapter
         */
        public HashSet<MapView> getMaps() {
            return mMaps;
        }


    }

    @Override
    public void onResume() {

        super.onResume();



        /*if (mapView != null) {
            mapView.onResume();
        }*/
    }


    @Override
    public void onPause() {
        if (mapView != null) {
            mapView.onPause();
        }
        super.onPause();
    }


    @Override
    public void onDestroy() {
        if (mapView != null) {
            try {
                mapView.onDestroy();
            } catch (NullPointerException e) {
                //Log.e(TAG, "Error while attempting MapView.onDestroy(), ignoring exception", e);
            }
        }
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if (mapView != null) {
            mapView.onLowMemory();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mapView != null) {
            mapView.onSaveInstanceState(outState);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder implements OnMapReadyCallback {

        Integer ukuran;

        MapView mapView2;

        TextView title;

        GoogleMap map2;



        RelativeLayout rl;
        TextView judul;
        TextView waktu;
        TextView remainingwaktu;
        TextView jarakpasien;
        TextView jaraklab;
        TextView alamatpasien;
        TextView namalab;
        TextView alamatlab;
        TextView namamember;
        LinearLayout pasien;

        //20170105
        /*RelativeLayout holderpasien[];
        ImageView iconpasien[];*/

        View child[];
        TextView namapasien[];
        TextView usiadangender[];

        View tests[][];
        TextView namatest[][];



        public ViewHolder(MapView itemView) {
            super(itemView);
            mapView2 = itemView;
        }

        @Override
        public void onMapReady(GoogleMap googleMap) {
            MapsInitializer.initialize(mContext.getApplicationContext());
            map2 = googleMap;
            //NamedLocation data[] = (NamedLocation[]) mapView2.getTag();
            final List<NamedLocation> data= (List<NamedLocation>) mapView2.getTag();

            if (data != null) {

                setMapLocation(map2, data.get(0),data.get(1),mapView2);
            }
            final PopupWindow[] mPopupWindow = new PopupWindow[1];
            map2.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {
                    //Toast.makeText(mContext, "clicked", Toast.LENGTH_SHORT).show();
                    LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);

                    // Inflate the custom layout/view
                    View customView = inflater.inflate(R.layout.popup_window_map,null);
                    mPopupWindow[0] = new PopupWindow(
                            customView,
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    );
// Set an elevation value for popup window
                    // Call requires API level 21
                    if(Build.VERSION.SDK_INT>=21){
                        mPopupWindow[0].setElevation(5.0f);
                    }
                    // Get a reference for the custom view close button
                    Button closeButton = (Button) customView.findViewById(R.id.button2);

                    // Set a click listener for the popup window close button
                    closeButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // Dismiss the popup window
                            mPopupWindow[0].dismiss();
                        }
                    });

                    TextView dir_pasien = (TextView) customView.findViewById(R.id.dir_pasien);
                    TextView dir_lab = (TextView) customView.findViewById(R.id.dir_lab);

                    dir_pasien.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Uri gmmIntentUri = Uri.parse("google.navigation:q=" + Double.toString(data.get(0).location.latitude) + "," + Double.toString(data.get(0).location.longitude));
                            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                            mapIntent.setPackage("com.google.android.apps.maps");
                            if (mapIntent.resolveActivity(mContext.getPackageManager()) != null) {
                                startActivity(mapIntent);
                            }else{
                                Toast.makeText(mContext, "Need Install Google Maps App", Toast.LENGTH_SHORT).show();
                            }


                        }
                    });

                    dir_lab.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Uri gmmIntentUri = Uri.parse("google.navigation:q=" + Double.toString(data.get(1).location.latitude) + "," + Double.toString(data.get(1).location.longitude));
                            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                            mapIntent.setPackage("com.google.android.apps.maps");
                            if (mapIntent.resolveActivity(mContext.getPackageManager()) != null) {
                                startActivity(mapIntent);
                            }else{
                                Toast.makeText(mContext, "Need Install Google Maps App", Toast.LENGTH_SHORT).show();
                            }


                        }
                    });

                    // Finally, show the popup window at the center location of root relative layout
                    mPopupWindow[0].showAtLocation(mapView2, Gravity.CENTER,0,0);


                }
            });
        }

        /**
         * Initialises the MapView by calling its lifecycle methods.
         */
        public void initializeMapView() {
            if (mapView2 != null) {
                // Initialise the MapView
                mapView2.onCreate(null);
                mapView2.onResume();

                // Set the map ready callback to receive the GoogleMap object
                mapView2.getMapAsync(this);
            }
        }

    }

    public class NamedLocation {

        public final String name;

        public final LatLng location;

        NamedLocation(String name, LatLng location) {
            this.name = name;
            this.location = location;
        }
    }

    /**
     * A list of locations to show in this ListView.
     */
    /*private final NamedLocation[] LIST_LOCATIONS = new NamedLocation[]{
            new NamedLocation("Cape Town", new LatLng(-33.920455, 18.466941)),
            new NamedLocation("Beijing", new LatLng(39.937795, 116.387224)),
            new NamedLocation("Bern", new LatLng(46.948020, 7.448206)),
            new NamedLocation("Breda", new LatLng(51.589256, 4.774396)),
            new NamedLocation("Brussels", new LatLng(50.854509, 4.376678)),
            new NamedLocation("Copenhagen", new LatLng(55.679423, 12.577114)),
            new NamedLocation("Hannover", new LatLng(52.372026, 9.735672)),
            new NamedLocation("Helsinki", new LatLng(60.169653, 24.939480)),
            new NamedLocation("Hong Kong", new LatLng(22.325862, 114.165532)),
            new NamedLocation("Istanbul", new LatLng(41.034435, 28.977556)),
            new NamedLocation("Johannesburg", new LatLng(-26.202886, 28.039753)),
            new NamedLocation("Lisbon", new LatLng(38.707163, -9.135517)),
            new NamedLocation("London", new LatLng(51.500208, -0.126729)),
            new NamedLocation("Madrid", new LatLng(40.420006, -3.709924)),
            new NamedLocation("Mexico City", new LatLng(19.427050, -99.127571)),
            new NamedLocation("Moscow", new LatLng(55.750449, 37.621136)),
            new NamedLocation("New York", new LatLng(40.750580, -73.993584)),
            new NamedLocation("Oslo", new LatLng(59.910761, 10.749092)),
            new NamedLocation("Paris", new LatLng(48.859972, 2.340260)),
            new NamedLocation("Prague", new LatLng(50.087811, 14.420460)),
            new NamedLocation("Rio de Janeiro", new LatLng(-22.90187, -43.232437)),
            new NamedLocation("Rome", new LatLng(41.889998, 12.500162)),
            new NamedLocation("Sao Paolo", new LatLng(-22.863878, -43.244097)),
            new NamedLocation("Seoul", new LatLng(37.560908, 126.987705)),
            new NamedLocation("Stockholm", new LatLng(59.330650, 18.067360)),
            new NamedLocation("Sydney", new LatLng(-33.873651, 151.2068896)),
            new NamedLocation("Taipei", new LatLng(25.022112, 121.478019)),
            new NamedLocation("Tokyo", new LatLng(35.670267, 139.769955)),
            new NamedLocation("Tulsa Oklahoma", new LatLng(36.149777, -95.993398)),
            new NamedLocation("Vaduz", new LatLng(47.141076, 9.521482)),
            new NamedLocation("Vienna", new LatLng(48.209206, 16.372778)),
            new NamedLocation("Warsaw", new LatLng(52.235474, 21.004057)),
            new NamedLocation("Wellington", new LatLng(-41.286480, 174.776217)),
            new NamedLocation("Winnipeg", new LatLng(49.875832, -97.150726))
    };*/

    private static void setMapLocation(GoogleMap map, NamedLocation datapasien, NamedLocation datalab, MapView mv) {
        List<LatLng> xx = Arrays.asList(datapasien.location,datalab.location);

        /*LatLng tengah = computeCentroid(xx);
        // Add a marker for this item and set the camera
        //map.moveCamera(CameraUpdateFactory.newLatLngZoom(tengah, 13f));
        Location loc1=new Location("");
        loc1.setLatitude(datapasien.location.latitude);
        loc1.setLongitude(datapasien.location.longitude);
        Location loc2=new Location("");
        loc2.setLatitude(datalab.location.latitude);
        loc2.setLongitude(datalab.location.longitude);

        float jarak = loc1.distanceTo(loc2)/2;


        float zoomLevel = getZoomLevel(jarak);*/

        map.addMarker(new MarkerOptions()
                .position(datapasien.location)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.patientmarker))
        );
        map.addMarker(new MarkerOptions()
                .position(datalab.location)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.labmarker))
        );

        //map.moveCamera(CameraUpdateFactory.newLatLngZoom(tengah, zoomLevel-1));
        LatLng aku=null;
        try {
            aku=new LatLng(MyApplication.getInstance().getLat(),MyApplication.getInstance().getLon());
            map.addMarker(new MarkerOptions()
                    .position(aku));

        } catch (Exception e) {
            e.printStackTrace();
        }

        LatLngBounds.Builder builder = new LatLngBounds.Builder();

        builder.include(datapasien.location);
        builder.include(datalab.location);
        if(aku!=null){
            builder.include(aku);
        }

        LatLngBounds bounds = builder.build();

        BitmapDrawable bd=(BitmapDrawable) mContext.getResources().getDrawable(R.drawable.patientmarker);
        int imageHeight = (int)  bd.getBitmap().getHeight();


        int width2 = mContext.getResources().getDisplayMetrics().widthPixels;
        int height2 = mContext.getResources().getDisplayMetrics().heightPixels;
        int width=mv.getWidth();
        int height=mv.getHeight();
        int padding = (int) (width * 0.10);
        int padding2 = (int) (width2 * 0.40);
        //CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding); //crash, need width and height
        CameraUpdate cu = null;
        try {
            cu = CameraUpdateFactory.newLatLngBounds(bounds, width, height, imageHeight);
        } catch (Exception e) {
            cu = CameraUpdateFactory.newLatLngBounds(bounds, width2, height2, imageHeight);
        }

        map.moveCamera(cu);

        // Set the map type back to normal.
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);


    }

    /**
     * RecycleListener that completely clears the {@link GoogleMap}
     * attached to a row in the ListView.
     * Sets the map type to {@link GoogleMap#MAP_TYPE_NONE} and clears
     * the map.
     */
    private AbsListView.RecyclerListener mRecycleListener = new AbsListView.RecyclerListener() {

        @Override
        public void onMovedToScrapHeap(View view) {
            ViewHolder holder = (ViewHolder) view.getTag();
            if (holder != null && holder.map2 != null) {
                // Clear the map and free up resources by changing the map type to none
                holder.map2.clear();
                holder.map2.setMapType(GoogleMap.MAP_TYPE_NONE);
            }

        }
    };

    /*private static LatLng computeCentroid(List<LatLng> points) {
        double latitude = 0;
        double longitude = 0;
        int n = points.size();

        for (LatLng point : points) {
            latitude += point.latitude;
            longitude += point.longitude;
        }

        return new LatLng(latitude/n, longitude/n);
    }

    private int getZoomLevel(double radius){
        double scale = radius / 500;
        return ((int) (16 - Math.log(scale) / Math.log(2)));
    }*/

    static class myViewHolder {
        LinearLayout rl;
        TextView invoice;
        TextView inv_medcheck_subtotal;
        TextView inv_labtest_subtotal;
        TextView inv_total;
        LinearLayout inv_medcheck_items;
        LinearLayout inv_labtest_items;

        View inv_medcheck_items_view[][];
        View inv_labtest_items_view[][];
        TextView inv_medcheck_namatest[][];
        TextView inv_medcheck_unit[][];
        TextView inv_medcheck_harga[][];
        TextView inv_labtest_namatest[][];
        TextView inv_labtest_unit[][];
        TextView inv_labtest_harga[][];

        Integer position;
    }

}