package com.zes.xiaoxuntakeaway.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;
import com.snappydb.SnappydbException;
import com.zes.bundle.fragment.BaseFragment;
import com.zes.bundle.utils.GsonUtil;
import com.zes.bundle.utils.MKNetworkTool;
import com.zes.xiaoxuntakeaway.R;
import com.zes.xiaoxuntakeaway.activity.MerchantActivity;
import com.zes.xiaoxuntakeaway.adapter.MerchantAdapter;
import com.zes.xiaoxuntakeaway.bean.Merchant;
import com.zes.xiaoxuntakeaway.bean.MerchantListCallBack;
import com.zes.xiaoxuntakeaway.bean.ResultDataInfo;
import com.zes.xiaoxuntakeaway.controller.MerchantController;
import com.zes.xiaoxuntakeaway.database.DbHelper;

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
    public static final String MERCHANT_LIST = "MERCHANT_LIST";

    private List<Merchant> mMerchantList;

    public MainFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        mMerchantLv = (ListView) rootView.findViewById(R.id.lv_merchant_main);
        mIntent = new Intent(getActivity(), MerchantActivity.class);

        try {
            String merchantStr = DbHelper.getSnappyDb().get(MERCHANT_LIST);
            if (!TextUtils.isEmpty(merchantStr))
                mMerchantList = GsonUtil.getGson().fromJson(merchantStr, new TypeToken<List<Merchant>>() {
                }.getType());
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
        if (mMerchantList != null && !MKNetworkTool.isMobileConnected(getActivity())) {
            mAdapter = new MerchantAdapter(getActivity(), mMerchantList, R.layout.item_merchant);
            mMerchantLv.setAdapter(mAdapter);
            onMerchantListItemClickEvent(mMerchantList);
        } else {
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
                            try {
                                DbHelper.getSnappyDb().put(MERCHANT_LIST, GsonUtil.getGson().toJson(response.getData()));
                            } catch (SnappydbException e) {
                                e.printStackTrace();
                            }
                            mAdapter = new MerchantAdapter(getActivity(), response.getData(), R.layout.item_merchant);
                            mMerchantLv.setAdapter(mAdapter);
                            onMerchantListItemClickEvent(response.getData());
                        }
                    }

                }
            });
        }


        return rootView;
    }

    private void onMerchantListItemClickEvent(final List<Merchant> merchantList) {

        mMerchantLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mIntent.putExtra(MainFragment.MERCHANT_ID, merchantList.get(position).getMerchant_id());
                mIntent.putExtra(MainFragment.MERCHANT_NAME, merchantList.get(position).getMerchant_name());
                mIntent.putExtra(MainFragment.MERCHANT_START_PRICE, merchantList.get(position).getMerchant_start_price());
                startActivity(mIntent);
            }
        });


    }


}
