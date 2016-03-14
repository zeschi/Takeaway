package com.zes.xiaoxuntakeaway.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.snappydb.DB;
import com.snappydb.SnappydbException;
import com.zes.bundle.activity.BaseActivity;
import com.zes.bundle.utils.MKLog;
import com.zes.bundle.view.ClearEditText;
import com.zes.xiaoxuntakeaway.R;
import com.zes.xiaoxuntakeaway.bean.ResultDataInfo;
import com.zes.xiaoxuntakeaway.bean.User;
import com.zes.xiaoxuntakeaway.bean.UserCallback;
import com.zes.xiaoxuntakeaway.constant.Const;
import com.zes.xiaoxuntakeaway.controller.UserController;
import com.zes.xiaoxuntakeaway.database.DbHelper;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by zes on 16-2-15.
 */
public class LoginActivity extends BaseActivity {

    @Bind(R.id.tv_login_register)
    TextView registerTv;
    @Bind(R.id.iv_login_back)
    ImageView ivLoginBack;
    @Bind(R.id.btn_login_login)
    Button btnLoginLogin;
    @Bind(R.id.et_login_user_account)
    ClearEditText etLoginUserAccount;
    @Bind(R.id.et_login_pwd)
    ClearEditText etLoginPwd;

    private DB mSnappyDb;

    private Intent mRegisterIntent;

    @Override
    protected int getContentViewId() {

        return R.layout.activity_login;
    }

    /**
     * 初始化数据
     *
     * @param savedInstanceState
     */
    @Override
    protected void initData(Bundle savedInstanceState) {
        mRegisterIntent = new Intent(this, RegisterActivity.class);
        mSnappyDb = DbHelper.getSnappyDb();

    }

    /**
     * 初始化视图
     */
    @Override
    protected void initView() {


    }

    @OnClick({R.id.tv_login_register, R.id.btn_login_login, R.id.iv_login_back})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                break;
            case R.id.tv_login_register:
                startActivity(mRegisterIntent);

                break;
            case R.id.btn_login_login:
                onLoginEvent();

                break;
        }
    }

    /**
     * 登录事件
     */
    private void onLoginEvent() {
        UserController.login(getEditTextString(etLoginUserAccount), getEditTextString(etLoginPwd), new UserCallback() {
            @Override
            public void onError(Call call, Exception e) {

            }

            @Override
            public void onResponse(ResultDataInfo<User> response) {
                if (response == null) {
                    showToast("未知错误");
                    return;
                }
                if (response.getCode() == Const.CODE_LOGIN_LOGIN_SUCCESS) {
                    showToast(response.getRetmsg());
                    //保存登录账号信息
                    if (mSnappyDb != null) {
                        try {
                            mSnappyDb.put("account", getEditTextString(etLoginUserAccount));
                            mSnappyDb.put("password", getEditTextString(etLoginPwd));
                            MKLog.e("userInfo", response.getData().getUser_account());
                            mSnappyDb.put("userInfo", response.getData());
                        } catch (SnappydbException e) {
                            e.printStackTrace();
                        }
                    }
                    finish();
//                    redictToActivity(LoginActivity.this, MainActivity.class);
                } else {
                    showToast(response.getRetmsg());
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
