package com.zes.xiaoxuntakeaway.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
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
import com.zes.xiaoxuntakeaway.bean.Menu;


public class TestSectionedAdapter extends SectionedBaseAdapter {

    private Context mContext;
    private String[] leftStr;
    private Menu[][] rightStr;
    private HolderClickListener mHolderClickListener;
    private ViewHolder holder;
    private int count = 0;

    public TestSectionedAdapter(Context context, String[] leftStr, Menu[][] rightStr) {
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
        holder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_right_merchant, null);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.iv_item_right_merchant_order);
            viewHolder.menuSalePriceTv = (TextView) convertView.findViewById(R.id.tv_item_right_merchant_order_price);
            viewHolder.menuGoodLikeTv = (TextView) convertView.findViewById(R.id.tv_item_right_menu_good_like);
            viewHolder.menuMouthSaleTv = (TextView) convertView.findViewById(R.id.tv_item_right_menu_month_sale);
            viewHolder.menuNameTv = (TextView) convertView.findViewById(R.id.tv_item_right_menu_name);
            viewHolder.menuAddIv = (ImageView) convertView.findViewById(R.id.iv_item_right_merchant_add);
            viewHolder.menuDelIv = (ImageView) convertView.findViewById(R.id.iv_item_right_merchant_del);
            viewHolder.menuCountTv = (TextView) convertView.findViewById(R.id.tv_item_menu_count);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (viewHolder != null) {
            holder = viewHolder;
            viewHolder.menuNameTv.setText(rightStr[section][position].getMenu_name());
            viewHolder.menuMouthSaleTv.setText("月售" + rightStr[section][position].getMenu_mouth_sale());
            viewHolder.menuGoodLikeTv.setText("好评" + rightStr[section][position].getMenu_good_like());
            viewHolder.menuSalePriceTv.setText("￥" + rightStr[section][position].getMenu_sale_price());
            final ViewHolder finalViewHolder = viewHolder;
            viewHolder.menuAddIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    count = Integer.parseInt(finalViewHolder.menuCountTv.getText().toString()) + 1;
                    finalViewHolder.menuCountTv.setText(count + "");
                    if (mHolderClickListener != null) {
                        int[] start_location = new int[2];
                        holder.menuAddIv.getLocationInWindow(start_location);//获取点击商品图片的位置
                        Drawable drawable = holder.menuAddIv.getDrawable();//复制一个新的商品图标
                        mHolderClickListener.onHolderClick(drawable, start_location, section, position);
                    }

                }
            });
            viewHolder.menuDelIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    count = Integer.parseInt(finalViewHolder.menuCountTv.getText().toString()) - 1;
                    if (count < 0)
                        count = 0;
                    finalViewHolder.menuCountTv.setText(count + "");
                }
            });
            Glide.with(mContext).load(rightStr[section][position].getMenu_portrait()).placeholder(R.drawable.pictures_no).into(viewHolder.imageView);
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
        TextView menuNameTv;
        TextView menuMouthSaleTv;
        TextView menuGoodLikeTv;
        ImageView menuAddIv;
        ImageView menuDelIv;
        TextView menuSalePriceTv;
        TextView menuCountTv;
        RelativeLayout layout;

    }

    public void setOnSetHolderClickListener(HolderClickListener holderClickListener) {
        this.mHolderClickListener = holderClickListener;
    }

    public interface HolderClickListener {
        void onHolderClick(Drawable drawable, int[] start_location, int section, int position);
    }
}
