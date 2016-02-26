package com.zes.xiaoxuntakeaway.bean;


import com.zes.bundle.fragment.BaseFragment;

/**
 * Created by zes on 15-11-30.
 */
public class TabFragmentData {
    private BaseFragment baseFragment;
    private String pageTitle;


    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }


    public BaseFragment getBaseFragment() {
        return baseFragment;
    }

    public void setBaseFragment(BaseFragment baseFragment) {
        this.baseFragment = baseFragment;
    }
}
