package com.zes.xiaoxuntakeaway.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.snappydb.DB;
import com.snappydb.SnappydbException;
import com.zes.bundle.activity.BaseActivity;
import com.zes.xiaoxuntakeaway.R;
import com.zes.xiaoxuntakeaway.bean.Address;
import com.zes.xiaoxuntakeaway.bean.ResultDataInfo;
import com.zes.xiaoxuntakeaway.bean.ResultDataStringCallBack;
import com.zes.xiaoxuntakeaway.bean.User;
import com.zes.xiaoxuntakeaway.controller.AddressController;
import com.zes.xiaoxuntakeaway.database.DbHelper;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by zes on 16-1-27.
 */
public class EditAddressActivity extends BaseActivity {
    @Bind(R.id.iv_login_back)
    ImageView ivLoginBack;
    @Bind(R.id.tv_merchant_activity_merchant_name)
    TextView tvMerchantActivityMerchantName;
    @Bind(R.id.tv_confirm_order_save)
    TextView tvConfirmOrderSave;
    @Bind(R.id.et_edit_address_receipt)
    EditText etEditAddressReceipt;
    @Bind(R.id.et_edit_address_door_plate)
    EditText etEditAddressDoorPlate;
    @Bind(R.id.et_edit_address_user_name)
    EditText etEditAddressUserName;
    @Bind(R.id.rb_edit_address_male)
    RadioButton rbEditAddressMale;
    @Bind(R.id.rb_edit_address_female)
    RadioButton rbEditAddressFemale;
    @Bind(R.id.et_edit_address_user_phone)
    EditText etEditAddressUserPhone;

    private User mUser;
    private Address mAddress;
    public static String ADDRESS = "address";
    private String mAddressId;
    private DB mSnappyDb;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_edit_address;
    }

    /**
     * 初始化数据
     *
     * @param savedInstanceState
     */
    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    /**
     * 初始化视图
     */
    @Override
    protected void initView() {
        mSnappyDb = DbHelper.getSnappyDb();

        try {
            mUser = mSnappyDb.getObject("userInfo", User.class);
            mAddress = (Address) getIntent().getSerializableExtra(EditAddressActivity.ADDRESS);
        } catch (SnappydbException e) {
            e.printStackTrace();
        }

        if (mAddress != null) {
            etEditAddressUserName.setText(mAddress.getUserRealName());
            etEditAddressDoorPlate.setText(mAddress.getDoorPlate());
            etEditAddressReceipt.setText(mAddress.getReceiptAddress());
            etEditAddressUserPhone.setText(mAddress.getUserPhone());

        }
    }


    @OnClick({R.id.tv_confirm_order_save})
    protected void click(View view) {

        switch (view.getId()) {

            case R.id.tv_confirm_order_save:
                if (mAddress == null) {
                    saveAddress();
                } else {
                    changeAddress();
                }
                break;
        }
    }

    /**
     * 获取输入的地址信息
     */
    private void getAddressInfo() {
        String userName = getEditTextString(etEditAddressUserName);
        String userPhone = getEditTextString(etEditAddressUserPhone);
        String doorPlate = getEditTextString(etEditAddressDoorPlate);
        String receipt = getEditTextString(etEditAddressReceipt);
        String userId = "";

        if (mUser != null) {
            userId = mUser.getUser_id();
        }

        mAddress.setUserId(userId);
        mAddress.setDoorPlate(doorPlate);
        mAddress.setReceiptAddress(receipt);
        mAddress.setUserRealName(userName);
        mAddress.setUserPhone(userPhone);
        try {
            DbHelper.getSnappyDb().put("address", mAddress);
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改地址
     */
    private void changeAddress() {
        getAddressInfo();
        try {
            mAddressId = mSnappyDb.get("addressId");
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
        AddressController.changeAddress(mAddress, mAddressId, new ResultDataStringCallBack() {
            @Override
            public void onError(Call call, Exception e) {

            }

            @Override
            public void onResponse(ResultDataInfo<String> response) {
                if (response.getCode() == 1) {
                    backConfirmActivity();
                }
            }
        });
    }

    /**
     * 保存地址
     */
    private void saveAddress() {
        mAddress = new Address();

        getAddressInfo();

        AddressController.saveAddress(mAddress, new ResultDataStringCallBack() {
            @Override
            public void onError(Call call, Exception e) {

            }

            @Override
            public void onResponse(ResultDataInfo<String> response) {
                if (response.getCode() == 1) {
                    try {
                        mSnappyDb.put("address", mAddress);
                        mSnappyDb.put("addressId", response.getData());
                    } catch (SnappydbException e) {
                        e.printStackTrace();
                    }
                    backConfirmActivity();

                }
            }
        });
    }

    /**
     * 返回确认订单
     */
    private void backConfirmActivity() {
        Intent intent = new Intent(EditAddressActivity.this, ConfirmOrderActivity.class);
        intent.putExtra(EditAddressActivity.ADDRESS, mAddress);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
