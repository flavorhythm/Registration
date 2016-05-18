package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.HashMap;
import java.util.Map;

import fragment.FragmentMain;

/***************************************************************************************************
 * PagerAdapter class extending FragmentStatePagerAdapter
 * Created by zyuki on 5/13/2016.
 *
 * Used in FragmentMain and set as an adapter for the RecyclerView
 * This adapter processes a map of fragment objects and displays each one within TabViews
 **************************************************************************************************/
public class PagerAdapter extends FragmentStatePagerAdapter {
    /***********************************************************************************************
     * GLOBAL VARIABLES
     **********************************************************************************************/
    /**Public**/
    public static final String TITLE = "main";

    /**Private**/
    private Map<String, Fragment> fragMap;

    /***********************************************************************************************
     * CONSTRUCTORS
     **********************************************************************************************/
    public PagerAdapter(FragmentManager fragManager) {
        super(fragManager);

        fragMap = new HashMap<>();
        addFragments();
    }

    /***********************************************************************************************
     * OVERRIDE METHODS
     **********************************************************************************************/
    @Override
    public CharSequence getPageTitle(int position) {
        return fragMap.keySet().toArray()[position].toString();
    }

    @Override
    public Fragment getItem(int position) {return fragMap.get(getPageTitle(position).toString());}

    @Override
    public int getCount() {return fragMap.size();}

    /***********************************************************************************************
     * PRIVATE METHODS
     **********************************************************************************************/
    private void addFragments() {fragMap.put(TITLE, new FragmentMain());}
}
