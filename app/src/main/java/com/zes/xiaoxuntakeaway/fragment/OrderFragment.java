package com.zes.xiaoxuntakeaway.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.snappydb.SnappydbException;
import com.zes.bundle.fragment.BaseFragment;
import com.zes.xiaoxuntakeaway.R;
import com.zes.xiaoxuntakeaway.adapter.OrderAdapter;
import com.zes.xiaoxuntakeaway.bean.Order;
import com.zes.xiaoxuntakeaway.bean.OrderListCallback;
import com.zes.xiaoxuntakeaway.bean.ResultDataInfo;
import com.zes.xiaoxuntakeaway.bean.User;
import com.zes.xiaoxuntakeaway.controller.OrderController;
import com.zes.xiaoxuntakeaway.database.DbHelper;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by zes on 16-1-15.
 */
public class OrderFragment extends BaseFragment {
    private List<String> lists = new ArrayList<>();
    private OrderAdapter mOrderAdapter;
    private ListView mOrderLv;
    private User mUser;

    public OrderFragment() {
        for (int i = 0; i < 10; i++) {
            lists.add("");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_order, container, false);
        mOrderLv = (ListView) rootView.findViewById(R.id.lv_order_list);

        try {
            mUser = DbHelper.getSnappyDb().get("userInfo", User.class);
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
        onGetOrderEvent();
        return rootView;
    }

    private void onGetOrderEvent() {
        OrderController.getOrderByUserId(mUser.getUser_id(), new OrderListCallback() {
                    @Override
                    public void onError(Call call, Exception e) {

                    }

                    @Override
                    public void onResponse(ResultDataInfo<List<Order>> response) {

                        if (response == null || response.getData() == null) {
                            return;
                        }

                        mOrderAdapter = new OrderAdapter(getActivity(), response.getData(), R.layout.item_order);
                        mOrderLv.setAdapter(mOrderAdapter);
//                        List<Menu> resultDataInfo = new Gson().fromJson(response.getData().get(0).getMenu_list(), new TypeToken<List<Menu>>() {
//                        }.getType());

                    }
                }
        );
    }

}
