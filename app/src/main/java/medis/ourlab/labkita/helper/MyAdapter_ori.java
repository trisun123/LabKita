package medis.ourlab.labkita.helper;

/**
 * Created by tri on 11/1/2016.
 */


import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import medis.ourlab.labkita.R;

public class MyAdapter_ori extends BaseAdapter {
    //namae tampung;
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private ArrayList<HashMap<String, String>> filteredData;
    private static LayoutInflater inflater=null;
    //public ImageLoader imageLoader;

    public static final String MY_DATA = null;
    Context mContext;
    //tools.SessionManager session;
    public MyAdapter_ori(Context context) {
        mContext = context;
    }

    public MyAdapter_ori(Activity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        data=d;
        filteredData = d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //imageLoader=new ImageLoader(activity.getApplicationContext());
    }

    @Override
    public int getCount() {
        //Log.e("traced","getcount : " + filteredData.size());


        return filteredData.size();

    }

    @Override
    public Object getItem(int position) {
        return filteredData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        //tampung=namae.getInstance();
        if(convertView==null)
            vi = inflater.inflate(R.layout.my_adapter_appointments_item, null);


        HashMap<String, String> mappingan = new HashMap<String, String>();
        //song = data.get(position);
        mappingan = filteredData.get(position);



        /*pasien.invalidate();
        vi.invalidate();*/

        TextView alamatpasien = (TextView)vi.findViewById(R.id.alamatpasien);
        TextView namalab = (TextView)vi.findViewById(R.id.namalab);
        TextView alamatlab = (TextView)vi.findViewById(R.id.alamatlab);
        TextView namamember= (TextView)vi.findViewById(R.id.namamember);
        TextView invoice = (TextView)vi.findViewById(R.id.invoice);

        //ImageView thumb_image=(ImageView)vi.findViewById(R.id.list_image); // thumb image

        //HashMap<String, String> song = new HashMap<String, String>();


        /*namapasien.setText(mappingan.get("namapasien"));
        usiadangender.setText(mappingan.get("usiadangender"));*/
        alamatpasien.setText(mappingan.get("alamatpasien"));
        namalab.setText(mappingan.get("namalab"));
        alamatlab.setText(mappingan.get("alamatlab"));
        namamember.setText(mappingan.get("namamember"));
        invoice.setText(mappingan.get("invoice"));

        return vi;
    }
    public Filter getFilter() {
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
    }

}