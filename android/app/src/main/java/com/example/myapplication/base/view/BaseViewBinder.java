package com.example.myapplication.base.view;


import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import me.drakeet.multitype.ItemViewBinder;

public abstract class BaseViewBinder<T, VH extends RecyclerView.ViewHolder> extends ItemViewBinder<T, VH> {

    protected final OnListItemClickListener listener;

    public BaseViewBinder() {
        this(null);
    }

    public BaseViewBinder(@Nullable OnListItemClickListener listener) {
        this.listener = listener;
    }
}
