package com.zes.xiaoxuntakeaway.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zes.bundle.fragment.BaseFragment;
import com.zes.bundle.utils.MKLog;
import com.zes.xiaoxuntakeaway.R;
import com.zes.xiaoxuntakeaway.activity.MerchantActivity;
import com.zes.xiaoxuntakeaway.adapter.MerchantAdapter;
import com.zes.xiaoxuntakeaway.bean.Merchant;
import com.zes.xiaoxuntakeaway.bean.MerchantListCallBack;
import com.zes.xiaoxuntakeaway.bean.ResultDataInfo;
import com.zes.xiaoxuntakeaway.controller.MerchantController;

import java.util.List;

import okhttp3.Call;

/**
 * Created by zes on 16-1-15.
 */
public class MainFragment extends BaseFragment {
    private MerchantAdapter mAdapter;
    private ListView mMerchantLv;
    private Intent mIntent;
    public static String MERCHANT_ID = "merchant_id";
    public static String MERCHANT_NAME = "merchant_name";
    public static String MERCHANT_START_PRICE = "start_price";

    public MainFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        mMerchantLv = (ListView) rootView.findViewById(R.id.lv_merchant_main);
        mIntent = new Intent(getActivity(), MerchantActivity.class);

//        mMerchantLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });
        MerchantController.getMerchantList(new MerchantListCallBack() {
            @Override
            public void onError(Call call, Exception e) {

            }

            @Override
            public void onResponse(ResultDataInfo<List<Merchant>> response) {
                if (response == null || response.getData() == null)
                    return;
                else {
                    if (response.getCode() == 1) {
                        MKLog.e("data", response.getData().toString());
                        mAdapter = new MerchantAdapter(getActivity(), response.getData(), R.layout.item_merchant);
                        mMerchantLv.setAdapter(mAdapter);
                        onMerchantListItemClickEvent(response);
                    }
                }

            }
        });

        return rootView;
    }

    private void onMerchantListItemClickEvent(final ResultDataInfo<List<Merchant>> response) {

        mMerchantLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mIntent.putExtra(MainFragment.MERCHANT_ID, response.getData().get(position).getMerchant_id());
                mIntent.putExtra(MainFragment.MERCHANT_NAME, response.getData().get(position).getMerchant_name());
                mIntent.putExtra(MainFragment.MERCHANT_START_PRICE, response.getData().get(position).getMerchant_start_price());
                startActivity(mIntent);
            }
        });


    }


}
