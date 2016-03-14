package com.zes.xiaoxuntakeaway.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.zes.xiaoxuntakeaway.R;
import com.zes.xiaoxuntakeaway.adapter.MerchantAdapter;
import com.zes.xiaoxuntakeaway.adapter.OrderAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zes on 16-1-14.
 */
public class TabFragment extends Fragment {
    private String mTitle = "Default";
    private List<String> lists = new ArrayList<>();
    private MerchantAdapter mAdapter;
    private OrderAdapter mOrderAdapter;
    private ListView mMerchantLv;

    public TabFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        for (int i = 0; i < 10; i++) {
            lists.add("");
        }
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        mMerchantLv = (ListView) rootView.findViewById(R.id.lv_merchant_main);

//        mAdapter = new MerchantAdapter(getActivity(), lists, R.layout.item_merchant);
    
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
