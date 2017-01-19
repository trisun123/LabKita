package medis.ourlab.labkita.model;

/**
 * Created by tri on 11/8/2016.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import medis.ourlab.labkita.R;
import medis.ourlab.labkita.app.MyApplication;
import medis.ourlab.labkita.helper.MyAdapter;
import medis.ourlab.labkita.helper.MyAdapter_appointments_withviewholder;
import medis.ourlab.labkita.helper.SQLiteHandler;


public class TwoFragment extends Fragment{
    List<PasienList> pasienList;
    Context mContext;
    MyAdapter_appointments_withviewholder adapter;
    ListView mylistview;
    public TwoFragment() {
        // Required empty public constructor
    }
    SQLiteHandler db;

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

        return inflater.inflate(R.layout.fragment_two, container, false);
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
                mylistview= (ListView) view.findViewById(R.id.list);
                if (MyApplication.getInstance().getPasienListCount() > 0) {


                    //Toast.makeText(getContext(), "jumlah order : " + Integer.toString(MyApplication.getInstance().getPasienListCount()), Toast.LENGTH_SHORT).show();
                    pasienList= MyApplication.getInstance().getPasienFromList();
                    MyApplication.getInstance().setAllPasienListFalseForAdapter();
                    adapter=new MyAdapter_appointments_withviewholder(getActivity(),pasienList);
                    mylistview.setAdapter(adapter);
                    //adapter.notifyDataSetChanged();
                }

            } catch (Exception e) {
                //e.printStackTrace();

            }
        }
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            String message = intent.getStringExtra("message");


            View view = getView();
            if(view!=null) {
                ListView xx = (ListView) view.findViewById(R.id.list);
                //((BaseAdapter)xx.getAdapter()).notifyDataSetChanged();
                //MyApplication.getInstance().setAllPasienListFalseForAdapter();
                db.setMasihOrder(MyApplication.getInstance().getInvoice(),false);
                MyApplication.getInstance().setInvoice("");
                db.closeDB();
                xx.setAdapter(new MyAdapter_appointments_withviewholder(getActivity(), MyApplication.getInstance().getPasienFromList()));
            }
        }
    };



}