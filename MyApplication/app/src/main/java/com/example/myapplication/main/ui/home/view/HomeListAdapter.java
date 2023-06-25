package com.example.myapplication.main.ui.home.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.common.List.CollectionUtils;
import com.example.myapplication.R;
import com.example.myapplication.main.ui.home.model.HomeModelVo;

import java.util.List;

public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.ViewHolder> {

    private List<HomeModelVo.DatasDTO> data;

    public HomeListAdapter(List<HomeModelVo.DatasDTO> data) {
        this.data = data;
    }

    public void setData(List<HomeModelVo.DatasDTO> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_home_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeModelVo.DatasDTO datasDTO = data.get(position);
        holder.textView.setText(datasDTO.getTitle());
    }

    @Override
    public int getItemCount() {
        return CollectionUtils.getListSize(data);
    }

    // 自定义ViewHolder类
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.home_fg_item_title);
        }
    }
}
