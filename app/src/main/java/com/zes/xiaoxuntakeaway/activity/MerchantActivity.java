package com.zes.xiaoxuntakeaway.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.zes.bundle.activity.BaseActivity;
import com.zes.xiaoxuntakeaway.R;
import com.zes.xiaoxuntakeaway.adapter.MerchantFragmentPageAdapter;
import com.zes.xiaoxuntakeaway.bean.TabFragmentData;
import com.zes.xiaoxuntakeaway.fragment.CommentFragment;
import com.zes.xiaoxuntakeaway.fragment.MainFragment;
import com.zes.xiaoxuntakeaway.fragment.MenuFragment;
import com.zes.xiaoxuntakeaway.fragment.MerchantFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zes on 16-2-19.
 */
public class MerchantActivity extends BaseActivity {
    @Bind(R.id.iv_login_back)
    ImageView ivLoginBack;
    @Bind(R.id.tv_merchant_activity_merchant_name)
    TextView tvMerchantActivityMerchantName;
    @Bind(R.id.iv_merchant_activity_search)
    ImageView ivMerchantActivitySearch;
    @Bind(R.id.tb_merchant_activity)
    TabLayout tbMerchantActivity;
    @Bind(R.id.vp_merchant_activity)
    ViewPager vpMerchantActivity;

    private List<TabFragmentData> mTabFragmentDatas;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_merchant;
    }

    /**
     * 初始化数据
     *
     * @param savedInstanceState
     */
    @Override
    protected void initData(Bundle savedInstanceState) {

        String merchantId = getIntent().getStringExtra(MainFragment.MERCHANT_ID);
        String merchantName = getIntent().getStringExtra(MainFragment.MERCHANT_NAME);
        if (!TextUtils.isEmpty(merchantName)) {
            tvMerchantActivityMerchantName.setText(merchantName);
        }
        mTabFragmentDatas = new ArrayList<>();
        TabFragmentData menuTab = new TabFragmentData();
        if (!TextUtils.isEmpty(merchantId))
            menuTab.setBaseFragment(MenuFragment.newInstance(merchantId));
        menuTab.setPageTitle("商品");
        mTabFragmentDatas.add(menuTab);

        TabFragmentData commentTab = new TabFragmentData();
        commentTab.setBaseFragment(new CommentFragment());
        commentTab.setPageTitle("评价");
        mTabFragmentDatas.add(commentTab);

        TabFragmentData merchantTab = new TabFragmentData();
        merchantTab.setBaseFragment(MerchantFragment.newInstance(merchantId));
        merchantTab.setPageTitle("商家");
        mTabFragmentDatas.add(merchantTab);

        vpMerchantActivity.setAdapter(new MerchantFragmentPageAdapter(getSupportFragmentManager(), mTabFragmentDatas));
        tbMerchantActivity.setupWithViewPager(vpMerchantActivity);


    }

    /**
     * 初始化视图
     */
    @Override
    protected void initView() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
