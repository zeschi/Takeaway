package com.zes.xiaoxuntakeaway.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zes.bundle.fragment.BaseFragment;
import com.zes.xiaoxuntakeaway.R;

/**
 * Created by zes on 16-2-19.
 */
public class CommentFragment extends BaseFragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mine, container, false);
        return rootView;
    }
}
