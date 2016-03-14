package com.zes.xiaoxuntakeaway.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.snappydb.DB;
import com.snappydb.SnappydbException;
import com.zes.bundle.activity.BaseActivity;
import com.zes.bundle.utils.MKLog;
import com.zes.xiaoxuntakeaway.R;
import com.zes.xiaoxuntakeaway.bean.Address;
import com.zes.xiaoxuntakeaway.bean.ResultDataInfo;
import com.zes.xiaoxuntakeaway.bean.ResultDataStringCallBack;
import com.zes.xiaoxuntakeaway.bean.User;
import com.zes.xiaoxuntakeaway.controller.OrderController;
import com.zes.xiaoxuntakeaway.database.DbHelper;
import com.zes.xiaoxuntakeaway.fragment.MenuFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by zes on 16-3-12.
 */
public class ConfirmOrderActivity extends BaseActivity {
    @Bind(R.id.iv_login_back)
    ImageView ivLoginBack;
    @Bind(R.id.tv_merchant_activity_merchant_name)
    TextView tvMerchantActivityMerchantName;
    @Bind(R.id.iv_confirm_order_address)
    ImageView ivConfirmOrderAddress;
    @Bind(R.id.tv_confirm_order_user_info)
    TextView tvConfirmOrderUserInfo;
    @Bind(R.id.rl_confirm_order_address)
    RelativeLayout rlConfirmOrderAddress;
    @Bind(R.id.tv_confirm_order_tips)
    TextView tvConfirmOrderTips;
    @Bind(R.id.tv_confirm_order_confirm)
    TextView tvConfirmOrderConfirm;
    @Bind(R.id.tv_confirm_order_address)
    TextView tvConfirmOrderAddress;

    private Intent mEditAddressIntent;

    private Intent mMainActivity;
    private DB mSnappyDb;

    private Address mAddress;
    private User mUser;
    private String mMerchantId = "";
    private String mMerchantListData;
    private String mAddressId = "";

    @Override
    protected int getContentViewId() {
        return R.layout.activity_confirm_order;
    }

    /**
     * 初始化数据
     *
     * @param savedInstanceState
     */
    @Override
    protected void initData(Bundle savedInstanceState) {
        mEditAddressIntent = new Intent(this, EditAddressActivity.class);
        mSnappyDb = DbHelper.getSnappyDb();
        mAddress = (Address) getIntent().getSerializableExtra(EditAddressActivity.ADDRESS);
        mMerchantId = getIntent().getStringExtra(MenuFragment.MERCHANT_ID);
        mMerchantListData = getIntent().getStringExtra(MenuFragment.MERCHANT_LIST_DATA);
        if (mAddress == null) {
            try {
                mAddress = mSnappyDb.get("address", Address.class);
                mUser = mSnappyDb.get("userInfo", User.class);
                mAddressId = mSnappyDb.get("addressId");
            } catch (SnappydbException e) {
                e.printStackTrace();
            }
        }
        if (mAddress != null) {
            tvConfirmOrderUserInfo.setText(mAddress.getUserRealName() + " 先生 " + mAddress.getUserPhone());
            tvConfirmOrderAddress.setText(mAddress.getReceiptAddress() + " " + mAddress.getDoorPlate());
        }


    }

    /**
     * 初始化视图
     */
    @Override
    protected void initView() {

    }


    @OnClick({R.id.rl_confirm_order_address, R.id.tv_confirm_order_confirm})
    protected void click(View view) {

        switch (view.getId()) {
            case R.id.rl_confirm_order_address:
                mEditAddressIntent.putExtra(EditAddressActivity.ADDRESS, mAddress);
                startActivity(mEditAddressIntent);
                break;
            case R.id.tv_confirm_order_confirm:
                confirmOrder();
                break;
        }

    }

    /**
     * 确认订单
     */
    private void confirmOrder() {

        MKLog.e("---------" + mUser.getUser_id());
        OrderController.createOrder(mUser.getUser_id(), mMerchantId, mMerchantListData, mAddressId,
                new ResultDataStringCallBack() {
                    @Override
                    public void onError(Call call, Exception e) {

                    }

                    @Override
                    public void onResponse(ResultDataInfo<String> response) {
                        if (response != null && response.getCode() == 1) {
                            Intent intent = new Intent(ConfirmOrderActivity.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.putExtra("order", true);
                            startActivity(intent);
                        }
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
