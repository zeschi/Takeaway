package com.zes.xiaoxuntakeaway.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.zes.bundle.activity.BaseActivity;
import com.zes.bundle.view.ClearEditText;
import com.zes.xiaoxuntakeaway.R;
import com.zes.xiaoxuntakeaway.bean.ResultDataInfo;
import com.zes.xiaoxuntakeaway.bean.ResultDataStringCallBack;
import com.zes.xiaoxuntakeaway.constant.Const;
import com.zes.xiaoxuntakeaway.controller.UserController;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by zes on 16-2-15.
 */
public class RegisterActivity extends BaseActivity {
    @Bind(R.id.iv_login_back)
    ImageView ivLoginBack;
    @Bind(R.id.et_register_account)
    ClearEditText etRegisterAccount;
    @Bind(R.id.et_register_password)
    ClearEditText etRegisterPassword;
    @Bind(R.id.btn_register_verification_code)
    Button btnRegisterVerificationCode;
    @Bind(R.id.btn_register_register)
    Button btnRegisterRegister;
    @Bind(R.id.et_register_verification_code)
    ClearEditText etRegisterVerificationCode;
    /**
     * 点击获取验证码的计时器
     */
    private TimeCount mTimer;
    /**
     * 倒计时多少分钟
     */
    private final static int COUNT_DOWN_MIN = 2;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_register;
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

    }


    @OnClick({R.id.btn_register_verification_code, R.id.btn_register_register})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register_register:
                onRegisterEvent();
                break;
            case R.id.btn_register_verification_code:
                onGetVerificationCodeEvent();
                break;
        }
    }

    /**
     * 注册
     */
    private void onRegisterEvent() {


    }

    /**
     * 　获取验证码
     */
    private void onGetVerificationCodeEvent() {
        UserController.getVerificationCode(new ResultDataStringCallBack() {
            @Override
            public void onError(Call call, Exception e) {

            }

            @Override
            public void onResponse(ResultDataInfo<String> response) {
                if (response == null) {
                    showToast("未知错误");
                    return;
                }
                if (response.getCode() == Const.CODE_REGISTER_VERIFICATION_CODE_SUCCESS) {
                    mTimer = new TimeCount(COUNT_DOWN_MIN * 60 * 1000, 1000);
                    mTimer.start();
                    etRegisterVerificationCode.setText(response.getData());
                } else {
                    showToast("未知错误");
                }
                // if (response.getCode()==)
//                showToast(response.getData());
            }
        });
    }

    /**
     * 倒计时内部类
     */
    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);// 参数：总时长，计时间隔
        }

        // 计时完毕时触发
        @Override
        public void onFinish() {
            btnRegisterVerificationCode.setText("重新验证");
//            setVerifyBtnEnabled(true);
            btnRegisterVerificationCode.setClickable(true);
        }

        // 计时过程显示
        @Override
        public void onTick(long millisUntilFinished) {
            btnRegisterVerificationCode.setText(millisUntilFinished / 1000 + "秒");
            btnRegisterVerificationCode.setClickable(false);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
