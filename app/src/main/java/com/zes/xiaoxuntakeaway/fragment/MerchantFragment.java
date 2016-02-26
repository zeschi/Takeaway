package com.zes.xiaoxuntakeaway.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zes.bundle.fragment.BaseFragment;
import com.zes.xiaoxuntakeaway.R;
import com.zes.xiaoxuntakeaway.bean.Merchant;
import com.zes.xiaoxuntakeaway.bean.MerchantCallback;
import com.zes.xiaoxuntakeaway.bean.ResultDataInfo;
import com.zes.xiaoxuntakeaway.controller.MerchantController;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by zes on 16-2-19.
 */
public class MerchantFragment extends BaseFragment {

    @Bind(R.id.iv_merchant_fragment_portrait)
    ImageView ivMerchantFragmentPortrait;
    @Bind(R.id.tv_merchant_fragment_name)
    TextView tvMerchantFragmentName;
    @Bind(R.id.iv_merchant_fragment_collection)
    ImageView ivMerchantFragmentCollection;
    @Bind(R.id.tv_merchant_fragment_start_price)
    TextView tvMerchantFragmentStartPrice;
    @Bind(R.id.tv_merchant_fragment_delivery_price)
    TextView tvMerchantFragmentDeliveryPrice;
    @Bind(R.id.tv_merchant_fragment_average_delivery_time)
    TextView tvMerchantFragmentAverageDeliveryTime;
    @Bind(R.id.tv_merchant_fragment_announcement)
    TextView tvMerchantFragmentAnnouncement;
    @Bind(R.id.tv_merchant_fragment_address)
    TextView tvMerchantFragmentAddress;
    @Bind(R.id.tv_merchant_fragment_business_hours)
    TextView tvMerchantFragmentBusinessHours;

    public static MerchantFragment newInstance(String merchantId) {

        Bundle args = new Bundle();
        args.putString(MainFragment.MERCHANT_ID, merchantId);
        MerchantFragment merchantFragment = new MerchantFragment();
        merchantFragment.setArguments(args);
        return merchantFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_merchant, container, false);
        ButterKnife.bind(this, rootView);
        String merchantId = getArguments().getString(MainFragment.MERCHANT_ID);
        getMerchantInfo(merchantId);

        return rootView;
    }

    private void getMerchantInfo(String merchantId) {
        if (!TextUtils.isEmpty(merchantId)) {
            MerchantController.getMerchantById(merchantId, new MerchantCallback() {
                @Override
                public void onError(Call call, Exception e) {

                }

                @Override
                public void onResponse(ResultDataInfo<Merchant> response) {

                    if (response == null || response.getData() == null)
                        return;
                    tvMerchantFragmentAddress.setText("地址:" + response.getData().getMerchant_address());
                    tvMerchantFragmentAnnouncement.setText(response.getData().getMerchant_announcement());
                    tvMerchantFragmentDeliveryPrice.setText(response.getData().getMerchant_delivery_price() + "元");
                    tvMerchantFragmentName.setText(response.getData().getMerchant_name());
                    tvMerchantFragmentAverageDeliveryTime.setText(response.getData().getMerchant_average_delivery_time());
                    tvMerchantFragmentBusinessHours.setText("营业时间:" + response.getData().getMerchant_business_hours());
                    tvMerchantFragmentStartPrice.setText(response.getData().getMerchant_start_price() + "元");
                    Glide.with(getActivity()).load(response.getData().getMerchant_portrait()).
                            into(ivMerchantFragmentPortrait);
                }
            });
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


}
