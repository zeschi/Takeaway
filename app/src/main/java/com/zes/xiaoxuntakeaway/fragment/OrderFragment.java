package com.zes.xiaoxuntakeaway.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;
import com.snappydb.SnappydbException;
import com.zes.bundle.fragment.BaseFragment;
import com.zes.bundle.utils.GsonUtil;
import com.zes.bundle.utils.MKNetworkTool;
import com.zes.xiaoxuntakeaway.R;
import com.zes.xiaoxuntakeaway.adapter.OrderAdapter;
import com.zes.xiaoxuntakeaway.bean.Order;
import com.zes.xiaoxuntakeaway.bean.OrderListCallback;
import com.zes.xiaoxuntakeaway.bean.ResultDataInfo;
import com.zes.xiaoxuntakeaway.bean.User;
import com.zes.xiaoxuntakeaway.controller.OrderController;
import com.zes.xiaoxuntakeaway.database.DbHelper;

import java.util.List;

import okhttp3.Call;

/**
 * Created by zes on 16-1-15.
 */
public class OrderFragment extends BaseFragment {
    private OrderAdapter mOrderAdapter;
    private ListView mOrderLv;
    private User mUser;
    public static final String ORDER_LIST = "userId_orderList";
    private List<Order> mOrderList;

    public OrderFragment() {

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

        try {
            String orderStr = DbHelper.getSnappyDb().get(mUser.getUser_id() + ORDER_LIST);
            if (!TextUtils.isEmpty(orderStr)) {
                mOrderList = GsonUtil.getGson().fromJson(orderStr, new TypeToken<List<Order>>() {
                }.getType());
            }
        } catch (SnappydbException e) {
            e.printStackTrace();
        }

        if (mOrderList != null && !MKNetworkTool.isConnectInternet(getActivity())) {
            mOrderAdapter = new OrderAdapter(getActivity(), mOrderList, R.layout.item_order);
            mOrderLv.setAdapter(mOrderAdapter);
        } else {
            onGetOrderFromNetEvent();
        }
        return rootView;
    }

    private void onGetOrderFromNetEvent() {
        OrderController.getOrderByUserId(mUser.getUser_id(), new OrderListCallback() {
                    @Override
                    public void onError(Call call, Exception e) {

                    }

                    @Override
                    public void onResponse(ResultDataInfo<List<Order>> response) {

                        if (response == null || response.getData() == null) {
                            return;
                        }
                        try {
                            DbHelper.getSnappyDb().put(mUser.getUser_id() + ORDER_LIST, GsonUtil.getGson().toJson(response.getData()));
                        } catch (SnappydbException e) {
                            e.printStackTrace();
                        }
                        mOrderAdapter = new OrderAdapter(getActivity(), response.getData(), R.layout.item_order);
                        mOrderLv.setAdapter(mOrderAdapter);

                    }
                }
        );
    }

}
