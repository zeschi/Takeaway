package com.zes.bundle.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.ButterKnife;


/**
 * BaseActivity
 */
public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getContentViewId());

        ButterKnife.bind(this);
        initView();
        initData(savedInstanceState);

    }


    protected abstract int getContentViewId();

    /**
     * 初始化数据
     */
    protected abstract void initData(Bundle savedInstanceState);

    /**
     * 初始化视图
     */
    protected abstract void initView();

    public void setContentViewWithoutInject(int layoutResId) {
        super.setContentView(layoutResId);
    }


    protected int[] getStartingLocation(View view) {
        int[] startingLocation = new int[2];
        view.getLocationOnScreen(startingLocation);
        startingLocation[0] += view.getWidth() / 2;
        return startingLocation;
    }

    /**
     * @param text
     */
    protected void showToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    /**
     * Activity跳转
     *
     * @param context
     * @param targetActivity
     * @param bundle
     */
    public void redictToActivity(Context context, Class<?> targetActivity, Bundle bundle) {
        Intent intent = new Intent(context, targetActivity);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * Activity跳转
     *
     * @param context
     * @param targetActivity
     */
    public void redictToActivity(Context context, Class<?> targetActivity) {
        Intent intent = new Intent(context, targetActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    /**
     * 获取EditText的输入 如果不是EditText类型，返回null
     *
     * @param view
     * @return
     */
    public String getEditTextString(View view) {
        if (view instanceof EditText) {
            return ((EditText) view).getText().toString();
        }
        return null;
    }


}
