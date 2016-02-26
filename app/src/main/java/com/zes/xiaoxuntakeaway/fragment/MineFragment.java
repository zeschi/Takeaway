package com.zes.xiaoxuntakeaway.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.snappydb.SnappydbException;
import com.zes.bundle.fragment.BaseFragment;
import com.zes.bundle.utils.GlideCircleTransform;
import com.zes.xiaoxuntakeaway.R;
import com.zes.xiaoxuntakeaway.activity.LoginActivity;
import com.zes.xiaoxuntakeaway.bean.User;
import com.zes.xiaoxuntakeaway.database.DbHelper;

/**
 * Created by zes on 16-1-15.
 */
public class MineFragment extends BaseFragment implements View.OnClickListener {

    private ImageView mPortraitIv;
    private RelativeLayout mMineInfoRl;
    private Intent mLoginIntent;
    private User mUser;
    private TextView mUseInfoTv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_mine, container, false);
        mPortraitIv = (ImageView) rootView.findViewById(R.id.iv_mine_portrait);
        mMineInfoRl = (RelativeLayout) rootView.findViewById(R.id.rl_mine_info);
        mMineInfoRl.setOnClickListener(this);
        mUseInfoTv = (TextView) rootView.findViewById(R.id.tv_mine_user_info);
        initData();
        Glide.with(getActivity()).load("http://pic25.nipic.com/20121206/6789926_185118320000_2.jpg").
                transform(new GlideCircleTransform(getActivity())).into(mPortraitIv);
        return rootView;
    }

    /**
     * 初始化数据
     */
    private void initData() {

        mLoginIntent = new Intent(getActivity(), LoginActivity.class);

        try {
            mUser = DbHelper.getSnappyDb().getObject("userInfo", User.class);
            if (mUser != null) {

                mUseInfoTv.setText(mUser.getUser_account());

            }
        } catch (SnappydbException e) {
            e.printStackTrace();
        }


    }


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_mine_info:
                startActivity(mLoginIntent);
                break;
        }
    }
}
