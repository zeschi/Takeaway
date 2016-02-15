package com.zes.xiaoxuntakeaway.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zes.bundle.activity.BaseActivity;
import com.zes.bundle.utils.MKLog;
import com.zes.bundle.view.ClearEditText;
import com.zes.xiaoxuntakeaway.R;
import com.zes.xiaoxuntakeaway.bean.ResultDataStringCallBack;
import com.zes.xiaoxuntakeaway.bean.ResultDataInfo;
import com.zes.xiaoxuntakeaway.controller.UserController;

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
        UserController.login(getEditTextString(etLoginUserAccount), getEditTextString(etLoginPwd), new ResultDataStringCallBack() {
            @Override
            public void onError(Call call, Exception e) {
            }

            @Override
            public void onResponse(ResultDataInfo<String> response) {
                MKLog.e(response.getRetmsg());
                showToast(response.getRetmsg());
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
