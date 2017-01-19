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
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import medis.ourlab.labkita.R;
import medis.ourlab.labkita.app.MyApplication;
import medis.ourlab.labkita.model.Pasien;
import medis.ourlab.labkita.model.PasienList;

public class MyAdapter_order_expandable_withmap extends BaseExpandableListAdapter implements OnMapReadyCallback {
    //namae tampung;
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private ArrayList<HashMap<String, String>> filteredData;
private Boolean mapready=false;
    private List<PasienList> pasienList;

    private static LayoutInflater inflater=null;

MapView mapView;
    GoogleMap map;

    //public ImageLoader imageLoader;

    //public static final String MY_DATA = null;
    Context mContext;
    //tools.SessionManager session;
    /*public MyAdapter(Context context) {
        mContext = context;
    }*/

    public MyAdapter_order_expandable_withmap(Context context, List<PasienList> pasienList) {
        /*activity = a;
        data=d;
        filteredData = d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);*/
        //imageLoader=new ImageLoader(activity.getApplicationContext());
        this.mContext=context;
        mContext=context;
        this.pasienList=pasienList;
        inflater = (LayoutInflater) this.mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        PasienList mappingan = pasienList.get(groupPosition);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.my_adapter_item_header_withmap, null);
            mapView = (MapView) convertView.findViewById(R.id.lite_listrow_map);

