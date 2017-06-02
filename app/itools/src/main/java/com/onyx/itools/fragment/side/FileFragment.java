package com.onyx.itools.fragment.side;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.onyx.itools.R;
import com.onyx.itools.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @Copyright: Copyright © 2017 Onyx International Inc. All rights reserved.
 * @Project: iTools
 * @Author: Jack
 * @Date: 2017/3/30 0030,0:48
 * @Version: V1.0
 * @Description: TODO
 */
public class FileFragment extends BaseFragment {
    private TextView textView;
    private RecyclerView rcvFiles;
    private FileListAdapter fileListAdapter;
    private List<String> mDatas;
    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_file;
    }

    @Override
    protected void initView(View view) {
        textView = (TextView) view.findViewById(R.id.menu_text);
        String text = getArguments().getString("text");
        textView.setText(text);


        rcvFiles = (RecyclerView) view.findViewById(R.id.rcv_files);

        rcvFiles.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcvFiles.setAdapter( fileListAdapter = new FileListAdapter());
        rcvFiles.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    protected void intData() {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++)
        {
            mDatas.add("" + (char) i);
        }
    }

    @Override
    protected void intEvent() {

    }

    class FileListAdapter extends RecyclerView.Adapter<FileListAdapter.MyViewHolder>
    {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    getActivity()).inflate(R.layout.item_recycleview_files, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position)
        {
            holder.tv.setText(mDatas.get(position));
        }

        @Override
        public int getItemCount()
        {
            return mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder
        {

            TextView tv;

            public MyViewHolder(View view)
            {
                super(view);
                tv = (TextView) view.findViewById(R.id.id_num);
            }
        }
    }
}
