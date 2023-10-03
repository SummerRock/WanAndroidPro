package com.example.myapplication.main.ui.home.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.base.view.BaseViewBinder;
import com.example.myapplication.base.view.OnListItemClickListener;
import com.example.myapplication.main.ui.home.model.HomeBannerWrap;
import com.smarteist.autoimageslider.SliderView;

public class HomeBannerViewBinder extends BaseViewBinder<HomeBannerWrap, HomeBannerViewBinder.CustomViewHolder> {

    public HomeBannerViewBinder(@Nullable OnListItemClickListener listener) {
        super(listener);
    }

    @NonNull
    @Override
    protected CustomViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_banner_container, parent, false);
        return new CustomViewHolder(itemView);
    }

    @Override
    protected void onBindViewHolder(@NonNull CustomViewHolder customViewHolder, @NonNull HomeBannerWrap homeBannerWrap) {
        SliderView sliderView = customViewHolder.imageSlider;
        sliderView.setSliderAdapter(new HomeBannerSliderAdapter(homeBannerWrap.dataList));
    }

    static class CustomViewHolder extends RecyclerView.ViewHolder {

        SliderView imageSlider;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            imageSlider = itemView.findViewById(R.id.imageSlider);
        }
    }
}
