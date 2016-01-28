package com.zes.xiaoxuntakeaway.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.zes.bundle.fragment.BaseFragment;
import com.zes.xiaoxuntakeaway.R;
import com.zes.xiaoxuntakeaway.adapter.MerchantAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zes on 16-1-15.
 */
public class MainFragment extends BaseFragment {
    private List<String> lists = new ArrayList<>();
    private MerchantAdapter mAdapter;
    private ListView mMerchantLv;

    public MainFragment() {
        for (int i = 0; i < 10; i++) {
            lists.add("");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        mMerchantLv = (ListView) rootView.findViewById(R.id.lv_merchant_main);

        mAdapter = new MerchantAdapter(getActivity(), lists, R.layout.item_merchant);
        //mOrderAdapter = new OrderAdapter(getActivity(), lists, R.layout.item_order);
        mMerchantLv.setAdapter(mAdapter);
//        if (getArguments() != null) {
//            mTitle = getArguments().getString("title");
//        }
//
//        TextView textView = new TextView(getActivity());
//        textView.setTextSize(20);
//        textView.setBackgroundColor(Color.parseColor("#ffffffff"));
//        textView.setGravity(Gravity.CENTER);
//        textView.setText(mTitle);
        return rootView;
    }

}
