package com.zes.xiaoxuntakeaway.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zes.xiaoxuntakeaway.bean.TabFragmentData;

import java.util.List;

/**
 * Created by zes on 15-11-28.
 */
public class MerchantFragmentPageAdapter extends FragmentPagerAdapter {

    private List<TabFragmentData> mBaseFragmentList;

    public MerchantFragmentPageAdapter(FragmentManager fm, List<TabFragmentData> baseFragmentList) {
        super(fm);
        this.mBaseFragmentList = baseFragmentList;
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {
        return mBaseFragmentList.get(position).getBaseFragment();
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return mBaseFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mBaseFragmentList.get(position).getPageTitle();
    }

}
