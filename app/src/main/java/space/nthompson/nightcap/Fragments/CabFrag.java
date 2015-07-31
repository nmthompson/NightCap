package space.nthompson.nightcap.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import space.nthompson.nightcap.R;

/**
 * Created by Nick on 7/30/15.
 */
public class CabFrag extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cab, container, false);
    }
}
