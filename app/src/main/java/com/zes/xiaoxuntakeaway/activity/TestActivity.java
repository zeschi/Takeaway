package com.zes.xiaoxuntakeaway.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.zes.bundle.adapter.MKBaseAdapter;
import com.zes.bundle.bean.ViewHolder;
import com.zes.bundle.utils.MKLog;
import com.zes.bundle.view.PinnedHeaderListView;
import com.zes.xiaoxuntakeaway.R;
import com.zes.xiaoxuntakeaway.adapter.TestSectionedAdapter;
import com.zes.xiaoxuntakeaway.bean.MenuCallback;
import com.zes.xiaoxuntakeaway.bean.MenuType;
import com.zes.xiaoxuntakeaway.bean.MenuTypeCallback;
import com.zes.xiaoxuntakeaway.bean.ResultDataInfo;
import com.zes.xiaoxuntakeaway.controller.MenuController;
import com.zes.xiaoxuntakeaway.controller.MenuTypeController;
import com.zes.xiaoxuntakeaway.fragment.MainFragment;

import java.util.Arrays;
import java.util.List;

import okhttp3.Call;


public class TestActivity extends Activity {

    private boolean isScroll = true;


    private List<com.zes.xiaoxuntakeaway.bean.Menu> menuList;
    private List<MenuType> menuTypeList;


    private ListView left_listView;

    private String[] leftStr;
    private com.zes.xiaoxuntakeaway.bean.Menu[][] rightStr;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_menu);

        if (!TextUtils.isEmpty(getIntent().getStringExtra(MainFragment.MERCHANT_ID))) {
            final String merchantId = getIntent().getStringExtra(MainFragment.MERCHANT_ID);
            MenuController.getMenuListByMerchantId(merchantId, new MenuCallback() {
                        @Override
                        public void onError(Call call, Exception e) {

                        }

                        @Override
                        public void onResponse(ResultDataInfo<List<com.zes.xiaoxuntakeaway.bean.Menu>> response) {
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

                                            initView();
                                        }
                                    }

                            );
                        }
                    }

            );


        }


    }

    private void initView() {
        final PinnedHeaderListView right_listview = (PinnedHeaderListView) findViewById(R.id.pinnedListView);
        LayoutInflater inflator = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout header1 = (LinearLayout) inflator.inflate(R.layout.list_item, null);
        ((TextView) header1.findViewById(R.id.textItem)).setText("HEADER 1");
        LinearLayout header2 = (LinearLayout) inflator.inflate(R.layout.list_item, null);
        ((TextView) header2.findViewById(R.id.textItem)).setText("HEADER 2");
        LinearLayout footer = (LinearLayout) inflator.inflate(R.layout.list_item, null);
        ((TextView) footer.findViewById(R.id.textItem)).setText("FOOTER");
//        listView.addHeaderView(header1);
//        listView.addHeaderView(header2);
//        listView.addFooterView(footer);
        final TestSectionedAdapter sectionedAdapter = new TestSectionedAdapter(this, leftStr, rightStr);
        right_listview.setAdapter(sectionedAdapter);

        left_listView = (ListView) findViewById(R.id.left_listview);
        left_listView.setAdapter(new MKBaseAdapter<String>(this, Arrays.asList(leftStr), R.layout.item_left_merchant) {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //  getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
