package com.glexer.smartlamp.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.glexer.smartlamp.R;
import com.glexer.smartlamp.data.fakedata.LocalDataSoures;
import com.glexer.smartlamp.data.model.MasterDevice;
import com.glexer.smartlamp.ui.adapter.MasterDeviceListAdapter;
import com.glexer.smartlamp.ui.widget.DividerItemDecoration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class MasterDeviceListFragment extends Fragment implements BaseQuickAdapter.RequestLoadMoreListener {

    List<MasterDevice> masterDeviceList = new ArrayList();
    MasterDeviceListAdapter mMasterDeviceListAdapter;
    public MasterDeviceListFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMasterDeviceListAdapter = new MasterDeviceListAdapter(getActivity(), R.layout.fragment_masterdeviceitem, masterDeviceList);
        masterDeviceList = LocalDataSoures.getSampleMasterDevice(10);
        Collections.sort(masterDeviceList);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_masterdeviceitem_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),LinearLayoutManager.VERTICAL));
            mMasterDeviceListAdapter.setOnLoadMoreListener(this,recyclerView);
            recyclerView.setAdapter(mMasterDeviceListAdapter);
            mMasterDeviceListAdapter.setNewData(masterDeviceList);
            setListener();
        }
        return view;
    }

    private void setListener() {
        mMasterDeviceListAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                final MasterDevice masterDevice = (MasterDevice) adapter.getData().get(position);
                PopupMenu menu = new PopupMenu(getContext(), view, Gravity.RIGHT);
                menu.inflate(R.menu.menu_delete);
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_delete_device:
                                new AlertDialog.Builder(getActivity())
                                        .setMessage("确定要删除 " + masterDevice.getName() + "?")
                                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        })
                                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
//                                            MqttReqAPI.mqttDeleteDevice(getActivity().getIntent().getStringExtra("did"), model.getNwk_addr(), model.getEp(), model.getIeee());
                                            }
                                        })
                                        .show();
                        }
                        return true;
                    }
                });
                menu.show();
                return true;
            }
        });

        mMasterDeviceListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MasterDevice masterDevice = (MasterDevice) adapter.getData().get(position);
                Log.i("device fragment", masterDevice.getName());
                Toast.makeText(getContext(),"short click", Toast.LENGTH_SHORT).show();
            }
        });
        mMasterDeviceListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public boolean onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                return false;
            }
        });
    }

    @Override
    public void onLoadMoreRequested() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                mMasterDeviceListAdapter.loadMoreComplete();
                mMasterDeviceListAdapter.loadMoreEnd();
            }
        }, 2000);
    }

    public MasterDeviceListAdapter getAdapter() {
        return mMasterDeviceListAdapter;
    }
}