            if (mapView != null) {
                // Initialise the MapView
                mapView.onCreate(null);
                // Set the map ready callback to receive the GoogleMap object
                mapView.getMapAsync(this);
            }
            if(mappingan.getMasih_order()==false) {


                if (mappingan.getMasih_order()==false) {



                    //try {
                    RelativeLayout rl = (RelativeLayout) convertView.findViewById(R.id.pick_order);
                    rl.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            MyApplication.getInstance().getPasienFromList().get(groupPosition).setMasih_order(true);
                            MyApplication.getInstance().setAllPasienListFalseForAdapter();
                            //MyApplication.getInstance().getPasienFromList().remove(position);
                            //mappingan.setMasih_order(false);
                            Intent intent = new Intent("pick order diklik");
                            LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
                            /*if(mContext instanceof ListPasien_tabview){
                                LocalBroadcastManager.getInstance((ListPasien_tabview)mContext).sendBroadcast(intent);
                            }*/


                        }
                    });

                    TextView alamatpasien = (TextView) convertView.findViewById(R.id.alamatpasien);
                    TextView namalab = (TextView) convertView.findViewById(R.id.namalab);
                    TextView alamatlab = (TextView) convertView.findViewById(R.id.alamatlab);
                    TextView namamember = (TextView) convertView.findViewById(R.id.namamember);
                    //TextView invoice = (TextView) convertView.findViewById(R.id.invoice);

                    /*TextView inv_medcheck_subtotal = (TextView) convertView.findViewById(R.id.inv_medcheck_subtotal);
                    TextView inv_labtest_subtotal = (TextView) convertView.findViewById(R.id.inv_labtest_subtotal);
                    TextView inv_total = (TextView) convertView.findViewById(R.id.inv_total);*/

                    alamatpasien.setText(mappingan.getAlamat_pasien());
                    namalab.setText(mappingan.getNama_lab());
                    alamatlab.setText(mappingan.getAlamat_lab());
                    namamember.setText(mappingan.getNama_member());
                    //invoice.setText(mappingan.getInvoice());

                    LinearLayout pasien = (LinearLayout) convertView.findViewById(R.id.layoutpasien);
                    /*LinearLayout inv_medcheck_items = (LinearLayout) convertView.findViewById(R.id.inv_medcheck_items);
                    LinearLayout inv_labtest_items = (LinearLayout) convertView.findViewById(R.id.inv_labtest_items);*/

                    ArrayList<Pasien> pasien_ini = mappingan.getPasien();


                    /*Double inv_medcheck_subtotal_num = 0.0;
                    Double inv_labtest_subtotal_num = 0.0;*/

                    for (Integer i = 0; i < pasien_ini.size(); i++) {
                        View child = inflater.inflate(R.layout.pasien_item, pasien, false);
                        TextView namapasien = (TextView) child.findViewById(R.id.namapasien);
                        TextView usiadangender = (TextView) child.findViewById(R.id.usiadangender);
                        namapasien.setText(pasien_ini.get(i).getName());
                        String gender = "";
                        if (pasien_ini.get(i).getGender() == 1) {
                            gender = "Male";
                        } else {
                            gender = "Female";
                        }
                        usiadangender.setText(gender + ", " + Integer.toString(pasien_ini.get(i).getUsiaPasien()) + " years old");

                        for (Integer j = 0; j < pasien_ini.get(i).getTestsLab().size(); j++) {
                            View tests = inflater.inflate(R.layout.pasien_tests_item, (LinearLayout) child, false);

                            /*View inv_medcheck_items_view = inflater.inflate(R.layout.invoice_medcheck_tests_item, inv_medcheck_items, false);
                            View inv_labtest_items_view = inflater.inflate(R.layout.invoice_medcheck_tests_item, inv_labtest_items, false);*/

                            TextView namatest = (TextView) tests.findViewById(R.id.namatest);
                            namatest.setText("\u2022" + pasien_ini.get(i).getTestsLab().get(j).getName());
                            ((LinearLayout) child).addView(tests);

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
                        if (mappingan.getProcessed_by_adapter_header() == false)
                            pasien.addView(child);
                    }
                    /*inv_medcheck_subtotal.setText("IDR " + String.format(Locale.GERMAN, "%,.0f", inv_medcheck_subtotal_num));
                    inv_labtest_subtotal.setText("IDR " + String.format(Locale.GERMAN, "%,.0f", inv_labtest_subtotal_num));
                    inv_total.setText("IDR " + String.format(Locale.GERMAN, "%,.0f", inv_medcheck_subtotal_num + inv_labtest_subtotal_num));*/

        /*pasien.invalidate();
        vi.invalidate();*/

                    mappingan.setProcessed_by_adapter_header(true); //disable supaya yang child bisa ke-create

            /*} catch (Exception e) {
                //e.printStackTrace();

            }*/
                }
            }else{
                convertView = inflater.inflate(R.layout.kosong, null);
            }
        }
        //vi = inflater.inflate(R.layout.my_adapter_appointments_item, null);





        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        PasienList mappingan = pasienList.get(groupPosition);
        if (convertView == null) {
            if(mappingan.getMasih_order()==false) {
                convertView = inflater.inflate(R.layout.my_adapter_item_child, null);
                if (mappingan.getMasih_order()==false) {


                    TextView invoice = (TextView) convertView.findViewById(R.id.invoice);

                    TextView inv_medcheck_subtotal = (TextView) convertView.findViewById(R.id.inv_medcheck_subtotal);
                    TextView inv_labtest_subtotal = (TextView) convertView.findViewById(R.id.inv_labtest_subtotal);
                    TextView inv_total = (TextView) convertView.findViewById(R.id.inv_total);

                    /*alamatpasien.setText(mappingan.getAlamat_pasien());
                    namalab.setText(mappingan.getNama_lab());
                    alamatlab.setText(mappingan.getAlamat_lab());
                    namamember.setText(mappingan.getNama_member());*/

                    invoice.setText(mappingan.getInvoice());

                    //LinearLayout pasien = (LinearLayout) convertView.findViewById(R.id.layoutpasien);
                    LinearLayout inv_medcheck_items = (LinearLayout) convertView.findViewById(R.id.inv_medcheck_items);
                    LinearLayout inv_labtest_items = (LinearLayout) convertView.findViewById(R.id.inv_labtest_items);

                    ArrayList<Pasien> pasien_ini = mappingan.getPasien();


                    Double inv_medcheck_subtotal_num = 0.0;
                    Double inv_labtest_subtotal_num = 0.0;

                    for (Integer i = 0; i < pasien_ini.size(); i++) {
                        //View child = inflater.inflate(R.layout.pasien_item, pasien, false);
                        /*TextView namapasien = (TextView) child.findViewById(R.id.namapasien);
                        TextView usiadangender = (TextView) child.findViewById(R.id.usiadangender);
                        namapasien.setText(pasien_ini.get(i).getName());
                        String gender = "";
                        if (pasien_ini.get(i).getGender() == 1) {
                            gender = "Male";
                        } else {
                            gender = "Female";
                        }
                        usiadangender.setText(gender + ", " + Integer.toString(pasien_ini.get(i).getUsiaPasien()) + " years old");*/

                        for (Integer j = 0; j < pasien_ini.get(i).getTestsLab().size(); j++) {
                            //View tests = inflater.inflate(R.layout.pasien_tests_item, (LinearLayout) child, false);

                            View inv_medcheck_items_view = inflater.inflate(R.layout.invoice_medcheck_tests_item, inv_medcheck_items, false);
                            View inv_labtest_items_view = inflater.inflate(R.layout.invoice_medcheck_tests_item, inv_labtest_items, false);

                            /*TextView namatest = (TextView) tests.findViewById(R.id.namatest);
                            namatest.setText("\u2022" + pasien_ini.get(i).getTestsLab().get(j).getName());
                            ((LinearLayout) child).addView(tests);*/

                            TextView inv_medcheck_namatest = (TextView) inv_medcheck_items_view.findViewById(R.id.inv_medcheck_namatest);
                            TextView inv_medcheck_unit = (TextView) inv_medcheck_items_view.findViewById(R.id.inv_medcheck_unit);
                            TextView inv_medcheck_harga = (TextView) inv_medcheck_items_view.findViewById(R.id.inv_medcheck_harga);

                            TextView inv_labtest_namatest = (TextView) inv_labtest_items_view.findViewById(R.id.inv_medcheck_namatest);
                            TextView inv_labtest_unit = (TextView) inv_labtest_items_view.findViewById(R.id.inv_medcheck_unit);
                            TextView inv_labtest_harga = (TextView) inv_labtest_items_view.findViewById(R.id.inv_medcheck_harga);

                            inv_medcheck_namatest.setText(pasien_ini.get(i).getTestsLab().get(j).getName());
                            inv_medcheck_unit.setText("1");

                            inv_labtest_namatest.setText(pasien_ini.get(i).getTestsLab().get(j).getName());
                            inv_labtest_unit.setText("1");

                            //inv_medcheck_harga.setText("IDR " + Double.toString(pasien_ini.get(i).getTestsLab().get(j).getHargaMedis()));
                            //inv_medcheck_harga.setText("IDR " + String.format("%,.2f",pasien_ini.get(i).getTestsLab().get(j).getHargaMedis()));
                            inv_medcheck_harga.setText("IDR " + String.format(Locale.GERMAN, "%,.0f", pasien_ini.get(i).getTestsLab().get(j).getHargaMedis()));
                            inv_labtest_harga.setText("IDR " + String.format(Locale.GERMAN, "%,.0f", pasien_ini.get(i).getTestsLab().get(j).getHargaLab()));

                            inv_medcheck_subtotal_num = inv_medcheck_subtotal_num + pasien_ini.get(i).getTestsLab().get(j).getHargaMedis();
                            inv_labtest_subtotal_num = inv_labtest_subtotal_num + pasien_ini.get(i).getTestsLab().get(j).getHargaLab();

                            /*if (mappingan.getProcessed_by_adapter_child() == false) {*/
                                ((LinearLayout) inv_medcheck_items).addView(inv_medcheck_items_view);
                                ((LinearLayout) inv_labtest_items).addView(inv_labtest_items_view);
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
                    inv_medcheck_subtotal.setText("IDR " + String.format(Locale.GERMAN, "%,.0f", inv_medcheck_subtotal_num));
                    inv_labtest_subtotal.setText("IDR " + String.format(Locale.GERMAN, "%,.0f", inv_labtest_subtotal_num));
                    inv_total.setText("IDR " + String.format(Locale.GERMAN, "%,.0f", inv_medcheck_subtotal_num + inv_labtest_subtotal_num));
        /*pasien.invalidate();
        vi.invalidate();*/

                    mappingan.setProcessed_by_adapter_child(true);
            /*} catch (Exception e) {
                //e.printStackTrace();

            }*/
                }
            }else{
                convertView = inflater.inflate(R.layout.kosong, null);
            }
        }
        //vi = inflater.inflate(R.layout.my_adapter_appointments_item, null);





        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(mContext);
        map = googleMap;

        //NamedLocation data = (NamedLocation) mapView.getTag();

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(LIST_LOCATIONS[0].location, 10f));
        map.addMarker(new MarkerOptions().position(LIST_LOCATIONS[0].location));

        // Set the map type back to normal.
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        //map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
    }

    private static class NamedLocation {

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
    private static final NamedLocation[] LIST_LOCATIONS = new NamedLocation[]{
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
    };
}