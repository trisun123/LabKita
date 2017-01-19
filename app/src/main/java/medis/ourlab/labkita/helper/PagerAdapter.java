package medis.ourlab.labkita.helper;

/**
 * Created by tri on 11/8/2016.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import medis.ourlab.labkita.model.OneFragment;
import medis.ourlab.labkita.model.TwoFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                OneFragment tab1 = new OneFragment();
                return tab1;
            case 1:
                TwoFragment tab2 = new TwoFragment();
                return tab2;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}