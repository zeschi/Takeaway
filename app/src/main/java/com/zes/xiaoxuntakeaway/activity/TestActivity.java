package com.zes.xiaoxuntakeaway.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.zes.bundle.adapter.MKBaseAdapter;
import com.zes.bundle.bean.ViewHolder;
import com.zes.bundle.view.PinnedHeaderListView;
import com.zes.xiaoxuntakeaway.R;
import com.zes.xiaoxuntakeaway.adapter.TestSectionedAdapter;

import java.util.Arrays;


public class TestActivity extends Activity {

    private boolean isScroll = true;

    private ListView left_listView;

    private String[] leftStr = new String[]{"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"};
    private String[][] rightStr = new String[][]{
            {"星期一  早餐", "星期一  午餐", "星期一  晚餐"}, {"星期二  早餐", "星期二  午餐", "星期二  晚餐"},
            {"星期三  早餐", "星期三  午餐", "星期三  晚餐"}, {"星期四  早餐", "星期四  午餐", "星期四  晚餐"},
            {"星期五  早餐", "星期五  午餐", "星期五  晚餐"}, {"星期六  早餐", "星期六  午餐", "星期六  晚餐"},
            {"星期日  早餐", "星期日  午餐", "星期日  晚餐"}};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);
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
                holder.setCircleImageByUrl(R.id.iv_item_left_merchant_type, "http://pic25.nipic.com/20121206/6789926_185118320000_2.jpg", R.drawable.pictures_no);

            }
        });

        left_listView.setOnItemClickListener(new OnItemClickListener() {

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

        right_listview.setOnScrollListener(new OnScrollListener() {

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
