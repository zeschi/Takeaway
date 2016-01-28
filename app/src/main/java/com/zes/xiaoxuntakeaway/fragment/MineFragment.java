package com.zes.xiaoxuntakeaway.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zes.bundle.fragment.BaseFragment;
import com.zes.xiaoxuntakeaway.R;

/**
 * Created by zes on 16-1-15.
 */
public class MineFragment extends BaseFragment {

    private ImageView mPotraitIv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_mine, container, false);
        mPotraitIv = (ImageView) rootView.findViewById(R.id.iv_mine_portrait);
        return rootView;
    }
}
