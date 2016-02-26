package com.zes.xiaoxuntakeaway.fragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zes.bundle.adapter.MKBaseAdapter;
import com.zes.bundle.bean.ViewHolder;
import com.zes.bundle.fragment.BaseFragment;
import com.zes.bundle.utils.MKLog;
import com.zes.bundle.view.PinnedHeaderListView;
import com.zes.xiaoxuntakeaway.R;
import com.zes.xiaoxuntakeaway.adapter.TestSectionedAdapter;
import com.zes.xiaoxuntakeaway.bean.Menu;
import com.zes.xiaoxuntakeaway.bean.MenuCallback;
import com.zes.xiaoxuntakeaway.bean.MenuType;
import com.zes.xiaoxuntakeaway.bean.MenuTypeCallback;
import com.zes.xiaoxuntakeaway.bean.ResultDataInfo;
import com.zes.xiaoxuntakeaway.bean.ResultDataStringCallBack;
import com.zes.xiaoxuntakeaway.controller.MenuController;
import com.zes.xiaoxuntakeaway.controller.MenuTypeController;
import com.zes.xiaoxuntakeaway.controller.OrderController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;

/**
 * Created by zes on 16-2-19.
 */
public class MenuFragment extends BaseFragment implements View.OnClickListener {
    private boolean isScroll = true;


    private List<Menu> menuList;
    private List<MenuType> menuTypeList;
    private ImageView ivMenuCar;
    //起送价
    private int startPrice;
    //订单总价
    private int allPrice = 0;
    private ListView left_listView;
    private TextView allPriceTv;
    private TextView startPriceTv;
    private String[] leftStr;
    private Menu[][] rightStr;
    private List<Menu> menuDataList;
    //动画时间
    private int AnimationDuration = 500;
    //正在执行的动画数量
    private int number = 0;
    //是否完成清理
    private boolean isClean = false;
    private FrameLayout animation_viewGroup;
    private Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    //用来清除动画后留下的垃圾
                    try {
                        animation_viewGroup.removeAllViews();
                    } catch (Exception e) {

                    }

                    isClean = false;

