package com.example.myapplication.base.view;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.common.list.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseListAdapter<V extends RecyclerView.ViewHolder, T> extends RecyclerView.Adapter<V> {

    protected final List<T> data = new ArrayList<>();

    public BaseListAdapter(@Nullable List<T> list) {
        if (list != null) {
            data.addAll(list);
        }
    }

    public void refreshData(List<T> list) {
        this.data.clear();
        this.data.addAll(list);
        notifyDataSetChanged();
    }

    public void addData(List<T> list) {
        this.data.addAll(list);
        notifyDataSetChanged();
    }

    public List<T> getData() {
        return this.data;
    }

    @Override
    public int getItemCount() {
        return CollectionUtils.getListSize(data);
    }
}
