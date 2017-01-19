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
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.ListView;

import java.util.ArrayList;

import medis.ourlab.labkita.R;
import medis.ourlab.labkita.app.MyApplication;
import medis.ourlab.labkita.helper.MyAdapter;
import medis.ourlab.labkita.helper.MyAdapter_order_expandable;
import medis.ourlab.labkita.helper.MyAdapter_order_expandable_withmap;


public class OneFragment_expandable extends Fragment{
    ArrayList<PasienList> pasienList;
    Context mContext;
    MyAdapter_order_expandable adapter;
    ExpandableListView mylistview;
    public OneFragment_expandable() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=getContext();

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
                    adapter=new MyAdapter_order_expandable(getActivity(),pasienList);
                    mylistview.setAdapter(adapter);

                    DisplayMetrics metrics = new DisplayMetrics();
                    WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
                    windowManager.getDefaultDisplay().getMetrics(metrics);
                    int width = metrics.widthPixels;
                    if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
                        mylistview.setIndicatorBounds(width - GetPixelFromDips(70), width - GetPixelFromDips(10));

                        //searchExpListView.setIndicatorBounds(width - GetPixelFromDips(50), width - GetPixelFromDips(10));
                    } else {
                        mylistview.setIndicatorBoundsRelative(width - GetPixelFromDips(70), width - GetPixelFromDips(10));
                        //searchExpListView.setIndicatorBoundsRelative(width - GetPixelFromDips(50), width - GetPixelFromDips(10));
                    }
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

            //adapter.notifyDataSetChanged(); //gak iso
            View view = getView();
            if(view!=null) {
                ExpandableListView xx = (ExpandableListView) view.findViewById(R.id.list);
                //((BaseAdapter)xx.getAdapter()).notifyDataSetChanged();
                //MyApplication.getInstance().setAllPasienListFalseForAdapter();
                xx.setAdapter(new MyAdapter_order_expandable(getActivity(), MyApplication.getInstance().getPasienFromList()));
            }
        }
    };

    public int GetPixelFromDips(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }

}