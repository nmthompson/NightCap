package space.nthompson.nightcap.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import space.nthompson.nightcap.Fragments.CabFrag;
import space.nthompson.nightcap.Fragments.CockFrag;

/**
 * Created by Nick on 7/30/15.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    int numOfTabs;

    public ViewPagerAdapter(FragmentManager manager, int numOfTabs) {
        super(manager);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                CabFrag cabFrab = new CabFrag();
                return cabFrab;
            case 1:
                CockFrag cockFrag = new CockFrag();
                return cockFrag;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }

}


