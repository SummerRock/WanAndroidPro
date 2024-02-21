package com.example.myapplication.main.ui.home.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.common.list.CollectionUtils;
import com.common.mainModule.LogUtils;
import com.example.myapplication.R;
import com.example.myapplication.main.ui.home.model.HomeBannerVo;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class HomeBannerSliderAdapter extends SliderViewAdapter<HomeBannerSliderAdapter.SliderViewHolder> {
    private final List<HomeBannerVo> dataList;

    public HomeBannerSliderAdapter(List<HomeBannerVo> dataList) {
        this.dataList = CollectionUtils.getListOrDefault(dataList);
    }

    @Override
    public SliderViewHolder onCreateViewHolder(ViewGroup parent) {
        LogUtils.i("创建HomeBannerView");
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_slider_layout, parent, false);
        return new SliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SliderViewHolder viewHolder, int position) {
        Glide.with(viewHolder.imageView.getContext())
                .load(dataList.get(position).getImagePath())
                .into(viewHolder.imageView);
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    public static class SliderViewHolder extends SliderViewAdapter.ViewHolder {
        ImageView imageView;

        public SliderViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.slider_image);
        }
    }
}

