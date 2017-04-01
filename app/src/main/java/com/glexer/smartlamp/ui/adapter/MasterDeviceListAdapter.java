package com.glexer.smartlamp.ui.adapter;

import android.content.Context;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.glexer.smartlamp.R;
import com.glexer.smartlamp.data.model.MasterDevice;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.filter;

/**
 * Created by Trice on 2017/3/23.
 */

public class MasterDeviceListAdapter extends BaseQuickAdapter<MasterDevice,BaseViewHolder> implements Filterable {

    private Context mContext;
    Filter filter;
    List<MasterDevice> mFilterData;

    public MasterDeviceListAdapter(Context mContext, int layoutResId, List<MasterDevice> data) {
        super(layoutResId, data);
        this.mContext = mContext;
        mFilterData = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, MasterDevice item) {
        helper.addOnClickListener(R.id.device_img);
        helper.setImageResource(R.id.device_img, R.mipmap.ic_launcher);
        helper.setText(R.id.device_name, item.getName());
        helper.setText(R.id.device_sn, item.getSn());
        helper.setText(R.id.device_online, item.getOnline() == 1 ? "在线" : "离线");

//        helper.itemView.setOnLongClickListener(onlong);
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new RecyclerViewFilter();
        }
        return filter;
    }

    private class RecyclerViewFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            //返回的results即为过滤后的ArrayList<City>
            FilterResults results = new FilterResults();

            //无约束字符串则返回null
            if (charSequence == null || charSequence.length() == 0) {
                results.values = null;
                results.count = 0;
            } else {
                String prefixString = charSequence.toString().toLowerCase();
                //新建Values存放过滤后的数据
                ArrayList<MasterDevice> newValues = new ArrayList<>();
                for (MasterDevice masterDevice : mFilterData) {
                    if (masterDevice.getSn().contains(prefixString)) {
                        newValues.add(masterDevice);
                    }
                }
                results.values = newValues;
                results.count = newValues.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            mData = (List<MasterDevice>) filterResults.values;
            if (filterResults.count > 0) {
                notifyDataSetChanged();//重绘当前可见区域
            } else {
                mData = mFilterData;
                notifyDataSetChanged();//会重绘控件（还原到初始状态）
            }
        }
    }
}