                    break;
                default:
                    break;
            }
        }
    };

    private void doAnim(Drawable drawable, int[] start_location) {
        if (!isClean) {
            setAnim(drawable, start_location);

        } else {
            try {
                animation_viewGroup.removeAllViews();
                isClean = false;
                setAnim(drawable, start_location);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                isClean = true;
            }
        }
    }

    /**
     * 动画效果设置
     *
     * @param drawable       将要加入购物车的商品
     * @param start_location 起始位置
     */
    private void setAnim(Drawable drawable, int[] start_location) {


        Animation mScaleAnimation = new ScaleAnimation(1.1f, 0.0f, 1.1f, 0.0f, Animation.RELATIVE_TO_SELF, 0.1f, Animation.RELATIVE_TO_SELF, 0.1f);
        mScaleAnimation.setDuration(AnimationDuration);
        mScaleAnimation.setFillAfter(true);


        final ImageView iview = new ImageView(getActivity());
        iview.setImageDrawable(drawable);
        final View view = addViewToAnimLayout(animation_viewGroup, iview, start_location);
        view.setAlpha(0.6f);

        int[] end_location = new int[2];
        ivMenuCar.getLocationInWindow(end_location);
        int endX = end_location[0];
        int endY = end_location[1] - start_location[1];

        Animation mTranslateAnimation = new TranslateAnimation(0, -start_location[0], 0, endY);
        Animation mRotateAnimation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mRotateAnimation.setDuration(AnimationDuration);
        mTranslateAnimation.setDuration(AnimationDuration);
        AnimationSet mAnimationSet = new AnimationSet(true);

        mAnimationSet.setFillAfter(true);
        mAnimationSet.addAnimation(mRotateAnimation);
        mAnimationSet.addAnimation(mScaleAnimation);
        mAnimationSet.addAnimation(mTranslateAnimation);

        mAnimationSet.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub
                number++;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // TODO Auto-generated method stub

                number--;
                if (number == 0) {
                    isClean = true;
                    myHandler.sendEmptyMessage(0);
                }

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub

            }

        });
        view.startAnimation(mAnimationSet);

    }

    /**
     * @param
     * @return void
     * @throws
     * @Description: 创建动画层
     */
    private FrameLayout createAnimLayout() {
        ViewGroup rootView = (ViewGroup) getActivity().getWindow().getDecorView();
        FrameLayout animLayout = new FrameLayout(getActivity());
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        animLayout.setLayoutParams(lp);
        animLayout.setBackgroundResource(android.R.color.transparent);
        rootView.addView(animLayout);
        return animLayout;

    }

    /**
     * @param vg       动画运行的层 这里是frameLayout
     * @param view     要运行动画的View
     * @param location 动画的起始位置
     * @return
     * @deprecated 将要执行动画的view 添加到动画层
     */
    private View addViewToAnimLayout(ViewGroup vg, View view, int[] location) {
        int x = location[0];
        int y = location[1];
        vg.addView(view);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                dip2px(getActivity(), 90), dip2px(getActivity(), 90));
        lp.leftMargin = x;
        lp.topMargin = y;
        view.setPadding(5, 5, 5, 5);
        view.setLayoutParams(lp);

        return view;
    }

    /**
     * dip，dp转化成px 用来处理不同分辨路的屏幕
     *
     * @param context
     * @param dpValue
     * @return
     */
    private int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static MenuFragment newInstance(String merchantId) {

        Bundle args = new Bundle();
        args.putString(MainFragment.MERCHANT_ID, merchantId);
        MenuFragment menuFragment = new MenuFragment();
        menuFragment.setArguments(args);
        return menuFragment;
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_menu, container, false);
        ivMenuCar = (ImageView) rootView.findViewById(R.id.iv_menu_car);
        startPriceTv = (TextView) rootView.findViewById(R.id.tv_order_menu_start_price);
        if (!TextUtils.isEmpty(getArguments().getString(MainFragment.MERCHANT_START_PRICE))) {
            startPrice = Integer.valueOf(getArguments().getString(MainFragment.MERCHANT_START_PRICE));
        }


        if (!TextUtils.isEmpty(getArguments().getString(MainFragment.MERCHANT_ID))) {
            final String merchantId = getArguments().getString(MainFragment.MERCHANT_ID);
            MenuController.getMenuListByMerchantId(merchantId, new MenuCallback() {
                        @Override
                        public void onError(Call call, Exception e) {

                        }

                        @Override
                        public void onResponse(ResultDataInfo<List<Menu>> response) {
                            if (response == null || response.getData() == null)
                                return;
                            MKLog.e("menu" + response.getData().toString());
                            menuList = response.getData();
                            MenuTypeController.getMenuTypeListByMerchantId(merchantId, new MenuTypeCallback() {
                                        @Override
                                        public void onError(Call call, Exception e) {

                                        }

                                        @Override
                                        public void onResponse(ResultDataInfo<List<MenuType>> response) {
                                            if (response == null || response.getData() == null)
                                                return;
                                            MKLog.e("menuType" + response.getData().toString());
                                            menuTypeList = response.getData();

                                            leftStr = new String[menuTypeList.size()];
                                            rightStr = new com.zes.xiaoxuntakeaway.bean.Menu[menuTypeList.size()][];
                                            int count = 0;
                                            int lastPos = 0;
                                            for (int i = 0; i < menuTypeList.size(); i++) {
                                                leftStr[i] = menuTypeList.get(i).getMenu_type_name();
                                                for (int j = lastPos; j < menuList.size(); j++) {
                                                    if (menuTypeList.get(i).getMenu_type_id().equals(menuList.get(j).getMenu_type_id())) {
                                                        count++;
                                                    }
                                                }
                                                rightStr[i] = new com.zes.xiaoxuntakeaway.bean.Menu[count];
                                                MKLog.e("count" + count);
                                                lastPos += count;
                                                count = 0;
                                            }
                                            int pos = 0;
                                            for (int i = 0; i < menuTypeList.size(); i++)
                                                for (int j = 0; j < rightStr[i].length; j++) {
                                                    for (int k = pos; k < menuList.size(); k++)
                                                        if (menuTypeList.get(i).getMenu_type_id().equals(menuList.get(k).getMenu_type_id())) {
                                                            rightStr[i][j] = menuList.get(k);
                                                            pos++;
                                                            break;
                                                        }
                                                }

                                            initView(rootView, inflater);
                                        }
                                    }

                            );
                        }
                    }

            );


        }
        return rootView;

    }

    private void initView(View rootView, LayoutInflater inflater) {
        final PinnedHeaderListView right_listview = (PinnedHeaderListView) rootView.findViewById(R.id.pinnedListView);
        menuDataList = new ArrayList<>();
        allPriceTv = (TextView) rootView.findViewById(R.id.tv_order_menu_all_price);
        LinearLayout header1 = (LinearLayout) inflater.inflate(R.layout.list_item, null);
        ((TextView) header1.findViewById(R.id.textItem)).setText("HEADER 1");
        LinearLayout header2 = (LinearLayout) inflater.inflate(R.layout.list_item, null);
        ((TextView) header2.findViewById(R.id.textItem)).setText("HEADER 2");
        LinearLayout footer = (LinearLayout) inflater.inflate(R.layout.list_item, null);
        ((TextView) footer.findViewById(R.id.textItem)).setText("FOOTER");
        animation_viewGroup = createAnimLayout();


        final TestSectionedAdapter sectionedAdapter = new TestSectionedAdapter(getActivity(), leftStr, rightStr);
        sectionedAdapter.setOnSetHolderClickListener(new TestSectionedAdapter.HolderClickListener() {
            @Override
            public void onHolderClick(Drawable drawable, int[] start_location, int section, int position) {
                doAnim(drawable, start_location);
                allPrice += Integer.parseInt(rightStr[section][position].getMenu_sale_price());

                menuDataList.add(rightStr[section][position]);


                MKLog.e(rightStr[section][position].getMenu_sale_price() + "aaaa");
                allPriceTv.setText("￥" + allPrice);
                if (allPrice > startPrice) {
                    startPriceTv.setText("去结算");
                    startPriceTv.setOnClickListener(MenuFragment.this);

                }
            }
        });
        right_listview.setAdapter(sectionedAdapter);

        left_listView = (ListView) rootView.findViewById(R.id.left_listview);
        left_listView.setAdapter(new MKBaseAdapter<String>(getActivity(), Arrays.asList(leftStr), R.layout.item_left_merchant) {
            /**
             * 所有逻辑代码在子类中的这个方法中实现。
             *
             * @param holder
             * @param data
             */
            @Override
            public void convert(ViewHolder holder, String data) {
                holder.setText(R.id.tv_item_left_menu_type, data);
                holder.setCircleImageByUrl(R.id.iv_item_left_merchant_type, "http://pic25.nipic.com/20121206/6789926_185118320000_2.jpg", R.drawable.pictures_no);

            }
        });

        left_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position,
                                    long arg3) {
                isScroll = false;

                for (int i = 0; i < left_listView.getChildCount(); i++) {
                    if (i == position) {
                        left_listView.getChildAt(i).setBackgroundColor(Color.rgb(255, 255, 255));
                    } else {
                        left_listView.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                    }
                }

                int rightSection = 0;
                for (int i = 0; i < position; i++) {
                    rightSection += sectionedAdapter.getCountForSection(i) + 1;
                }
                right_listview.setSelection(rightSection);

            }

        });

        right_listview.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView arg0, int arg1) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                if (isScroll) {
                    for (int i = 0; i < left_listView.getChildCount(); i++) {

                        if (i == sectionedAdapter.getSectionForPosition(firstVisibleItem)) {
                            left_listView.getChildAt(i).setBackgroundColor(
                                    Color.rgb(255, 255, 255));
                        } else {
                            left_listView.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);

                        }
                    }

                } else {
                    isScroll = true;
                }
            }
        });


    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_order_menu_start_price:
                MKLog.e(new Gson().toJson(menuDataList) + "json");
                OrderController.createOrder("1", "1", new Gson().toJson(menuDataList), new ResultDataStringCallBack() {
                    @Override
                    public void onError(Call call, Exception e) {

                    }

                    @Override
                    public void onResponse(ResultDataInfo<String> response) {
                        Toast.makeText(getActivity(), response.getRetmsg() + "papappapa", Toast.LENGTH_SHORT).show();
                    }
                });

                break;
        }

    }
}
