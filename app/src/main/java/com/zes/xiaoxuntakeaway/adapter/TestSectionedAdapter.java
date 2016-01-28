package com.zes.xiaoxuntakeaway.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zes.bundle.view.SectionedBaseAdapter;
import com.zes.xiaoxuntakeaway.R;


public class TestSectionedAdapter extends SectionedBaseAdapter {

    private Context mContext;
    private String[] leftStr;
    private String[][] rightStr;

    public TestSectionedAdapter(Context context, String[] leftStr, String[][] rightStr) {
        this.mContext = context;
        this.leftStr = leftStr;
        this.rightStr = rightStr;
    }

    @Override
    public Object getItem(int section, int position) {
        return rightStr[section][position];
    }

    @Override
    public long getItemId(int section, int position) {
        return position;
    }

    @Override
    public int getSectionCount() {
        return leftStr.length;
    }

    @Override
    public int getCountForSection(int section) {
        return rightStr[section].length;
    }

    @Override
    public View getItemView(final int section, final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_right_merchant, null);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.iv_item_right_merchant_order);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (viewHolder != null) {
            Glide.with(mContext).load("http://pic25.nipic.com/20121206/6789926_185118320000_2.jpg").placeholder(R.drawable.pictures_no).into(viewHolder.imageView);
        }
        return convertView;
    }

    @Override
    public View getSectionHeaderView(int section, View convertView, ViewGroup parent) {
        LinearLayout layout = null;
        if (convertView == null) {
            LayoutInflater inflator = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = (LinearLayout) inflator.inflate(R.layout.head_item, null);
        } else {
            layout = (LinearLayout) convertView;
        }
        layout.setClickable(false);
        ((TextView) layout.findViewById(R.id.textItem)).setText(leftStr[section]);
        return layout;
    }

    private class ViewHolder {
        ImageView imageView;
        RelativeLayout layout;

    }
}
