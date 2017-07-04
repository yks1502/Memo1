package com.example.memo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YUNKYUSEOK on 2017-06-29.
 */

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {
    private List<Memo> memoList;

    public RecycleAdapter(List<Memo> memos) {
        this.memoList = memos;
    }

    @Override
    public RecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Memo item = memoList.get(position);
        viewHolder.titleView.setText("Title  " + item.title);
        viewHolder.contentView.setText("Content  " + item.content);
        viewHolder.idView.setText(String.valueOf(item.id));
    }

    @Override
    public int getItemCount() {
        return memoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleView;
        public TextView contentView;
        public TextView idView;

        public ViewHolder(View v) {
            super(v);
            titleView = (TextView) v.findViewById(R.id.rv_title);
            contentView = (TextView) v.findViewById(R.id.rv_content);
            idView = (TextView) v.findViewById(R.id.rv_id);
        }
    }
}
